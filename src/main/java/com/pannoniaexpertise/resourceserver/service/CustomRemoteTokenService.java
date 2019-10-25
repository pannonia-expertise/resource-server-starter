package com.pannoniaexpertise.resourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

@Service
public class CustomRemoteTokenService extends RemoteTokenServices {

    @Autowired
    TokenStore tokenStore;

    @Override
    public OAuth2AccessToken readAccessToken(final String accessToken) {
        return tokenStore.readAccessToken(accessToken);
    }
}
