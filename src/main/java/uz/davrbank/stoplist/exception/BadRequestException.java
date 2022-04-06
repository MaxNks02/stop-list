package uz.davrbank.stoplist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import uz.davrbank.stoplist.exception.handler.ApiErrorMessages;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ApiErrorMessages.BAD_REQUEST)
public class BadRequestException extends BaseException {

    public BadRequestException() {
        this(ApiErrorMessages.BAD_REQUEST);
    }

    public BadRequestException(String s) {
        super(s);
    }
}
