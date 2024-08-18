package pl.com.recruitmentempik.marcinolek.service

import org.mapstruct.factory.Mappers
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserDTO
import pl.com.recruitmentempik.marcinolek.entity.UserRequestCount
import pl.com.recruitmentempik.marcinolek.feign.GitHubClient
import pl.com.recruitmentempik.marcinolek.mapper.GitHubUserMapper
import pl.com.recruitmentempik.marcinolek.repository.UserRequestCountRepository
import spock.lang.Specification

import java.time.ZonedDateTime

class GitHubUserServiceTests extends Specification {

    def userRequestCountRepository = Mock(UserRequestCountRepository)
    def gitHubClient = Mock(GitHubClient)
    def gitHubUserMapper = Mappers.getMapper(GitHubUserMapper.class)

    def gitHubUserService = new GitHubUserService(userRequestCountRepository, gitHubUserMapper, gitHubClient)

    def "should get GitHub user with calculations by login and increment request count"() {
        given:
        def gitHubUserDTO = getGitHubUserMockInstance()
        def login = gitHubUserDTO.login
        def userRequestCount = UserRequestCount.createNew(login)

        when:
        def result = gitHubUserService.getByLoginAndIncrementUserRequestCount(login)

        then:
        1 * gitHubClient.getUserByLogin(login) >> gitHubUserDTO
        1 * userRequestCountRepository.findByLoginIgnoreCase(login) >> Optional.of(userRequestCount)
        1 * userRequestCountRepository.save({ UserRequestCount entity ->
            {
                entity.login == login
                entity.requestCount == 2
            }
        })
        result.login == login
        result.calculations == new BigDecimal("0.004116073")
    }

    def "should create new request count if user does not exist"() {
        given:
        def gitHubUserDTO = getGitHubUserMockInstance()
        def login = gitHubUserDTO.login

        when:
        def result = gitHubUserService.getByLoginAndIncrementUserRequestCount(login)

        then:
        1 * gitHubClient.getUserByLogin(login) >> gitHubUserDTO
        1 * userRequestCountRepository.findByLoginIgnoreCase(login) >> Optional.empty()
        1 * userRequestCountRepository.save({ UserRequestCount entity ->
            {
                entity.login == login
                entity.requestCount == 1
            }
        })
    }

    def "should throw exception when GitHub client returns an error"() {
        given:
        def login = "invaliduser"

        when:
        gitHubClient.getUserByLogin(login) >> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found") }
        gitHubUserService.getByLoginAndIncrementUserRequestCount(login)

        then:
        thrown(ResponseStatusException)
        0 * userRequestCountRepository.save(_)
        0 * userRequestCountRepository.findByLoginIgnoreCase(_)
    }

    def "should throw exception for null login"() {
        when:
        gitHubUserService.getByLoginAndIncrementUserRequestCount(null)

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "Login must not be blank"
    }

    def "should throw exception for empty login"() {
        when:
        gitHubUserService.getByLoginAndIncrementUserRequestCount("")

        then:
        def ex = thrown(IllegalArgumentException)
        ex.message == "Login must not be blank"
    }


    private static GitHubUserDTO getGitHubUserMockInstance() {
        return GitHubUserDTO.newInstance(
                followers: 14577,
                publicRepos: 8,
                login: 'octocat',
                name: 'The Octocat',
                type: 'User',
                avatarUrl: 'https://avatars.githubusercontent.com/u/583231?v=4',
                createdAt: ZonedDateTime.parse('2011-01-25T18:44:36Z'),
                id: 583231L
        )
    }

}
