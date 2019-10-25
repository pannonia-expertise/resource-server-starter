package com.pannoniaexpertise.resourceserver.security;

import com.pannoniaexpertise.resourceserver.service.PermissionService;

import lombok.AllArgsConstructor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.core.Authentication;

@AllArgsConstructor
public class SecurityExpressionHandlerContent extends DefaultMethodSecurityExpressionHandler {

    private final PermissionService permissionService;

    private final EvaluationContextConfigurerAdapter evaluationContextConfigurerAdapter;

    @Override
    public StandardEvaluationContext createEvaluationContextInternal(Authentication authentication, MethodInvocation mi) {
        StandardEvaluationContext ec = super.createEvaluationContextInternal(authentication, mi);
        this.setPermissionEvaluator(new CustomPermissionEvaluator(permissionService));
        if (evaluationContextConfigurerAdapter != null) {
            ec = evaluationContextConfigurerAdapter.configure(ec, authentication);
        }
        return ec;
    }
}
