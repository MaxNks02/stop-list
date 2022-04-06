package uz.davrbank.stoplist.dao.mapper;

import uz.davrbank.stoplist.dao.dto.BaseDto;
import uz.davrbank.stoplist.dao.model.BaseEntity;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDto> {
    D convertFromEntity(E entity);
    E convertFromDto(D dto);

    List<D> convertFromEntityList(List<E> entityList);
    List<E> convertFromDtoList(List<D> dtoList);
}
