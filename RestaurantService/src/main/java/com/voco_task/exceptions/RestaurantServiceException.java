package com.voco_task.exceptions;

import lombok.Getter;

@Getter
public class RestaurantServiceException extends RuntimeException{
    private final ErrorType errorType;

    public RestaurantServiceException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public RestaurantServiceException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }



}
