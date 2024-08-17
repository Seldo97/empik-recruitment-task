package pl.com.recruitmentempik.marcinolek.dto;

import lombok.Getter;
import lombok.Setter;
import pl.com.recruitmentempik.marcinolek.dto.base.AbstractGitHubUserDTO;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Setter
@Getter
public class GitHubUserResponseDTO extends AbstractGitHubUserDTO {
    private BigDecimal calculations;
}
