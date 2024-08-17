package pl.com.recruitmentempik.marcinolek.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.com.recruitmentempik.marcinolek.feign.decoder.GitHubClientErrorDecoder;

@Configuration
public class GitHubClientConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return new GitHubClientErrorDecoder();
    }

}
