package com.target.service;

import com.target.impl.error.ErrorEnum;
import com.target.impl.exception.BusinessException;
import com.target.intf.ErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseController {

    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    protected void checkForValidityOfRequest(BindingResult result) throws BusinessException {
        if (result.hasErrors() && !CollectionUtils.isEmpty(result.getFieldErrors())) {
            logger.info("method=checkForValidityOfRequest rejecting the request because of field error");
            List<ErrorDTO> skeletonErrorDTOS = result.getFieldErrors()
                    .stream()
                    .map(x -> ErrorEnum.getErrorEnumForErrorCode(x.getDefaultMessage()))
                    .map(x -> new ErrorDTO(x.name(), x.getErrorMessage()))
                    .collect(Collectors.toList());
            throw new BusinessException(skeletonErrorDTOS);
        }
    }
}
