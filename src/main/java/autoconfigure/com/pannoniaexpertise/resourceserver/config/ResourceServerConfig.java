package autoconfigure.com.pannoniaexpertise.resourceserver.config;

import com.pannoniaexpertise.resourceserver.security.ResourceServerHttpSecurityAdapter;
import com.pannoniaexpertise.resourceserver.service.CustomRemoteTokenService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableResourceServer
@EnableWebSecurity
@AllArgsConstructor
@Slf4j
@EnableConfigurationProperties(autoconfigure.com.pannoniaexpertise.resourceserver.config.ResourceServerProperties.class)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final ResourceServerProperties resourceServerProperties;

    private DataSource dataSource;

    private ResourceServerHttpSecurityAdapter httpSecurityConfigAdapter;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        /* Configure security from adapter */
        httpSecurityConfigAdapter.configure(http);
    }

    @Bean
    @ConditionalOnMissingBean
    public RemoteTokenServices tokenService() {
        final RemoteTokenServices tokenService = new CustomRemoteTokenService();
        tokenService.setCheckTokenEndpointUrl(resourceServerProperties.getCheckTokenUrl());
        tokenService.setClientId(resourceServerProperties.getClientId());
        tokenService.setClientSecret(resourceServerProperties.getClientSecret());
        return tokenService;
    }

    @Bean
    @ConditionalOnMissingBean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

}
