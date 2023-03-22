package com.hackernewsapi.demo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.hackernewsapi.demo.model.Comment;
import com.hackernewsapi.demo.model.Story;
import com.hackernewsapi.demo.repository.StoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HackerNewsServiceImpl implements HackerNewsService {

	@Autowired
	StoryRepository storyRepository;

	private String BASE_URL = "https://hacker-news.firebaseio.com/v0/";
	private String TOP_STORIES_ENDPOINT = "topstories.json";
	private String ENDPOINT = "item/%s.json";

	private RestTemplate restTemplate = new RestTemplate();

	@Override
	@Cacheable(value = "stories", key = "'customKey'")
	public List<Story> getTopStories() {
		try {
			ResponseEntity<Long[]> stories = restTemplate.getForEntity(BASE_URL + TOP_STORIES_ENDPOINT, Long[].class);
			List<Long> topStories = Arrays.asList(stories.getBody());

			List<Story> topTenStories = new ArrayList<>();
			topStories.stream().limit(10).forEach(storyId -> {
				Story story = getStory(storyId);
				topTenStories.add(story);
				topTenStories.sort((Story story1, Story story2) -> story1.getScore().compareTo(story2.getScore()));
				storyRepository.save(story);
			});
			log.info("Stories fetched from api");
			return topTenStories;
		} catch (Exception e) {
			log.error("Exception while fetching stories from api ", e);
		}
		return null;
	}

	@Override
	@Cacheable(value = "paststories")
	public List<Story> getPastStories() {
		try {
			List<Story> stories = storyRepository.findAll();
			if (!stories.isEmpty()) {
				log.info("Stories fetched from database");
				return stories;

			} else {
				log.error("No Stories found");
			}
			return stories;
		} catch (Exception e) {
			log.error("Exception while fetching stories from database ", e);
		}
		return null;

	}

	@Override
	@Cacheable(value = "topComments")
	public List<Comment> getTopComments(Long storyId) {
		try {
			Story story = getStory(storyId);
			Long[] commentIds = story.getKids();
			List<Comment> comments = new ArrayList<>();
			if (commentIds != null) {
				Arrays.stream(commentIds).limit(10).filter(Objects::nonNull).map(commentId -> getComment(commentId))
						.forEach(comment -> {
							comments.add(comment);
							comments.sort((Comment c1, Comment c2) -> (c1.getKids() == null ? 0 : c1.getKids().length)
									- (c2.getKids() == null ? 0 : c2.getKids().length));

						});
			} else {
				log.error("No Comments Found");
			}
			return comments;
		} catch (Exception e) {
			log.error("Exception while fetching comments from api ", e);
		}
		return null;

	}

	public Story getStory(Long id) {
		String url = String.format(BASE_URL + ENDPOINT, id);
		ResponseEntity<Story> response = restTemplate.getForEntity(url, Story.class);
		return response.getBody();
	}

	public Comment getComment(Long id) {
		String url = String.format(BASE_URL + ENDPOINT, id);
		ResponseEntity<Comment> response = restTemplate.getForEntity(url, Comment.class);
		return response.getBody();
	}

}
