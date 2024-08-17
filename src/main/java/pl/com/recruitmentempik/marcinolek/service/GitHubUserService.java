package pl.com.recruitmentempik.marcinolek.service;

import jakarta.annotation.Nonnull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserDTO;
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserResponseDTO;
import pl.com.recruitmentempik.marcinolek.entity.UserRequestCount;
import pl.com.recruitmentempik.marcinolek.feign.GitHubClient;
import pl.com.recruitmentempik.marcinolek.mapper.GitHubUserMapper;
import pl.com.recruitmentempik.marcinolek.repository.UserRequestCountRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class GitHubUserService {

    private final UserRequestCountRepository userRequestCountRepository;
    private final GitHubUserMapper gitHubUserMapper;
    private final GitHubClient gitHubClient;

    @Transactional
    public GitHubUserResponseDTO getByLoginAndIncrementUserRequestCount(@Nonnull String login) {
        log.info("Get a GitHub user by login [{}]", login);
        GitHubUserDTO gitHubUserDTO = gitHubClient.getUserByLogin(login);
        incrementUserRequestCountAndSave(login);
        return gitHubUserMapper.mapToResponseDTO(gitHubUserDTO);
    }

    private UserRequestCount incrementUserRequestCountAndSave(String login) {
        Optional<UserRequestCount> opUser = userRequestCountRepository.findByLoginIgnoreCase(login);
        if (opUser.isPresent()) {
            return incrementUserRequestCount(opUser.get());
        } else {
            return createUserRequestCount(login);
        }
    }

    private UserRequestCount createUserRequestCount(String login) {
        log.info("Create new request count for login [{}]", login);
        return userRequestCountRepository.save(UserRequestCount.createNew(login));
    }

    private UserRequestCount incrementUserRequestCount(UserRequestCount entity) {
        log.info("Increment request count for login [{}], current value [{}]", entity.getLogin(), entity.getRequestCount());
        entity.setRequestCount(entity.getRequestCount() + 1);
        return userRequestCountRepository.save(entity);
    }

}
