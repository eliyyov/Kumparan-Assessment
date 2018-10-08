package com.kumparan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.kumparan.entity.News;

public interface NewsRepository extends CrudRepository<News, Integer>{
	
	List<News> findAll();
	
	News findNewsByNewsId(int newsId);

	News findNewsByTittleAndContentAndStatus(String tittle, String content, String status);
	
	List<News> findNewsByStatus(String status);
	
	@Query( value = "SELECT c.news_id, a.tittle, a.content, a.added_date, a.status, topic_name \r\n" + 
			"FROM news a \r\n" + 
			"LEFT JOIN news_topic c ON a.news_id = c.news_id \r\n" + 
			"LEFT JOIN topic b ON b.topic_id = c.topic_id \r\n" + 
			"WHERE topic_name = :topicParameter", nativeQuery = true)
	List<Object[]> findNewsByTopicName(@Param("topicParameter")String topic);
	
}
