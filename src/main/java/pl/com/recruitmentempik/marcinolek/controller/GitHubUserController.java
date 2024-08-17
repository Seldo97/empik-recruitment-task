package pl.com.recruitmentempik.marcinolek.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.recruitmentempik.marcinolek.dto.GitHubUserResponseDTO;
import pl.com.recruitmentempik.marcinolek.service.GitHubUserService;

@RestController
@RequestMapping("github-users")
@AllArgsConstructor
public class GitHubUserController {

    private final GitHubUserService gitHubUserService;

    @Operation(
            summary = "Get a GitHub user by login",
            description = "Returns a user when the login is valid and increments the login request count in the database."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Resource not found"),
            @ApiResponse(responseCode = "403", description = "Forbidden (Rate limit exceeded)")
    })
    @GetMapping("/{login}")
    public ResponseEntity<GitHubUserResponseDTO> getGitHubUserByLogin(@PathVariable String login) {
        GitHubUserResponseDTO result = gitHubUserService.getByLoginAndIncrementUserRequestCount(login);
        return ResponseEntity.ok(result);
    }

}
