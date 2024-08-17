package pl.com.recruitmentempik.marcinolek.entity;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.com.recruitmentempik.marcinolek.entity.base.AbstractEntity;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class UserRequestCount extends AbstractEntity {

    private String login;
    private Integer requestCount;

    private UserRequestCount(String login) {
        this.login = login;
        this.requestCount = 1;
    }

    public static UserRequestCount createNew(@Nonnull String login) {
        return new UserRequestCount(login);
    }

}
