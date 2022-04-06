package uz.davrbank.stoplist.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.davrbank.stoplist.dao.dto.StopListDto;
import uz.davrbank.stoplist.dao.mapper.StopListMapper;
import uz.davrbank.stoplist.utils.ExcelHelper;
import uz.davrbank.stoplist.dao.model.StopListEntity;
import uz.davrbank.stoplist.dao.repo.StopListRepo;
import uz.davrbank.stoplist.exception.DatabaseException;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

import java.util.LinkedList;
import java.util.List;

@Service
public class StopListService extends BaseService<StopListRepo, StopListEntity, StopListDto, StopListMapper>{
    private final StopListRepo repo;
    private final ExcelHelper excelHelper;

    public StopListService(StopListRepo repository, StopListMapper mapper, StopListRepo repo, ExcelHelper excelHelper) {
        super(repository, mapper);
        this.repo = repo;
        this.excelHelper = excelHelper;
    }


    @Transactional
    public ResponseEntity<?> uploadFile(MultipartFile files) {
        List<StopListEntity> list = excelHelper.excelToList(files);
        List<StopListEntity> savedList = new LinkedList<>();
        try {
            if (repo.count() != 0)
                repo.deleteAll();
            savedList = repo.saveAll(list);
        } catch (RuntimeException exception) {
            throw new DatabaseException(String.format(ApiErrorMessages.INTERNAL_SERVER_ERROR + "%s", exception.getMessage()));
        }
        return ResponseEntity.ok(savedList);
    }

    @Override
    public StopListDto update(StopListEntity entity, Long id) {
        return null;
    }
}
