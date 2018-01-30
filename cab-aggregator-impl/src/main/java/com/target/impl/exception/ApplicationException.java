package com.target.impl.exception;

import com.target.intf.ErrorDTO;

import java.util.Arrays;
import java.util.List;

public class ApplicationException extends RuntimeException {
	private final List<ErrorDTO> errorDTOS;

	public ApplicationException(List<ErrorDTO> errorDTOS) {
		super(errorDTOS.stream().map(ErrorDTO::getErrorCode).reduce("", (a, b) -> a + " " + b));
		this.errorDTOS = errorDTOS;
	}

	public ApplicationException(ErrorDTO skeletonErrorDTO) {
		super(skeletonErrorDTO.getErrorCode());
		this.errorDTOS = Arrays.asList(skeletonErrorDTO);
	}

	public List<ErrorDTO> getErrorDTOS() {
		return errorDTOS;
	}

}
