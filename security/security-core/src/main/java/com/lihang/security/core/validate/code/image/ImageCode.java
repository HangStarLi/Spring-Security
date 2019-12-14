package com.lihang.security.core.validate.code.image;

import com.lihang.security.core.validate.code.ValidateCode;

import java.awt.image.BufferedImage;

public class ImageCode extends ValidateCode {
        private BufferedImage image;

        public ImageCode(BufferedImage image, String code, Integer expireTime) {
            super(code,expireTime);
            this.image = image;
        }






    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }



}
