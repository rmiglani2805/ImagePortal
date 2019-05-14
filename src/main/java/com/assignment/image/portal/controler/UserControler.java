package com.assignment.image.portal.controler;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.image.portal.model.Account;
import com.assignment.image.portal.model.Image;
import com.assignment.image.portal.service.AccountService;
import com.assignment.image.portal.util.ImageApiException;



@RestController
@RequestMapping(value = "/userInfo")
public class UserControler {
	private static final Logger logger = LoggerFactory.getLogger(UserControler.class);
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/{userName}", method = RequestMethod.GET)
	@Produces({"application/json"})
	public Account getUserInfo(@PathVariable("userName") String userName) {
		try {
			return accountService.getAccount(userName);
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		} 
		return new Account();
	}
	
	@RequestMapping(value = "/imageList/{page}", method = RequestMethod.GET)
	@Produces({"application/json"})
	public List<Image> getUserInfo(@PathVariable("page") int page) {
		try {
			return accountService.listImages(page);
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		} 
		return new ArrayList<Image>();
	}
}
