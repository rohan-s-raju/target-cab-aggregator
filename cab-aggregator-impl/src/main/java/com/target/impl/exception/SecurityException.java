package com.target.impl.exception;


import com.target.intf.ErrorDTO;

import java.util.Arrays;
import java.util.List;

public class SecurityException extends RuntimeException {

	private final List<ErrorDTO> errorDTOS;

	public SecurityException(List<ErrorDTO> errorDTOS) {
		super(errorDTOS.stream().map(ErrorDTO::getErrorCode).reduce("", (a, b) -> a + " " + b));
		this.errorDTOS = errorDTOS;
	}

	public SecurityException(ErrorDTO groupManagementErrorDTO) {
		super(groupManagementErrorDTO.getErrorCode());
		this.errorDTOS = Arrays.asList(groupManagementErrorDTO);
	}

	public List<ErrorDTO> getErrorDTOS() {
		return errorDTOS;
	}
}
