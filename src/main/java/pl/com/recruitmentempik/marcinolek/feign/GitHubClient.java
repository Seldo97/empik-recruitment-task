package pl.com.recruitmentempik.marcinolek.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.com.recruitmentempik.marcinolek.config.GitHubClientConfig;
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserDTO;

@FeignClient(name = "githubClient", url = "https://api.github.com", configuration = GitHubClientConfig.class)
public interface GitHubClient {

    @GetMapping("/users/{login}")
    GitHubUserDTO getUserByLogin(@PathVariable String login);

}
