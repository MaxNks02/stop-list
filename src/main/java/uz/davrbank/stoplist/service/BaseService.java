package uz.davrbank.stoplist.service;

import uz.davrbank.stoplist.dao.dto.BaseDto;
import uz.davrbank.stoplist.dao.mapper.BaseMapper;
import uz.davrbank.stoplist.dao.model.BaseEntity;
import uz.davrbank.stoplist.dao.repo.BaseRepo;
import uz.davrbank.stoplist.exception.CustomNotFoundException;
import uz.davrbank.stoplist.exception.DatabaseException;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

import java.util.List;

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

    public List<E> getAll() {
        List<E> entityList = repository.findAll();
        if (entityList.isEmpty()) {
            throw new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + " %s", "List empty!"));
        }
        return entityList;
    }

    public E getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + "%s Id: %s", "Data not found.", id)));
    }

    public E create(E entity) {
        try {
            return repository.save(entity);
        } catch (RuntimeException exception) {
            throw new DatabaseException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + " %s", exception.getMessage()));
        }
    }

    public List<E> creatAll(List<E> entityList) {
        try {
            return repository.saveAll(entityList);
        } catch (RuntimeException exception) {
            throw new DatabaseException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + " %s", exception.getMessage()));
        }
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new CustomNotFoundException(String.format(ApiErrorMessages.NOT_FOUND + " %s", "List empty!"));
        }
        repository.deleteById(id);
    }

    public abstract D update(E entity, Long id);
}
