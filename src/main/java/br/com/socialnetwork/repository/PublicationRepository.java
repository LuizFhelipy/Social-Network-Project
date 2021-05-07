package br.com.socialnetwork.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.socialnetwork.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

	Page<Publication> findByAuthorName(String author, Pageable pagination);
	
	Optional<Publication> findById(Long id);

}
