package com.lihang.volidator;

import com.lihang.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    @Autowired
    HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {

        System.out.println("init my validator");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.hello(o+"");
        return false;
    }
}
