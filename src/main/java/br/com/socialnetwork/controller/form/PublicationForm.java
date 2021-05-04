package br.com.socialnetwork.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.socialnetwork.model.Publication;

public class PublicationForm {

	@NotNull
	@NotEmpty
	private String title;
	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String message;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Publication converter() {
		return new Publication(title, message);
	}
}
