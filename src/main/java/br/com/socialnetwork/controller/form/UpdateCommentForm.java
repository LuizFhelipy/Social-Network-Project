package br.com.socialnetwork.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.socialnetwork.model.Comment;
import br.com.socialnetwork.repository.CommentRepository;

public class UpdateCommentForm {
	
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

	public Comment update(Long id, CommentRepository commentRepository) {
		Comment comment = commentRepository.getOne(id);
		comment.setMessage(this.message);
		return comment;
	}
	
}
