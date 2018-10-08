package com.kumparan.repository;

import com.kumparan.entity.Topic;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TopicRepository extends CrudRepository<Topic, Integer>{
	
	List<Topic> findAll();
	
	Topic findTopicByTopicName(String topicName);
	
	Topic findTopicByTopicId(int topicId);
	
}
