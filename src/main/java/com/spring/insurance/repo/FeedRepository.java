package com.spring.insurance.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.insurance.entity.Feed;

@Repository
public interface FeedRepository extends JpaRepository<Feed, Long> {

}
