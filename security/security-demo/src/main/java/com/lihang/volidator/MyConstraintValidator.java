package com.lihang.volidator;

import com.lihang.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MyConstraintValidator implements ConstraintValidator<MyConstraint,Object> {
    @Autowired
    HelloService helloService;
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public void initialize(MyConstraint myConstraint) {
        logger.info("init my validator");
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        helloService.hello(o+"");
        return false;
    }
}
