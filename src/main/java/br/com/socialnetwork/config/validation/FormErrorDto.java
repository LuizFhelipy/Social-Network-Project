package br.com.socialnetwork.config.validation;

public class FormErrorDto {

	private String field;
	private String error;

	public FormErrorDto(String field, String error) {
		super();
		this.field = field;
		this.error = error;
	}

	public String getField() {
		return field;
	}

	public String getError() {
		return error;
	}

}
