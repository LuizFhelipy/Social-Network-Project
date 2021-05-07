package br.com.socialnetwork.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.socialnetwork.model.Publication;

public class PublicationDto {
	private Long id;
	private String title;
	private String message;
	private String media;
	private LocalDateTime creationDate;
	
	public PublicationDto(Publication publication) {
		this.id = publication.getId();
		this.title = publication.getTitle();
		this.message = publication.getMessage();
		this.media = publication.getMedia();
		this.creationDate = publication.getCreationDate();
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
	public String getMedia() {
		return media;
	}
	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public static Page<PublicationDto> converter(Page<Publication> publications) {
		return publications.map(PublicationDto::new);
	}
}
