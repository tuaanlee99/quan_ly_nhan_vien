package com.example.quan_ly_nhan_vien.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.IM_USED)
public class ResourceBeUsedException extends Exception{
    public ResourceBeUsedException(String message, Object object){
        super(message, (Throwable) object);
    }

}
