package pl.com.recruitmentempik.marcinolek.dto.base;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Setter
@Getter
public abstract class AbstractGitHubUserDTO extends AbstractIdDTO<Long> {

    protected String login;
    protected String name;
    protected String type;
    @JsonAlias("avatar_url")
    protected String avatarUrl;
    @JsonAlias("created_at")
    protected ZonedDateTime createdAt;

}
