package br.com.socialnetwork.controller.dto;

import java.time.LocalDateTime;

import br.com.socialnetwork.model.Comment;

public class CommentDto {
	
	private Long id;
	private String message;
	private LocalDateTime creationDate;
	private String author;
	
	public CommentDto(Comment comment) {
		this.id = comment.getId();
		this.message = comment.getMessage();
		this.creationDate = comment.getCreationDate();
		this.author = comment.getAuthor().getName();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public String getAuthor() {
		return author;
	}
	
}
