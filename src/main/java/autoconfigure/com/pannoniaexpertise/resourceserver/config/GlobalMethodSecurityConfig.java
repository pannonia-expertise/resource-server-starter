package autoconfigure.com.pannoniaexpertise.resourceserver.config;

import com.pannoniaexpertise.resourceserver.security.EvaluationContextConfigurerAdapter;
import com.pannoniaexpertise.resourceserver.security.SecurityExpressionHandlerContent;
import com.pannoniaexpertise.resourceserver.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

import java.util.Optional;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Slf4j
public class GlobalMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private final PermissionService permissionService;

    private final EvaluationContextConfigurerAdapter evaluationContextConfigurerAdapter;

    public GlobalMethodSecurityConfig(final PermissionService permissionService,
                                      Optional<EvaluationContextConfigurerAdapter> configurerAdapter) {
        this.permissionService = permissionService;
        this.evaluationContextConfigurerAdapter = configurerAdapter.isPresent() ? configurerAdapter.get() : null;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new SecurityExpressionHandlerContent(permissionService, evaluationContextConfigurerAdapter);
    }
}
