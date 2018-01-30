

package com.target.intf;


/**
 * ErrorDTO class.
 * @author Rohan Raju
 */
public class ErrorDTO {

	private String errorCode;
	private String errorMessage;

	public ErrorDTO() {
	}

	public ErrorDTO(String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ErrorDTO{" + "errorCode='" + errorCode + '\'' + ", errorMessage='" + errorMessage + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ErrorDTO that = (ErrorDTO) o;

		if (errorCode != null ? !errorCode.equals(that.errorCode) : that.errorCode != null) {
			return false;
		}
		return errorMessage != null ? errorMessage.equals(that.errorMessage) : that.errorMessage == null;
	}

	@Override
	public int hashCode() {
		int result = errorCode != null ? errorCode.hashCode() : 0;
		result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
		return result;
	}
}
