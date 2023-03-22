package com.hackernewsapi.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hackernewsapi.demo.model.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
}