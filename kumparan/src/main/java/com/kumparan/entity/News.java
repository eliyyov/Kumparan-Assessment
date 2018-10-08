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
@Table(name="news")
public class News implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "news_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int newsId;
	
	@Column(name = "tittle")
	@NotNull
	private String tittle;
	
	@Column(name = "content")
	@NotNull
	private String content;
	
	@Column(name = "status")
	@NotNull
	private String status;
	
	@Column(name = "added_date")
	private Date addedDate;

	public int getNewsId() {
		return newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
