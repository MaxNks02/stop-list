package uz.davrbank.stoplist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.davrbank.stoplist.dao.dto.StopListDto;
import uz.davrbank.stoplist.exception.BadRequestException;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;
import uz.davrbank.stoplist.service.StopListService;
import uz.davrbank.stoplist.utils.ExcelHelper;

import java.util.Objects;

import static uz.davrbank.stoplist.controller.BaseController.SL;

@RequestMapping(value = SL)
@RestController
public class StopListController extends BaseController<StopListService> {

    public StopListController(StopListService service) {
        super(service);
    }

    @PostMapping(value = FILE_UPLOAD)
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile files) {
        if (!files.isEmpty()) {
            String fileType = Objects.requireNonNull(files.getOriginalFilename()).substring(files.getOriginalFilename().length() - 4);
            if (fileType.equals(ExcelHelper.mimeType)) {
                return service.uploadFile(files);
            }
            throw new BadRequestException(String.format(ApiErrorMessages.BAD_REQUEST + "%s", "File type must be \"xlsx\"!"));
        }
        throw new BadRequestException(String.format(ApiErrorMessages.BAD_REQUEST + "%s", "File cannot be null!"));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody StopListDto dto) {
        return ResponseEntity.ok(service.getMapper().convertFromEntity(service.create(dto)));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {
        return ResponseEntity.ok(service.getAllWithPagination(page, size));
    }
}
