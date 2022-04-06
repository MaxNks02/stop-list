package uz.davrbank.stoplist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = ApiErrorMessages.INTERNAL_SERVER_ERROR)
public class FileException extends BaseException{
    public FileException() {
        this(ApiErrorMessages.INTERNAL_SERVER_ERROR);
    }

    public FileException(String s) {
        super(s);
    }
}
