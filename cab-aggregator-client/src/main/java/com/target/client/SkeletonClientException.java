package com.target.client;


import com.target.intf.ErrorDTO;

import java.util.Arrays;
import java.util.List;

/**
 * SkeletonClientException class.
 * @author Rohan Raju
 *
 */
public class SkeletonClientException extends Exception {

	private final int status;
	private final List<ErrorDTO> skeletonErrorDTOS;

	public SkeletonClientException(int status, List<ErrorDTO> skeletonErrorDTOS) {
		this.status = status;
		this.skeletonErrorDTOS = skeletonErrorDTOS;
	}

	public SkeletonClientException(int status, ErrorDTO errorDTO) {
		this.status = status;
		this.skeletonErrorDTOS = Arrays.asList(errorDTO);
	}

	public int getStatus() {
		return status;
	}

	public List<ErrorDTO> getSkeletonErrorDTOS() {
		return skeletonErrorDTOS;
	}
}
