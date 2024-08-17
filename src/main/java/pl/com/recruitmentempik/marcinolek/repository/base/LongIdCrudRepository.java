package pl.com.recruitmentempik.marcinolek.repository.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import pl.com.recruitmentempik.marcinolek.entity.base.EntityLongId;

import java.util.Optional;

@NoRepositoryBean
public interface LongIdCrudRepository<T extends EntityLongId> extends Repository<T, Long> {

    <S extends T> S save(S entity);

    void deleteById(Long id);

    Optional<T> findById(Long id);

    Page<T> findAll(Pageable pageable);

}
