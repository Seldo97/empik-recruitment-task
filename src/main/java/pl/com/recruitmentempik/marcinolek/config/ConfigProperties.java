package pl.com.recruitmentempik.marcinolek.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ConfigProperties {

    @Value("${server.port}")
    private int serverPort;

    @Value("${server.address}")
    private String serverAddress;

    @Value("${springdoc.swagger-ui.path:/swagger-ui.html}")
    private String swaggerPath;

    @Value("${spring.h2.console.path}")
    private String h2Console;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

}
