package br.com.socialnetwork.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.socialnetwork.controller.dto.CommentDto;
import br.com.socialnetwork.controller.form.CommentForm;
import br.com.socialnetwork.controller.form.UpdateCommentForm;
import br.com.socialnetwork.model.Comment;
import br.com.socialnetwork.model.Publication;
import br.com.socialnetwork.model.User;
import br.com.socialnetwork.repository.CommentRepository;
import br.com.socialnetwork.repository.PublicationRepository;
import br.com.socialnetwork.repository.UserRepository;

@RestController
@RequestMapping("/publications/{id}/comments")
public class CommentsController {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private PublicationRepository publicationRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CommentDto> register(Authentication authentication, @RequestBody @Valid CommentForm commentForm,
			UriComponentsBuilder uriBuilder, @PathVariable Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		User user = userRepository.getOne(authenticatedUser.getId());
		Optional<Publication> publication = publicationRepository.findById(id);
		if(publication.isPresent()) {
			Comment comment = commentForm.converter(publication.get(),user);
			commentRepository.save(comment);
			URI uri = uriBuilder.path("/publications/{id}/comments/{commentid}").buildAndExpand(publication.get().getId(),comment.getId()).toUri();
			return ResponseEntity.created(uri).body(new CommentDto(comment));
		}
		
		return ResponseEntity.notFound().build();
		
	}
	
	@PutMapping("/{idcomment}")
	@Transactional
	public ResponseEntity<CommentDto> update(Authentication authentication, @PathVariable("idcomment") Long id,
			@RequestBody @Valid UpdateCommentForm commentForm) {
		User authenticatedUser = (User) authentication.getPrincipal();
		@SuppressWarnings("unused")
		User user = userRepository.getOne(authenticatedUser.getId());
		Optional<Comment> optional = commentRepository.findById(id);
		if (optional.isPresent()) {
			Comment comment = commentForm.update(id, commentRepository);
			return ResponseEntity.ok(new CommentDto(comment));
		}

		return ResponseEntity.notFound().build();

	}
	
	@DeleteMapping("/{idcomment}")
	@Transactional
	public ResponseEntity<?> delete(Authentication authentication, @PathVariable("idcomment") Long id) {
		User authenticatedUser = (User) authentication.getPrincipal();
		@SuppressWarnings("unused")
		User user = userRepository.getOne(authenticatedUser.getId());
		Optional<Comment> optional = commentRepository.findById(id);
		if (optional.isPresent()) {
			commentRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	
	
	
}
