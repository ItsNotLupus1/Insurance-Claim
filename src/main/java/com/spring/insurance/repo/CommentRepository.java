package com.spring.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.insurance.entity.Comments;

@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

}
