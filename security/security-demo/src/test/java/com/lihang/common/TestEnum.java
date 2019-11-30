package com.lihang.common;

import com.lihang.security.core.validate.code.ValidateCodeType;
import org.junit.Test;

public class TestEnum {
    @Test
    public void testValueOf(){
        System.out.println(ValidateCodeType.valueOf("IMAGE").getParamNameOnValidate());
    }
}
