package com.voco_task.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    LOGIN_ERROR(4000,"E-posta veya parola hatalı.",HttpStatus.BAD_REQUEST),
    PASSWORD_UNMATCH(4001,"Parolalar eşleşmiyor.",HttpStatus.BAD_REQUEST),//login
    STATUS_NOT_ACTIVE(4002,"Kullanıcı durumu etkin değil, etkinleştirme bağlantısını almak için lütfen e-postanızı kontrol edin.",HttpStatus.BAD_REQUEST),
    USER_DELETED(4003,"Kullanıcı kaydı silinmiştir.Yeni bir kullanıcı oluşturunuz!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4004,"Kullanıcı bulunamadı", HttpStatus.BAD_REQUEST),
    CODE_NO_VALID(4005, "Girilen kod geçerli değil.", HttpStatus.BAD_REQUEST),
    EXIST_BY_EMAIL(4006,"E-posta zaten mevcut.",HttpStatus.BAD_REQUEST),
    TOKEN_NOT_CREATED(4100,"Token oluşturulamadı.",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4101,"Geçersiz token bilgisi.",HttpStatus.BAD_REQUEST),
    MAİL_NOT_SEND(4300, "E-posta gönderme işlem başarısız olmuştur",HttpStatus.METHOD_NOT_ALLOWED),
    MAİL_NOT_VALİD(4301, "E-posta adresi geçerli değildir",HttpStatus.METHOD_NOT_ALLOWED),
    EMAIL_DUPLICATE(4302,"E-posta zaten mevcut.",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(4303,"E-posta bulunamadı. Lütfen tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST(5000,"Parametre hatası.",HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(5100,"Beklenmeyen bir hata oluştu.",HttpStatus.INTERNAL_SERVER_ERROR),


;


    private int code;
    private String message;
    HttpStatus httpStatus;
}
