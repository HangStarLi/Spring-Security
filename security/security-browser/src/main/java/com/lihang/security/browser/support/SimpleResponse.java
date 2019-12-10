package com.lihang.security.browser.support;

/**
 * 在MyAuthenticationFailureHandler中使用了，只是打印简单的错误信息
 * 因为objectMapper.writeValueAsString必须是将对象转换为json
 * 所以给错误信息封装成对象
 */
public class SimpleResponse {
    private Object content;

    public SimpleResponse() {
    }

    public SimpleResponse(String content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
