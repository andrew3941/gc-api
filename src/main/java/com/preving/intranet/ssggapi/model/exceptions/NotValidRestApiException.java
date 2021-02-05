package com.preving.intranet.ssggapi.model.exceptions;

import com.preving.intranet.ssggapi.model.exceptions.errors.RestApiErrorCode;
import com.preving.intranet.ssggapi.model.exceptions.errors.RestApiErrorDetail;
import org.springframework.http.HttpStatus;

import java.util.List;

public class NotValidRestApiException extends CustomRestApiException {


    public NotValidRestApiException(RestApiErrorCode code) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code);
    }

    public NotValidRestApiException(RestApiErrorCode code, String customMessage) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, customMessage);
    }

    public NotValidRestApiException(RestApiErrorCode code, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, errorsDetail);
    }

    public NotValidRestApiException(RestApiErrorCode code, String customMessage, List<RestApiErrorDetail> errorsDetail) {
        super(HttpStatus.UNPROCESSABLE_ENTITY, code, customMessage, errorsDetail);
    }
}
