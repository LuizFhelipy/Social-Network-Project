package br.com.socialnetwork.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.socialnetwork.model.Comment;
import br.com.socialnetwork.model.Publication;
import br.com.socialnetwork.model.User;

public class CommentForm {

	@NotNull
	@NotEmpty
	@Length(min = 5)
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Comment converter(Publication publication, User user) {
		
		return new Comment(message,publication,user);
		
	}
	
}
