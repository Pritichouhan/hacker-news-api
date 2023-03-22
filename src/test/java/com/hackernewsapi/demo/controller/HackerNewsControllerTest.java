package com.hackernewsapi.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.hackernewsapi.demo.model.Story;
import com.hackernewsapi.demo.service.HackerNewsService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HackerNewsControllerTest {
	@MockBean
	private HackerNewsService hackerNewsService;
	@Autowired
	private HackerNewsAPIController hackerNewsAPIController;

	@Test
	public void testGetTopStories() throws Exception {
		List<Story> topStories = new ArrayList<>();
		Story story = new Story();
		story.setId(1L);
		story.setTitle("Test Story");
		story.setUrl("http://test.com");
		story.setScore(10);
		story.setTime(new Date(19970301));
		story.setBy("testuser");
		topStories.add(story);
		Mockito.when(hackerNewsService.getTopStories()).thenReturn(topStories);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(hackerNewsAPIController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/hacker-news/top-stories"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1L))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Story"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].url").value("http://test.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(10))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].time").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].by").value("testuser"));
	}

	@Test
	public void testPastStories() throws Exception {
		List<Story> topStories = new ArrayList<>();
		Story story = new Story();
		story.setId(1L);
		story.setTitle("Test Story");
		story.setUrl("http://test.com");
		story.setScore(10);
		story.setTime(new Date(19970301));
		story.setBy("testuser");
		topStories.add(story);
		Mockito.when(hackerNewsService.getPastStories()).thenReturn(topStories);
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(hackerNewsAPIController).build();
		mockMvc.perform(MockMvcRequestBuilders.get("/hacker-news/past-stories"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("Test Story"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].url").value("http://test.com"))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].score").value(10))
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].time").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$[0].by").value("testuser"));
	}

}
