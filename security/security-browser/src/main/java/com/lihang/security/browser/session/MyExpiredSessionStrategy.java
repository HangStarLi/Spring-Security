package com.lihang.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

public class MyExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public MyExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        //这个event中保存了request和response
        onSessionInvalid(sessionInformationExpiredEvent.getRequest(),sessionInformationExpiredEvent.getResponse());
        //sessionInformationExpiredEvent.getResponse().setContentType("application/json;charset=utf-8");
        //sessionInformationExpiredEvent.getResponse().getWriter().write("您的账号在异地登陆");
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
