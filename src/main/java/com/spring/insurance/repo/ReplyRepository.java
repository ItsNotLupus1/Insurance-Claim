package com.spring.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.insurance.entity.Reply;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

}
