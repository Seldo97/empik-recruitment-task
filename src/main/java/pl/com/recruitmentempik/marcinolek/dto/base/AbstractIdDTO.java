package pl.com.recruitmentempik.marcinolek.dto.base;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@EqualsAndHashCode
public class AbstractIdDTO<ID extends Serializable> {

    protected ID id;

}
