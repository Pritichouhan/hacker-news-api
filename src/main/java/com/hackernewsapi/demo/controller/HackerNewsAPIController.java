package com.hackernewsapi.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hackernewsapi.demo.model.Comment;
import com.hackernewsapi.demo.model.Story;
import com.hackernewsapi.demo.service.HackerNewsService;

@RestController
@RequestMapping("/hacker-news")
public class HackerNewsAPIController {

	@Autowired
	HackerNewsService hackerNewsService;

	@GetMapping("/top-stories")
	public ResponseEntity<List<Story>> getTopStories() {
		List<Story> topStories = hackerNewsService.getTopStories();
		return new ResponseEntity<>(topStories, HttpStatus.OK);
	}

	@GetMapping("/past-stories")
	public ResponseEntity<List<Story>> getPastStories() {
		List<Story> stories = hackerNewsService.getPastStories();
		return new ResponseEntity<>(stories, HttpStatus.OK);
	}

	@GetMapping("/comments/{StoryId}")
	public ResponseEntity<List<Comment>> getTopTenComments(@PathVariable Long StoryId) {
		List<Comment> comments = hackerNewsService.getTopComments(StoryId);
		return new ResponseEntity<>(comments, HttpStatus.OK);
	}
}
