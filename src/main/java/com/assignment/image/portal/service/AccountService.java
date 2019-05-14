package com.assignment.image.portal.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.assignment.image.portal.model.Account;
import com.assignment.image.portal.model.Image;
import com.assignment.image.portal.model.ImgurResponseWrapper;
import com.assignment.image.portal.util.ImageApiException;
import com.assignment.image.portal.util.ImageAuthException;
import com.google.gson.GsonBuilder;

import retrofit.Call;
import retrofit.Response;


/**
 * 
 * Manages the user's account, settings, favorites, etc
 * <p>
 * See <a href="http://api.imgur.com/endpoints/account">Imgur Accounts</a> for API details
 *
 */
@Service
public class AccountService {

	/**
	 * Given an account name, return the Account object for it.
	 * <p>
     * <b>ACCESS: ANONYMOUS</b>
	 * @param userName the name of the account
	 * @return Account object
	 * @throws ImageApiException something went pear-shaped
	 */
	public Account getAccount( String userName ) throws ImageApiException {
		Call<ImgurResponseWrapper<Account>> call =
				client.getApi().getAccount( userName );

		try {
			Response<ImgurResponseWrapper<Account>> res = call.execute();
			ImgurResponseWrapper<Account> out = res.body();
			client.throwOnWrapperError( res );

			return out.getData();
		} catch (IOException e) {
			throw new ImageApiException( e.getMessage() );
		} 
	} // getAccount



	
	/**
	 * Returns a paged list of images associated with the current
	 * user, paged 50 at a time.
	 * <p>
     * <b>ACCESS: AUTHENTICATED USER</b>
	 * @param page the page number, starting at 0
	 * @return A list of Image objects
	 * @throws ImageApiException something failed
	 */
	public List<Image> listImages( int page ) throws ImageApiException {
		String userName = client.getAuthenticatedUserName();
		if( userName == null ) {
			throw new ImageAuthException( "No user logged in", 401 );
		} // if

		Call<ImgurResponseWrapper<List<Image>>> call =
				client.getApi().listAccountImages( userName, page );

		try {
			Response<ImgurResponseWrapper<List<Image>>> res = call.execute();
			ImgurResponseWrapper<List<Image>> out = res.body();
			client.throwOnWrapperError( res );
			return out.getData();
			
		} catch (IOException e) {
			throw new ImageApiException( e.getMessage() );
		} 
	} // listImages
	
	/**
	 * Returns a list of image IDs associated with the current
	 * user, paged 50 at a time
	 * <p>
     * <b>ACCESS: AUTHENTICATED USER</b>
	 * @param page The page number to fetch, starting at 0
	 * @return A list of Image IDs (strings)
	 * @throws ImageApiException something failed
	 */
	public List<String> listImageIds( int page ) throws ImageApiException {
		String userName = client.getAuthenticatedUserName();
		if( userName == null ) {
			throw new ImageAuthException( "No user logged in", 401 );
		} // if

		Call<ImgurResponseWrapper<List<String>>> call =
				client.getApi().listAccountImageIds( userName, page );
		try {
			Response<ImgurResponseWrapper<List<String>>> res = call.execute();
			ImgurResponseWrapper<List<String>> out = res.body();
			client.throwOnWrapperError( res );
			return out.getData();
		} catch (IOException e) {
			throw new ImageApiException( e.getMessage() );
		} 
	} // listImageIds
	
	/**
	 * Returns the total number of Images the current user owns.
	 * <p>
     * <b>ACCESS: AUTHENTICATED USER</b>
	 * @return int the number of images the user owns
	 * @throws ImageApiException something failed
	 */
	public int getImageCount() throws ImageApiException {
		String userName = client.getAuthenticatedUserName();
		if( userName == null ) {
			throw new ImageAuthException( "No user logged in", 401 );
		} // if

		Call<ImgurResponseWrapper<Integer>> call =
				client.getApi().getAccountImageCount( userName );

		try {
			Response<ImgurResponseWrapper<Integer>> res = call.execute();
			ImgurResponseWrapper<Integer> out = res.body();

			client.throwOnWrapperError( res );
			return (Integer)out.getData();
		} catch (IOException e) {
			throw new ImageApiException( e.getMessage() );
		} 
	} // getImageCount
	

	// ================================================

	protected AccountService(ImageClient imgurClient, GsonBuilder gsonBuilder) {
		this.client = imgurClient;
	}

	private ImageClient client = null;

}
