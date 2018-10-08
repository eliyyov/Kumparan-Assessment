package com.kumparan.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kumparan.common.GenericResponseDataAlreadyExist;
import com.kumparan.common.GenericResponseDataNotFound;
import com.kumparan.common.GenericResponseInvalidRequest;
import com.kumparan.common.GenericResponseOK;
import com.kumparan.entity.News;
import com.kumparan.entity.NewsPlus;
import com.kumparan.entity.NewsTopic;
import com.kumparan.entity.Topic;
import com.kumparan.repository.NewsRepository;
import com.kumparan.repository.NewsTopicRepository;
import com.kumparan.repository.TopicRepository;

@Service
public class KumparanServices {

	@Autowired
	TopicRepository topicRepository;

	@Autowired
	NewsRepository newsRepository;

	@Autowired
	NewsTopicRepository newsTopicRepository;

	public Object getAllTopic() {

		List<Topic> result = topicRepository.findAll();

		if (result == null || result.size() == 0) {
			return new GenericResponseDataNotFound();
		} else {
			return result;
		}
	}

	public Object getTopicByTopicName(String request) {

		JSONObject jsonObject = new JSONObject(request);
		String topicName = jsonObject.getString("topic_name");

		Topic result = topicRepository.findTopicByTopicName(topicName);

		if (result == null) {
			return new GenericResponseDataNotFound();
		} else {
			return result;
		}
	}

	public Object createTopic(String request) {
		JSONObject jsonObject = new JSONObject(request);
		Topic input = new Topic();
		String topicName = jsonObject.getString("topic_name");

		Topic result = topicRepository.findTopicByTopicName(topicName);

		if (result == null) {
			input.setTopicName(jsonObject.getString("topic_name"));
			input.setAddedDate(new Date());
			topicRepository.save(input);

			return new GenericResponseOK();
		} else {
			return new GenericResponseDataAlreadyExist();
		}
	}

	public Object updateTopic(String request) {
		JSONObject jsonObject = new JSONObject(request);
		int topicId = jsonObject.getInt("topic_id");

		Topic result = topicRepository.findTopicByTopicId(topicId);

		if (result == null) {
			return new GenericResponseDataNotFound();
		} else {
			result.setTopicName(jsonObject.getString("topic_name"));
			topicRepository.save(result);

			return new GenericResponseOK();
		}
	}

	public Object deleteTopic(String request) throws JSONException {
		JSONObject jsonObject = new JSONObject(request);
		int topicId = jsonObject.getInt("topic_id");

		Topic result = topicRepository.findTopicByTopicId(topicId);

		if (result == null) {
			return new GenericResponseDataNotFound();
		} else {
			topicRepository.delete(result);
			return new GenericResponseOK();
		}
	}

	public Object getAllNews() {

		List<News> result = newsRepository.findAll();

		if (result == null || result.size() == 0) {
			return new GenericResponseDataNotFound();
		} else {
			return result;
		}
	}

	public Object getNewsByStatus(String request) {

		JSONObject jsonObject = new JSONObject(request);
		String status = jsonObject.getString("status");

		List<News> result = newsRepository.findNewsByStatus(status);

		if (result == null || result.size() == 0) {
			return new GenericResponseDataNotFound();
		} else {
			return result;
		}
	}

	public Object getNewsByTopicName(String request) {

		JSONObject jsonObject = new JSONObject(request);
		String topicName = jsonObject.getString("topic_name");

		List<Object[]> result = newsRepository.findNewsByTopicName(topicName);

		if (result == null || result.size() == 0) {
			return new GenericResponseDataNotFound();
		} else {

			List<Object> tampung = new ArrayList<>();
			
			for (int i = 0; i < result.size(); i++) {
				NewsPlus isiNews = new NewsPlus();
				
				isiNews.setNewsId(Integer.valueOf(result.get(i)[0].toString()));
				isiNews.setTittle(result.get(i)[1].toString());
				isiNews.setContent(result.get(i)[2].toString());
				isiNews.setAddedDate((Date)result.get(i)[3]);
				isiNews.setStatus(result.get(i)[4].toString());
				isiNews.setTopicName(result.get(i)[5].toString());
				tampung.add(isiNews);
			}
			return tampung;
		}
	}

	public Object createNews(String request) {
		JSONObject jsonObject = new JSONObject(request);
		JSONArray topic = jsonObject.getJSONArray("topic");
		Topic result = new Topic();
		News input = new News();
		String topicName;
		JSONArray tampungArray = new JSONArray();

		// check if topic exist or not
		for (int i = 0; i < topic.length(); i++) {
			topicName = topic.getString(i);

			result = topicRepository.findTopicByTopicName(topicName);

			// if topic not exist
			if (result == null) {
				return new GenericResponseDataNotFound();
			} else {
				int topicId = result.getTopicId();

				// put topicId in array for later use
				tampungArray.put(topicId);
			}
		}

		// check if news exist or not
		String tittle = jsonObject.getString("tittle");
		String content = jsonObject.getString("content");
		String status = jsonObject.getString("status");

		News resultNews = newsRepository.findNewsByTittleAndContentAndStatus(tittle, content, status);

		// if news doesn't exist
		if (resultNews == null) {
			input.setTittle(tittle);
			input.setContent(content);
			input.setStatus(status);
			input.setAddedDate(new Date());
			newsRepository.save(input);
		} else {
			return new GenericResponseDataAlreadyExist();
		}

		// get newsId based on tittle, content, and status
		News resultNewsTwo = newsRepository.findNewsByTittleAndContentAndStatus(tittle, content, status);

		// get newsId
		int newsId = resultNewsTwo.getNewsId();

		// insert newsId and TopicId in NewsTopic table
		for (int i = 0; i < tampungArray.length(); i++) {

			NewsTopic newsTopic = new NewsTopic();

			int topicId = (int) tampungArray.get(i);

			newsTopic.setNewsId(newsId);
			newsTopic.setTopicId(topicId);
			newsTopicRepository.save(newsTopic);

		}
		return new GenericResponseOK();
	}

	public Object updateNews(String request) {
		JSONObject jsonObject = new JSONObject(request);
		int newsId = jsonObject.getInt("news_id");
		String tittle = jsonObject.getString("tittle");
		String content = jsonObject.getString("content");
		String status = jsonObject.getString("status");

		JSONArray topic = jsonObject.getJSONArray("topic");
		Topic result = new Topic();
		String topicName;
		JSONArray tampungArray = new JSONArray();

		// check if topic exist or not
		for (int i = 0; i < topic.length(); i++) {
			topicName = topic.getString(i);

			result = topicRepository.findTopicByTopicName(topicName);

			// if topic not exist
			if (result == null) {
				return new GenericResponseDataNotFound();
			} else {
				int topicId = result.getTopicId();

				// put topicId in array for later use
				tampungArray.put(topicId);
			}
		}

		// check News based on news_id
		News resultNews = newsRepository.findNewsByNewsId(newsId);

		// if data not found
		if (resultNews == null) {
			return new GenericResponseInvalidRequest();
		} else {
			// update news_topic
			List<NewsTopic> resultNewsTopic = newsTopicRepository.findNewsTopicByNewsId(newsId);

			if (resultNewsTopic.size() < 0) {
				return new GenericResponseInvalidRequest();
			} else {
				// loop for update NewsTopic table
				for (int i = 0; i < resultNewsTopic.size(); i++) {

					NewsTopic newNewsTopic = resultNewsTopic.get(i);
					int topic_id = (int) tampungArray.get(i);

					newNewsTopic.setTopicId(topic_id);
					newsTopicRepository.save(newNewsTopic);
				}
			}
			// update news based on request
			resultNews.setTittle(tittle);
			resultNews.setContent(content);
			resultNews.setStatus(status);
			newsRepository.save(resultNews);

			return new GenericResponseOK();
		}
	}

	public Object deleteNews(String request) throws JSONException {
		JSONObject jsonObject = new JSONObject(request);
		int newsId = jsonObject.getInt("news_id");

		News resultNews = newsRepository.findNewsByNewsId(newsId);

		if (resultNews == null) {
			return new GenericResponseDataNotFound();
		} else {

			List<NewsTopic> resultNewsTopic = newsTopicRepository.findNewsTopicByNewsId(newsId);

			newsTopicRepository.deleteAll(resultNewsTopic);
			newsRepository.delete(resultNews);
			return new GenericResponseOK();
		}

	}

}
