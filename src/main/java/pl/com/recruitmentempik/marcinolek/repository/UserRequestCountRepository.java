package pl.com.recruitmentempik.marcinolek.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import pl.com.recruitmentempik.marcinolek.entity.UserRequestCount;
import pl.com.recruitmentempik.marcinolek.repository.base.LongIdCrudRepository;

import java.util.Optional;

@Repository
public interface UserRequestCountRepository extends LongIdCrudRepository<UserRequestCount> {

    Optional<UserRequestCount> findByLoginIgnoreCase(@NonNull String login);

}
