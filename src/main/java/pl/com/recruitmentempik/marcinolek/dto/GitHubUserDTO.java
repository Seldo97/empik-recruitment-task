package pl.com.recruitmentempik.marcinolek.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import pl.com.recruitmentempik.marcinolek.dto.base.AbstractGitHubUserDTO;

@Setter
@Getter
public class GitHubUserDTO extends AbstractGitHubUserDTO {
    private Integer followers;
    @JsonAlias("public_repos")
    private Integer publicRepos;
}
