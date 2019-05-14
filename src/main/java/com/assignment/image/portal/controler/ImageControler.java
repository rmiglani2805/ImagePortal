package com.assignment.image.portal.controler;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.image.portal.model.Account;
import com.assignment.image.portal.model.Image;
import com.assignment.image.portal.service.AccountService;
import com.assignment.image.portal.service.AuthService;
import com.assignment.image.portal.service.ImageService;
import com.assignment.image.portal.util.ImageApiException;



@RestController
@RequestMapping(value = "/image")
public class ImageControler {
	private static final Logger logger = LoggerFactory.getLogger(ImageControler.class);
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private AuthService authService;
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Produces({"application/json"})
	public Image getImageInfo(@PathVariable(value="id") String id) {
		try {
			if(authService.isUserAuthenticated()) {
				if(!authService.isAccessTokenValid()) {
					authService.getRefreshToken();
				}
				return imageService.getImageInfo(id);
			}
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		}
		return new Image();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@Produces({"application/json"})
	public Boolean deleteImage(@PathVariable(value="id") String id) {
		try {
			if(authService.isUserAuthenticated()) {
				if(!authService.isAccessTokenValid()) {
					authService.getRefreshToken();
				}
			return imageService.deleteImage(id);
			}
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		}
		return false;
	}
	
	@RequestMapping(value = "/upload/imageUrl", method = RequestMethod.POST)
	@Produces({"application/json"})
	public Image uploadUrlImage(@RequestParam("url") String url, @RequestParam("fileName") String fileName, @RequestParam("albumId") String albumId,
			@RequestParam("title") String title, @RequestParam("description") String description) {
		try {
			if(authService.isUserAuthenticated()) {
				if(!authService.isAccessTokenValid()) {
					authService.getRefreshToken();
				}
			return imageService.uploadUrlImage(url, fileName, albumId, title, description);
			}
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		}
		return new Image();
	}
	
	@RequestMapping(value = "/upload/localImage", method = RequestMethod.POST)
	@Produces({"application/json"})
	public Image uploadLocalImage(@RequestParam("mimeType") String mimeType, @RequestParam("fileName") String fileName, @RequestParam("albumId") String albumId,
			@RequestParam("title") String title, @RequestParam("description") String description) {
		try {
			if(authService.isUserAuthenticated()) {
				if(!authService.isAccessTokenValid()) {
					authService.getRefreshToken();
				}
			return imageService.uploadLocalImage(mimeType, fileName, albumId, title, description);
			}
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		return new Image();
	}
	
	@RequestMapping(value = "/updateImage", method = RequestMethod.PUT)
	@Produces({"application/json"})
	public boolean updateImage(@RequestParam("id") String id, @RequestParam("title") String title, @RequestParam("description") String description) {
		try {
			if(authService.isUserAuthenticated()) {
				if(!authService.isAccessTokenValid()) {
					authService.getRefreshToken();
				}
			return imageService.updateImage(id, title, description);
			}
			
		} catch (ImageApiException e) {
			logger.error(e.getMessage());
		} 
		return false;
	}
}
