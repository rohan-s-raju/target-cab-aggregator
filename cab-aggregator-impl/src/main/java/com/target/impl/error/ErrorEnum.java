package com.target.impl.error;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;


public enum ErrorEnum {

	GENERIC_ERROR("Internal Server Error"),
	TEST_CODE_IS_NOT_NULL("test code is not null"),
	TEST_NAME_IS_NOT_NULL("test name is not null"),
	TEAM_MEMBER_IS_NOT_NULL("team_member_is_not_null"),
	GENDER_IS_NOT_NULL("gender_is_not_null"),
	DROP_POINT_NOT_FOUND("drop_point_not_found"),
	DROP_POINT_IS_NOT_NULL("drop_point_is_not_null"),
	DROP_POINT_HEAD_QUARTER_IS_NOT_PRESENT("drop_point_head_quarter_is_not_present"),
	DROP_POINT_INPUT_DATA_IS_NOT_VALID("drop_point_input_data_is_not_valid"),
	DROP_POINT_INPUT_DATA_IS_NOT_VALID_AT("drop_point_input_data_is_not_valid_at %s"),
	CAB_CAPACITY_DID_NOT_MATCH("cab_capacity_did_not_match");

	private String errorMessage;

	ErrorEnum(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	private final static Map<String, ErrorEnum> errorCodeToErrorEnumMapping = Arrays.stream(ErrorEnum.values()).collect(Collectors.toConcurrentMap(x -> x.name(), x -> x));

	public static ErrorEnum getErrorEnumForErrorCode(String errorCode) {
		return errorCodeToErrorEnumMapping.getOrDefault(errorCode, GENERIC_ERROR);
	}

	public String getErrorMessage() {
		return errorMessage;
	}

}
