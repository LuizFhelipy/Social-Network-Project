package br.com.socialnetwork.controller.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.socialnetwork.model.Publication;

public class DetailsPublicationDto  {
	
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	private String author;
	private List<CommentDto> comments;
	
	public DetailsPublicationDto(Publication publication) {
		this.id = publication.getId();
		this.title = publication.getTitle();
		this.message = publication.getMessage();
		this.creationDate = publication.getCreationDate();
		this.author = publication.getAuthor().getName();
		this.comments = new ArrayList<>();
		this.comments.addAll(publication.getComments().stream().map(CommentDto::new).collect(Collectors.toList()));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
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

	public List<CommentDto> getComments() {
		return comments;
	}
	
}
