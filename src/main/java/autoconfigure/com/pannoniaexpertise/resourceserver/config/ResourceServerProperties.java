package autoconfigure.com.pannoniaexpertise.resourceserver.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ConfigurationProperties(prefix = "resource-server")
public class ResourceServerProperties {

    @NotNull
    private String checkTokenUrl;

    @NotNull
    private String clientId;

    @NotNull
    private String clientSecret;
}
