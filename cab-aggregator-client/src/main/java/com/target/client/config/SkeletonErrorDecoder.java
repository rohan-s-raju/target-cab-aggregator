package com.target.client.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.target.client.SkeletonClientException;
import com.target.intf.ErrorDTO;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class SkeletonErrorDecoder implements ErrorDecoder {

	public Exception decode(String methodKey, Response response) {

		if (response.status() >= 400 && response.status() <= 499) {
			return new SkeletonClientException(response.status(), getRestGroupManagementErrorDTO(response));
		}
		if (response.status() >= 500 && response.status() <= 599) {
			return new SkeletonClientException(response.status(), new ErrorDTO("500", "Internal Server ErrorEnum"));
		}
		return FeignException.errorStatus(methodKey, response);
	}

	private List<ErrorDTO> getRestGroupManagementErrorDTO(Response response) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String error = IOUtils.toString(response.body().asInputStream(), "UTF-8");
			return objectMapper.readValue(error, new TypeReference<List<ErrorDTO>>() {
			});
		}
		catch (IOException e) {
			return Arrays.asList(new ErrorDTO("500", "Internal Server ErrorEnum"));
		}
	}
}
