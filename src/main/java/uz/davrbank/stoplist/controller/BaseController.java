package uz.davrbank.stoplist.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import uz.davrbank.stoplist.service.BaseService;

@SuppressWarnings("ALL")
@CrossOrigin
public abstract class BaseController<S extends BaseService> {
    public static final String API_PATH = "/api";
    public static final String V_1 = "/v1";
    public static final String SL = API_PATH + V_1 + "/sl";

    public static final String FILE_UPLOAD = SL + "/file-upload";

    protected S service;

    public BaseController(S service) {
        this.service = service;
    }
}
