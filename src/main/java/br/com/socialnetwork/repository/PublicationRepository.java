package br.com.socialnetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.socialnetwork.model.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long>{

	List<Publication> findByAuthorName(String author);

}
