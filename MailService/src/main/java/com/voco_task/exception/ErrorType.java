package com.voco_task.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {

    INTERNAL_ERROR_SERVER(5200,"Sunucu Hatası",HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(4200,"Parametre hatası",HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(4210, "Böyle bir kullanıcı bulunamadı",HttpStatus.NOT_FOUND),
    INVALID_CODE(4212, "Geçersiz Kod",HttpStatus.BAD_REQUEST),
    UNEXPECTED_ERROR(4213,"Beklenmeyen bir hata olustu",HttpStatus.BAD_REQUEST),
    INVALID_TOKEN(4214, "Geçersiz Token !!!",HttpStatus.BAD_REQUEST),
    STATUS_NOT_FOUND(4217, "Böyle bir kullanıcı durumu bulunamadı",HttpStatus.BAD_REQUEST ),
    MAİL_NOT_SEND(4300, "E-posta gönderme işlem başarısız olmuştur",HttpStatus.METHOD_NOT_ALLOWED),
    MAİL_NOT_VALİD(4301, "E-posta adresi geçerli değildir",HttpStatus.METHOD_NOT_ALLOWED),
    EMAIL_DUPLICATE(4302,"E-posta zaten mevcut.",HttpStatus.BAD_REQUEST),
    EMAIL_NOT_FOUND(4303,"E-posta bulunamadı. Lütfen tekrar deneyiniz.",HttpStatus.BAD_REQUEST),
    ;


    private int code;
    private String message;
    HttpStatus httpStatus;


}
