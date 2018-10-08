package com.kumparan.controller;

import java.text.ParseException;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kumparan.services.KumparanServices;

@RestController
public class KumparanController {

	@Autowired
	KumparanServices kumparanServices;
	
	
	@RequestMapping(path = "/getAllTopic", method = RequestMethod.GET, produces = "application/json")
	public Object getAllTopic() throws Exception, ParseException {
		return kumparanServices.getAllTopic();
	}
	
	@RequestMapping(path = "/getTopicByTopicName", method = RequestMethod.POST, produces = "application/json")
	public Object getTopicByTopicName(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.getTopicByTopicName(input);
	}
	
	@RequestMapping(path = "/createTopic", method = RequestMethod.POST, produces = "application/json")
	public Object createTopic(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.createTopic(input);
	}
	
	@RequestMapping(path = "/updateTopic", method = RequestMethod.POST, produces = "application/json")
	public Object updateTopic(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.updateTopic(input);
	}
	
	@RequestMapping(path = "/deleteTopic", method = RequestMethod.POST, produces = "application/json")
	public Object deleteTopic(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.deleteTopic(input);
	}
	
	@RequestMapping(path = "/getAllNews", method = RequestMethod.GET, produces = "application/json")
	public Object getAllNews() throws Exception, ParseException {
		return kumparanServices.getAllNews();
	}
	
	@RequestMapping(path = "/getNewsByStatus", method = RequestMethod.POST, produces = "application/json")
	public Object getNewsByStatus(@RequestBody String input) throws Exception, ParseException {
		return kumparanServices.getNewsByStatus(input);
	}
	
	@RequestMapping(path = "/getNewsByTopicName", method = RequestMethod.POST, produces = "application/json")
	public Object getNewsByTopicName(@RequestBody String input) throws Exception, ParseException {
		return kumparanServices.getNewsByTopicName(input);
	}
	
	@RequestMapping(path = "/createNews", method = RequestMethod.POST, produces = "application/json")
	public Object createNews(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.createNews(input);
	}
	
	@RequestMapping(path = "/updateNews", method = RequestMethod.POST, produces = "application/json")
	public Object updateNews(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.updateNews(input);
	}
	
	@RequestMapping(path = "/deleteNews", method = RequestMethod.POST, produces = "application/json")
	public Object deleteNews(@RequestBody String input) throws JSONException, ParseException {
		return kumparanServices.deleteNews(input);
	}
	
	
}
