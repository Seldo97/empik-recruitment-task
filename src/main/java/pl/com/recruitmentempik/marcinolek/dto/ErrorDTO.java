package pl.com.recruitmentempik.marcinolek.dto;

import org.springframework.http.HttpStatusCode;

public record ErrorDTO(HttpStatusCode statusCode, String cause) {
}
