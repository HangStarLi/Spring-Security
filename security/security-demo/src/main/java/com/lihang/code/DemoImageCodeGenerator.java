package com.lihang.code;

import com.lihang.security.core.validate.code.image.ImageCode;
import com.lihang.security.core.validate.code.ValidateCodeGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.ServletWebRequest;
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Override
    public ImageCode generate(ServletWebRequest request) {
        logger.info("更高级的验证码生成逻辑");
        return null;
    }
}
