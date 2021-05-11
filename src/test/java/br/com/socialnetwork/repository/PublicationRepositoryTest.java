package br.com.socialnetwork.repository;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.socialnetwork.model.Publication;

@RunWith(SpringRunner.class)
@DataJpaTest
class PublicationRepositoryTest {
	
	@Autowired
	private PublicationRepository publicationRepository;

	@Test
	void ShoultfindByAuthorName() {
		Pageable pageable = null;
		String author = "Luiz";
		Page<Publication> publicationtest = publicationRepository.findByAuthorName(author, pageable);
		
		Assert.assertEquals(author,publicationtest.getContent().get(0).getAuthor().getName());
	}
	
	@Test
	void ShoultfindById() {
		Long id = 1L;
		Optional<Publication> publicationtest =  publicationRepository.findById(id);
		
		Assert.assertTrue(publicationtest.isPresent());
	}

}
