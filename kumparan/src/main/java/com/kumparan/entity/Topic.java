package com.kumparan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="topic")
public class Topic implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "topic_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int topicId;
	
	@Column(name = "topic_name")
	@NotNull
	private String topicName;
	
	@Column(name = "added_date")
	private Date addedDate;

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicName() {
		return topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
}
