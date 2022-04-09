package uz.davrbank.stoplist.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import uz.davrbank.stoplist.dao.dto.BaseDto;
import uz.davrbank.stoplist.dao.mapper.BaseMapper;
import uz.davrbank.stoplist.dao.model.BaseEntity;
import uz.davrbank.stoplist.dao.repo.BaseRepo;
import uz.davrbank.stoplist.exception.CustomNotFoundException;
import uz.davrbank.stoplist.exception.DatabaseException;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<R extends BaseRepo<E>, E extends BaseEntity, D extends BaseDto, M extends BaseMapper<E, D>> {
    private final R repository;
    private final M mapper;

    public BaseService(R repository, M mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public R getRepository() {
        return repository;
    }

    public M getMapper() {
        return mapper;
    }

    public List<D> getAll() {
        List<E> entityList = repository.findAll();
        if (entityList.isEmpty())
            throw new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + " %s", "List empty!"));
        return mapper.convertFromEntityList(entityList);
    }

    public D getById(Long id) {
        E entity = repository.findById(id).orElseThrow(() -> new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + "%s Id: %s", "Data not found.", id)));
        return mapper.convertFromEntity(entity);
    }

    public E create(D dto) {
        try {
            return repository.save(mapper.convertFromDto(dto));
        } catch (RuntimeException exception) {
            throw new DatabaseException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + " %s", exception.getMessage()));
        }
    }

    public List<E> creatAll(List<D> dtoList) {
        try {
            return repository.saveAll(mapper.convertFromDtoList(dtoList));
        } catch (RuntimeException exception) {
            throw new DatabaseException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + " %s", exception.getMessage()));
        }
    }

    public Map<String, Object> getAllWithPagination(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        Page<D> list;

        try {
            list = repository.findAll(pageable).map(mapper::convertFromEntity);
        }catch (RuntimeException exception){
            throw new DatabaseException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + " %s", exception.getMessage()));
        }

        if (list.isEmpty())
            throw new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + " %s", "List empty!"));

        Map<String, Object> data = new HashMap<>();
        data.put("elements", list.getContent());
        data.put("current_page", list.getNumber());
        data.put("total_elements", list.getTotalElements());
        data.put("total_pages", list.getTotalPages());
        data.put("size", list.getSize());

        return data;
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + " %s", "List empty!"));
        }
        repository.deleteById(id);
    }

    public abstract D update(E entity, Long id);
}
