package com.target.service;

import com.target.impl.error.ErrorEnum;
import com.target.impl.exception.ApplicationException;
import com.target.impl.exception.BusinessException;
import com.target.impl.exception.SecurityException;
import com.target.intf.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    List<ErrorDTO> handleBusinessExceptionException(HttpServletRequest request, HttpServletResponse response, BusinessException ex) {
        logger.info("method=handleBusinessExceptionException and exception ={}", ex.getErrorDTOS());
        return ((BusinessException) ex).getErrorDTOS();
    }

    @ExceptionHandler(ApplicationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    List<ErrorDTO> handleApplicationExceptionException(HttpServletRequest request, HttpServletResponse response, ApplicationException ex) {
        logger.error("method=handleApplicationExceptionException and exception", ex);
        return ex.getErrorDTOS();
    }

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
    public @ResponseBody
    List<ErrorDTO> handleSecurityExceptionException(HttpServletRequest request, HttpServletResponse response, SecurityException ex) {
        logger.error("method=handleApplicationExceptionException and exception", ex);
        return ex.getErrorDTOS();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    List<ErrorDTO> handleException(HttpServletRequest request, HttpServletResponse response, Exception ex) {
        logger.error("method=handleException and exception", ex);
        return Arrays.asList(new ErrorDTO(ErrorEnum.GENERIC_ERROR.name(), ErrorEnum.GENERIC_ERROR.getErrorMessage()));
    }

}
