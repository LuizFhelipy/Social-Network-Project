package br.com.socialnetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.socialnetwork.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

}
