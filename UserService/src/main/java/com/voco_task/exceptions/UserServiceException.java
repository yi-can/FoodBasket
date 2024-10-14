package com.voco_task.exceptions;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException{
    private final ErrorType errorType;

    public UserServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public UserServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }



}
