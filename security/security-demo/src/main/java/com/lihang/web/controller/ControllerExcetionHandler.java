package com.lihang.web.controller;

import com.lihang.exception.UserNotExitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExcetionHandler {
    @ExceptionHandler(UserNotExitException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> handleUserNotExestException(UserNotExitException exception){
        System.out.println("test handle");
         Map<String,Object> result = new HashMap<>();
         result.put("id",exception.getId());
         result.put("message",exception.getMessage());
         return result;
    }
}
