package com.voco_task.service;


import com.voco_task.dto.request.LoginRequestDto;
import com.voco_task.dto.request.PasswordForgotDto;
import com.voco_task.dto.request.RegisterRequestRestaurantDto;
import com.voco_task.dto.request.RegisterRequestUserDto;
import com.voco_task.exceptions.AuthServiceException;
import com.voco_task.exceptions.ErrorType;
import com.voco_task.mapper.IAuthMapper;
import com.voco_task.rabbitmq.model.*;
import com.voco_task.rabbitmq.producer.AuthMailProducer;
import com.voco_task.rabbitmq.producer.AuthRestaurantProducer;
import com.voco_task.rabbitmq.producer.AuthUserProducer;
import com.voco_task.repository.entity.Auth;
import com.voco_task.repository.IAuthRepository;
import com.voco_task.repository.enums.ERole;
import com.voco_task.repository.enums.EStatus;
import com.voco_task.utility.CodeGenerator;
import com.voco_task.utility.JwtTokenManager;
import com.voco_task.utility.ServiceManager;
import org.springframework.stereotype.Service;
//import org.springframework.kafka.core.KafkaTemplate;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class AuthService extends ServiceManager<Auth, String> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final AuthRestaurantProducer authRestaurantProducer;
    private final AuthMailProducer authMailProducer;
    private final AuthUserProducer authUserProducer;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, AuthRestaurantProducer authRestaurantProducer, AuthMailProducer authMailProducer, AuthUserProducer authUserProducer) {
        super(authRepository);
        this.authRepository = authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.authRestaurantProducer = authRestaurantProducer;
        this.authMailProducer = authMailProducer;
        this.authUserProducer = authUserProducer;
    }

    public String registerUser(RegisterRequestUserDto dto) {
        Optional<Auth> authOptional = authRepository.findByEmail(dto.getEmail());
        if (!authOptional.isEmpty())
            throw new AuthServiceException(ErrorType.EXIST_BY_EMAIL);
        if (!isValidEmailAddress(dto.getEmail())) {
            throw new AuthServiceException(ErrorType.MAİL_NOT_VALİD);
        }
        Auth auth = IAuthMapper.INSTANCE.toRegisterUser(dto);
        auth.setActivationCode(CodeGenerator.generateCode());
        auth.setRole(ERole.USER);
        auth.setStatus(EStatus.ACTIVE);
        save(auth);
        String token = jwtTokenManager.createToken(auth.getAuthId(), auth.getRole(), auth.getEmail())
                .orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));
        authMailProducer.sendMail(AuthMailModel.builder()
                .activationCode(auth.getActivationCode())
                .token(token)
                .email(auth.getEmail())
                .firstname(dto.getFirstname())
                .build());
        AuthUserModel authUserModel = IAuthMapper.INSTANCE.toAuthUserModel(dto);
        authUserProducer.createUser(authUserModel);
        return token;
    }

    public Boolean registerRestaurant(RegisterRequestRestaurantDto dto) {
        Optional<Auth> authOptional = authRepository.findByEmail(dto.getEmail());
        if (!authOptional.isEmpty())
            throw new AuthServiceException(ErrorType.EXIST_BY_EMAIL);
        if (!isValidEmailAddress(dto.getEmail())) {
            throw new AuthServiceException(ErrorType.MAİL_NOT_VALİD);
        }
        Auth auth = IAuthMapper.INSTANCE.toRegisterRestaurant(dto);
        auth.setRole(ERole.RESTAURANT);
        save(auth);
        authRestaurantProducer.createRestaurant(AuthRestaurantModel.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .avatar(dto.getAvatar()).build());
        return true;
    }

    public String login(LoginRequestDto dto) {
        Optional<Auth> authOptional = authRepository.findByEmailAndPassword(dto.getEmail(), dto.getPassword());
        if (authOptional.isEmpty())
            throw new AuthServiceException(ErrorType.LOGIN_ERROR);
        if (authOptional.get().getStatus().equals(EStatus.INACTIVE)) {
            throw new AuthServiceException(ErrorType.STATUS_NOT_ACTIVE);
        }
        return jwtTokenManager.createToken(authOptional.get().getAuthId(), authOptional.get().getRole(), authOptional.get().getEmail())
                .orElseThrow(() -> new AuthServiceException(ErrorType.TOKEN_NOT_CREATED));
    }


    public String activateStatus(String token) {
//        String authId = jwtTokenManager.getIdFromToken(token).orElseThrow(() -> {
//            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
//        });
        Optional<String> authId = jwtTokenManager.getIdFromToken(token);
        if (authId.isEmpty()) {
            throw new AuthServiceException(ErrorType.INVALID_TOKEN);
        }
        System.out.println(authId.get());
        Optional<Auth> optionalAuth = findById(authId.get());
        if (!optionalAuth.isEmpty())
            throw new AuthServiceException(ErrorType.EXIST_BY_EMAIL);
        System.out.println("Deneme " + optionalAuth.get().getEmail());
        optionalAuth.get().setStatus(EStatus.ACTIVE);
        update(optionalAuth.get());

        return "Hesabınız aktive edilmiştir";
    }

    //E-Posta adreslerinin geçerli olup olmadığını kontrol etmek için kullanıldı.
    public static boolean isValidEmailAddress(String email) {
        String EMAIL_REGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public String updateAuth(UserAuthUpdateModel model) {
        String email = jwtTokenManager.getEmailFromToken(model.getToken()).
                orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));
        Auth auth = authRepository.findByEmail(email).orElseThrow(() -> new AuthServiceException((ErrorType.USER_NOT_FOUND)));
        auth.setPassword(model.getPassword());
        if(!model.getEmail().isEmpty())
            auth.setEmail(model.getEmail());
        update(auth);
        return "Güncelleme işlemi başarı ile gerçekleşti";
    }


    public String forgot(PasswordForgotDto dto) {
        Auth auth = authRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new AuthServiceException((ErrorType.EMAIL_NOT_FOUND)));
        if(auth.getActivationCode().equals(dto.getActivationCode())) {
            String token = jwtTokenManager.createTokenPassword(auth.getPassword()).orElseThrow(() -> new AuthServiceException(ErrorType.INVALID_TOKEN));
            authMailProducer.sendMailPassword(AuthMailModel.builder()
                    .activationCode(auth.getActivationCode())
                    .email(auth.getEmail())
                    .token(token)
                    .build());
            return "Parolanız mailinize gitmiştir";
        }
        return "Aktivasyon kodunuz ya da mail adresinizi hatalı girdiniz";
    }

    public void deleteAuth(UserAuthUpdateModel model) {
        Auth auth = authRepository.findByEmail(model.getEmail()).orElseThrow(() -> {throw new AuthServiceException(ErrorType.USER_NOT_FOUND);});
        deleteById(auth.getAuthId());
    }
}
