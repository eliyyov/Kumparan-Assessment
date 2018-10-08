package com.kumparan.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kumparan.entity.NewsTopic;

public interface NewsTopicRepository extends CrudRepository<NewsTopic, Integer>{
	
	List<NewsTopic> findNewsTopicByNewsId(int newsId);

}
