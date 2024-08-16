package pl.com.recruitmentempik.marcinolek;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.com.recruitmentempik.marcinolek.config.ConfigProperties;

@Slf4j
@SpringBootApplication
public class MarcinolekApplication {

	private final ConfigProperties configProperties;

	public MarcinolekApplication(ConfigProperties configProperties) {
		this.configProperties = configProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(MarcinolekApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner() {
		return args -> {
			String host =  configProperties.getServerAddress() + ":" + configProperties.getServerPort();

            String sb = "\n----------------------------------------------------------\n" +
                    "APPLICATION IS UP AND RUNNING!\n" +
                    "----------------------------------------------------------\n" +
                    "Local Address: \t\t\t" + host + "\n" +
                    "Swagger UI: \t\t\t" + host + configProperties.getSwaggerPath() + "\n" +
                    "H2 db console: \t\t\t" + host + configProperties.getH2Console() + "\n" +
                    "JDBC url: \t\t\t\t" + configProperties.getJdbcUrl() + "\n" +
                    "----------------------------------------------------------\n";

			log.info(sb);
		};
	}

}
