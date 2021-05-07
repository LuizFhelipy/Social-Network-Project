package br.com.socialnetwork.controller;

import java.io.IOException;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.socialnetwork.config.cloudinary.CloudinaryService;
import br.com.socialnetwork.controller.dto.DetailsPublicationDto;
import br.com.socialnetwork.controller.dto.PublicationDto;
import br.com.socialnetwork.controller.form.UpdatePublicationForm;
import br.com.socialnetwork.model.Publication;
import br.com.socialnetwork.model.User;
import br.com.socialnetwork.repository.PublicationRepository;
import br.com.socialnetwork.repository.UserRepository;

@RestController
@RequestMapping("/publications")
public class PublicationsController {

	@Autowired
	private PublicationRepository publicationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CloudinaryService cloudinaryService;

	@GetMapping
	@Cacheable(value = "listoftopics")
	public Page<PublicationDto> list(@RequestParam(required = false) String author,
			@PageableDefault(sort = "id", direction = Direction.ASC)Pageable pagination) {

		if (author == null) {
			Page<Publication> publications = publicationRepository.findAll(pagination);
			return PublicationDto.converter(publications);
		} else {
			Page<Publication> publications = publicationRepository.findByAuthorName(author, pagination);
			return PublicationDto.converter(publications);
		}

	}

	@SuppressWarnings("rawtypes")
	@PostMapping
	@Transactional
	@CacheEvict(value = "listoftopics", allEntries = true)
	public ResponseEntity<PublicationDto> register(@RequestParam("title") String title,
			@RequestParam("message") String message, @RequestParam("file") MultipartFile file,
			UriComponentsBuilder uriBuilder) throws IOException {
		long userid = 1;
		User user = userRepository.getOne(userid);
		Map uploadResult = cloudinaryService.upload(file, "publication");

		String media = uploadResult.get("public_id").toString() + "." + uploadResult.get("format").toString();
		System.out.println(media);
		Publication publication = new Publication(title, message, media, user);
		publicationRepository.save(publication);

		URI uri = uriBuilder.path("/publications/{id}").buildAndExpand(publication.getId()).toUri();
		return ResponseEntity.created(uri).body(new PublicationDto(publication));
	}

	@GetMapping("/{id}")
	public ResponseEntity<DetailsPublicationDto> detail(@PathVariable Long id) {
		Optional<Publication> publication = publicationRepository.findById(id);
		if (publication.isPresent()) {
			return ResponseEntity.ok(new DetailsPublicationDto(publication.get()));
		}

		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listoftopics", allEntries = true)
	public ResponseEntity<PublicationDto> update(@PathVariable Long id,
			@RequestBody @Valid UpdatePublicationForm publicationForm) {
		Optional<Publication> optional = publicationRepository.findById(id);
		if (optional.isPresent()) {
			Publication publication = publicationForm.update(id, publicationRepository);
			return ResponseEntity.ok(new PublicationDto(publication));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listoftopics", allEntries = true)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Optional<Publication> optional = publicationRepository.findById(id);
		if (optional.isPresent()) {
			publicationRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
