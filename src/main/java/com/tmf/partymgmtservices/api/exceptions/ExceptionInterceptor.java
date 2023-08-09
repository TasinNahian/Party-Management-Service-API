package com.tmf.partymgmtservices.api.exceptions;

import com.tmf.partymgmtservices.api.util.SessionRequestUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class ExceptionInterceptor extends ResponseEntityExceptionHandler {


    private static final Logger LOG = LogManager.getLogger(ExceptionInterceptor.class);

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ResponseError> missingParameters(RuntimeException rte) {
        LOG.info("Call missingParameters %s", SessionRequestUtil.getSessionRequest());

        ResponseError responseError = new ResponseError();
        responseError.setReason("Mandatory attribute's value is missing");
        responseError.setMessage(rte.getMessage());
        responseError.setCode(HttpStatus.BAD_REQUEST.toString());
        responseError.setReferenceError("Please provide mandatory fields in request");

        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ResponseError> itemsNotFound(RuntimeException rte) {
        LOG.info("Call itemsNotFound %s", SessionRequestUtil.getSessionRequest());

        ResponseError responseError = new ResponseError();
        responseError.setReason("Data is not present in the database!");
        responseError.setMessage(rte.getMessage());
        responseError.setCode(HttpStatus.NOT_FOUND.toString());
        responseError.setReferenceError("Please provide correct information in the request");

        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }
}

