package com.kumparan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="news_topic")
public class NewsTopic implements Serializable{
	
private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "news_id")
	@NotNull
	private int newsId;
	
	@Column(name = "topic_id")
	@NotNull
	private int topicId;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
