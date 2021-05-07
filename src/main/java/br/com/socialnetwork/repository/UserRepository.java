package br.com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import br.com.socialnetwork.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
}
