package com.hackernewsapi.demo.service;

import java.util.List;

import com.hackernewsapi.demo.model.Comment;
import com.hackernewsapi.demo.model.Story;

public interface HackerNewsService {
	
	List<Story> getTopStories();
	
	List<Story> getPastStories();
	
	List<Comment> getTopComments(Long storyId);
	
	
	
	

}
