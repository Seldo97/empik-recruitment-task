package pl.com.recruitmentempik.marcinolek.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserDTO;
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserResponseDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Mapper(componentModel = "spring")
public abstract class GitHubUserMapper {

    @Mapping(target = "calculations", source = "dto", qualifiedByName = "toCalculations")
    public abstract GitHubUserResponseDTO mapToResponseDTO(GitHubUserDTO dto);

    @Named("toCalculations")
    protected BigDecimal toCalculations(GitHubUserDTO dto) {
        if (dto.getFollowers().equals(0)) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(6)
                .divide(BigDecimal.valueOf(dto.getFollowers()), 10, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(2).add(BigDecimal.valueOf(dto.getPublicRepos())));
    }

}
