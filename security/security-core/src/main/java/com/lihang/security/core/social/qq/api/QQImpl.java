package com.lihang.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
     private static final String URL_GET_OPENID ="https://graph.qq.com/oauth2.0/me?access_token=%s";
     private String URL_GET_USERINFO ="https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
     private String appId;
     private String openId;
     private ObjectMapper objectMapper = new ObjectMapper();
     private Logger logger = LoggerFactory.getLogger(getClass());
     public QQImpl(String accessToken,String appId){
         super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
         this.appId = appId;
         String url = String.format(URL_GET_OPENID,accessToken);
         String result = getRestTemplate().getForObject(url,String.class);
         logger.info("获取的result中包含openId,result: "+result);
         this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
     }
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);

        logger.info("获取的QQUserInfo:"+result);
        QQUserInfo userInfo = null;
        try {
            userInfo = objectMapper.readValue(result,QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        }catch (Exception e){
            throw new RuntimeException("获取用户信息出错",e);
        }
    }
}
