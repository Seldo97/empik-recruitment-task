package pl.com.recruitmentempik.marcinolek.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
public class GitHubClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        HttpStatus status = HttpStatus.valueOf(response.status());
        String reason = response.reason();
        log.error("GitHub api error. Method: {}, status: {}, reason: {}", s, status, reason);
        return new ResponseStatusException(status, "GitHub api error: " + reason);
    }

}
