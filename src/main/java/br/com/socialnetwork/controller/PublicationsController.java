package br.com.socialnetwork.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.socialnetwork.controller.dto.DetailsPublicationDto;
import br.com.socialnetwork.controller.dto.PublicationDto;
import br.com.socialnetwork.controller.form.PublicationForm;
import br.com.socialnetwork.model.Publication;
import br.com.socialnetwork.repository.PublicationRepository;

@RestController
@RequestMapping("/publications")
public class PublicationsController {

	@Autowired
	private PublicationRepository publicationRepository;

	@GetMapping
	public List<PublicationDto> list(String author) {
		if (author == null) {
			List<Publication> publications = publicationRepository.findAll();
			return PublicationDto.converter(publications);
		} else {
			List<Publication> publications = publicationRepository.findByAuthorName(author);
			return PublicationDto.converter(publications);
		}

	}

	@PostMapping
	public ResponseEntity<PublicationDto> register(@RequestBody @Valid PublicationForm publicationForm,
			UriComponentsBuilder uriBuilder) {
		Publication publication = publicationForm.converter();
		publicationRepository.save(publication);

		URI uri = uriBuilder.path("/publications/{id}").buildAndExpand(publication.getId()).toUri();
		return ResponseEntity.created(uri).body(new PublicationDto(publication));
	}
	
	@GetMapping("/{id}")
	public DetailsPublicationDto detail(@PathVariable Long id) {
		Publication publication = publicationRepository.getOne(id);
		return new DetailsPublicationDto(publication);
	}

}
