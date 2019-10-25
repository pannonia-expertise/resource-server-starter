package com.pannoniaexpertise.resourceserver.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface ResourceServerHttpSecurityAdapter {

    void configure(final HttpSecurity http) throws Exception;
}
