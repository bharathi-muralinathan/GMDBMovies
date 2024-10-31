package com.gmdb.gmdb;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StarRatingRequiredException extends RuntimeException{

    public StarRatingRequiredException(String message){
        super(message);
    }
}
