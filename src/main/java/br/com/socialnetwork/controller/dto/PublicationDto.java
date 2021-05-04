package br.com.socialnetwork.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.socialnetwork.model.Publication;

public class PublicationDto {
	private Long id;
	private String title;
	private String message;
	private LocalDateTime creationDate;
	
	public PublicationDto(Publication publication) {
		this.id = publication.getId();
		this.title = publication.getTitle();
		this.message = publication.getMessage();
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
	public LocalDateTime getCreationDate() {
		return creationDate;
	}


	public static List<PublicationDto> converter(List<Publication> publications) {
		return publications.stream().map(PublicationDto::new).collect(Collectors.toList());
	}
}
