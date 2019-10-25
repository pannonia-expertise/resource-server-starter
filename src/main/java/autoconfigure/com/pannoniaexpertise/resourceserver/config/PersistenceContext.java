package autoconfigure.com.pannoniaexpertise.resourceserver.config;

import com.pannoniaexpertise.resourceserver.repository.PermissionsRepository;
import com.pannoniaexpertise.resourceserver.security.ResourceServerHttpSecurityAdapter;
import com.pannoniaexpertise.resourceserver.service.PermissionService;
import com.pannoniaexpertise.resourceserver.service.PermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class PersistenceContext {

    @Bean
    @ConditionalOnMissingBean
    public PermissionService permissionService(final PermissionsRepository repository) {
        return new PermissionServiceImpl(repository);
    }

    @Bean
    @ConditionalOnMissingBean
    public ResourceServerHttpSecurityAdapter defaultConfig() {
        return http -> {
            /* Use default security configuration */
            http.cors()
                    .and()
                    .csrf()
                    .disable()
                    .authorizeRequests()
                    .anyRequest()
                    .permitAll();
            log.warn("No ResourceServerHttpSecurityAdapter bean found. Permitting all by default.");
        };
    }
}
