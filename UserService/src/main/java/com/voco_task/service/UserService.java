package com.voco_task.service;


import com.voco_task.dto.request.UserUpdateDto;
import com.voco_task.exceptions.ErrorType;
import com.voco_task.exceptions.UserServiceException;
import com.voco_task.mapper.IUserMapper;
import com.voco_task.rabbitmq.model.AuthUserModel;
import com.voco_task.rabbitmq.model.EmailControlModel;
import com.voco_task.rabbitmq.model.UserAuthUpdateModel;
import com.voco_task.rabbitmq.producer.UserAuthProducer;
import com.voco_task.repository.IUserRepository;
import com.voco_task.repository.entity.User;
import com.voco_task.utility.JwtTokenManager;
import com.voco_task.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceManager<User, String> {

    private final IUserRepository userRepository;
    private final JwtTokenManager jwtTokenManager;
    private final UserAuthProducer userAuthProducer;


    public UserService(IUserRepository userRepository, JwtTokenManager jwtTokenManager, UserAuthProducer userAuthProducer) {
        super(userRepository);
        this.userRepository = userRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.userAuthProducer = userAuthProducer;
    }

    public void saveUser(AuthUserModel model) {
        User user = IUserMapper.INSTANCE.toRegisterUser(model);
        save(user);
    }

    public String registerUser(UserUpdateDto dto, String token) {
        String email = jwtTokenManager.getEmailFromToken(token).orElseThrow(() -> {
        throw new UserServiceException(ErrorType.INVALID_TOKEN);
        });
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty())
            throw new UserServiceException(ErrorType.USER_NOT_FOUND);
        if (!dto.getFirstname().isEmpty()) {
            userOptional.get().setFirstname(dto.getFirstname());
        }if (!dto.getSurname().isEmpty()) {
            userOptional.get().setSurname(dto.getSurname());
        }if (!dto.getPhoneNumber().isEmpty()) {
            userOptional.get().setPhoneNumber(dto.getPhoneNumber());
        }if (!dto.getProfilePicture().isEmpty()) {
            userOptional.get().setProfilePicture(dto.getProfilePicture());
        }if (!dto.getGender().isEmpty()) {
            userOptional.get().setGender(dto.getGender());
        }if (!dto.getAddress().isEmpty()) {
            userOptional.get().setAddress(dto.getAddress());
        }if (!dto.getPassword().isEmpty() && !dto.getPassword().equals(userOptional.get().getPassword())) {
            userOptional.get().setPassword(dto.getPassword());
        }if (!dto.getEmail().isEmpty() && !dto.getEmail().equals(userOptional.get().getEmail())) {
            Boolean control = (Boolean) userAuthProducer.userControl(EmailControlModel.builder().email(email).build());
            if (control) {
                userOptional.get().setEmail(dto.getEmail());
                update(userOptional.get());
                userAuthProducer.updateAuth(UserAuthUpdateModel.builder()
                .token(token).email(userOptional.get().getEmail()).password(userOptional.get().getPassword()).build());
                return "Güncelleme işlemleriniz gerçekleştirilmiştir";
            }else{
                userAuthProducer.updateAuth(UserAuthUpdateModel.builder()
                        .token(token).email(null).password(userOptional.get().getPassword()).build());
                return "Girilen email adresi sistemde mevcut olduğu için email değiştirme işleminiz gerçekleştirememiştir";
            }
        }
        return "Tamamdır";
    }

    public String deleteUser(String token) {
        String email = jwtTokenManager.getEmailFromToken(token).orElseThrow(() -> {throw new UserServiceException(ErrorType.INVALID_TOKEN);});
        User user = userRepository.findByEmail(email).orElseThrow(() -> { throw new UserServiceException(ErrorType.USER_NOT_FOUND);});
        System.out.println(user.getUserId());
        userAuthProducer.userDelete(UserAuthUpdateModel.builder().email(email).build());
        deleteById(user.getUserId());
        return "Hesabınız silinmiştir";
    }
}
