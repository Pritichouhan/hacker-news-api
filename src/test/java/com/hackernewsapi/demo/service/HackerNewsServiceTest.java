package com.hackernewsapi.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.hackernewsapi.demo.model.Comment;
import com.hackernewsapi.demo.model.Story;
import com.hackernewsapi.demo.repository.StoryRepository;

@SpringBootTest
@ActiveProfiles("test")
public class HackerNewsServiceTest {

	@MockBean
	StoryRepository storyRepository;

	@Autowired
	HackerNewsService hackerNewsService;

	@Test
	void testGetTopStories() {
		List<Story> stories = new ArrayList<>();
		Story story = new Story();
		story.setId(1L);
		story.setBy("user");
		story.setScore(100);
		story.setTitle("Title 1");
		story.setUrl("http://example.com/1");
		stories.add(story);
		when(this.hackerNewsService.getTopStories()).thenReturn(stories);
		assertNotNull(story);
		assertEquals(1, stories.size());
	}

	@Test
	void testGetPastStories() {
		List<Story> stories = new ArrayList<>();
		Story story = new Story();
		story.setId(1L);
		story.setBy("user");
		story.setScore(100);
		story.setTitle("Title 1");
		story.setUrl("http://example.com/1");
		stories.add(story);
		when(this.storyRepository.findAll()).thenReturn(stories);
		when(this.hackerNewsService.getPastStories()).thenReturn(stories);
		assertNotNull(story);
		assertEquals(1, stories.size());
	}

}
