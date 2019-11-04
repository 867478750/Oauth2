package org.nlb.security.social.impl;

import org.apache.commons.lang.StringUtils;
import org.nlb.security.entity.QQInfo;
import org.nlb.security.social.QQ;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    private static final String getInfoUrl = "https://graph.qq.com/user/get_user_info";
    private static final String getOpenId = "https://graph.qq.com/oauth2.0/me";
    private String appId;
    private String openId;

    public QQImpl(String openId, String accessToken) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.openId = openId;
        String url = getOpenId + "access_token=" + accessToken;
        String result = getRestTemplate().getForObject(url, String.class);
        System.out.println(result);
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }

    @Override
    public QQInfo getQQInfo() {
        return null;
    }
}


