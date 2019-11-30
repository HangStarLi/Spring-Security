package com.lihang.code;

import com.lihang.security.core.validate.code.image.ImageCode;
import com.lihang.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.web.context.request.ServletWebRequest;
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的验证码生成逻辑");
        return null;
    }
}
