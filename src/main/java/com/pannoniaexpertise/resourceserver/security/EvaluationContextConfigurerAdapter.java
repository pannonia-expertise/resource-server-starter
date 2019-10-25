package com.pannoniaexpertise.resourceserver.security;

import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.security.core.Authentication;

public interface EvaluationContextConfigurerAdapter {

    StandardEvaluationContext configure(StandardEvaluationContext standardEvaluationContext, Authentication auth);
}
