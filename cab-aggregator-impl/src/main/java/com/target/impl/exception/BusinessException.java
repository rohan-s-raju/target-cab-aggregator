package com.target.impl.exception;

import com.target.intf.ErrorDTO;

import java.util.Arrays;
import java.util.List;

public class BusinessException extends RuntimeException {

	private final List<ErrorDTO> errorDTOS;

	public BusinessException(List<ErrorDTO> errorDTOS) {
		super(errorDTOS.stream().map(ErrorDTO::getErrorCode).reduce("", (a, b) -> a + " " + b));
		this.errorDTOS = errorDTOS;
	}

	public BusinessException(ErrorDTO groupManagementErrorDTO) {
		super(groupManagementErrorDTO.getErrorCode());
		this.errorDTOS = Arrays.asList(groupManagementErrorDTO);
	}

	public List<ErrorDTO> getErrorDTOS() {
		return errorDTOS;
	}
}
