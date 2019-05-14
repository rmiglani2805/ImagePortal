/** This file is released under the Apache License 2.0. See the LICENSE file for details. **/
package com.assignment.image.portal.util;

import java.util.List;

import com.assignment.image.portal.model.Account;
import com.assignment.image.portal.model.AccountSettings;
import com.assignment.image.portal.model.Image;
import com.assignment.image.portal.model.ImgurResponseWrapper;
import com.assignment.image.portal.model.OAuth2;

import com.squareup.okhttp.RequestBody;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.HEAD;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.http.Query;


public interface RetrofittedImgur {
	// apparently Retrofit 2 doesn't want to support multiple
		// interfaces in the same API.  There's logic to this, but
		// It's onerous and a pain to create multiple clients for
		// the same API, so we're just going to jam them all in here.
		
		// ============================================================
		// ============================================================
		// ============================================================
		// ============================================================
		// ============================================================
		// ACCOUNT CALLS
		// ============================================================

		@GET("/3/account/{username}")
		Call<ImgurResponseWrapper<Account>> getAccount(
				@Path("username") String userName );


		@GET("/3/account/{username}/settings")
		Call<ImgurResponseWrapper<AccountSettings>> getAccountSettings(
				@Path("username") String userName );


		
		@GET("/3/account/{username}/verifyemail")
		Call<ImgurResponseWrapper<Boolean>> isAccountVerified(
				@Path("username") String userName );
		
		@POST("/3/account/{username}/verifyemail")
		Call<ImgurResponseWrapper<Boolean>> sendAccountVerificationEmail(
				@Path("username") String userName );



		@GET("/3/account/{username}/images/{page}")
		Call<ImgurResponseWrapper<List<Image>>> listAccountImages(
				@Path("username") String userName,
				@Path("page") int page
				);

		@GET("/3/account/{username}/images/ids/{page}")
		Call<ImgurResponseWrapper<List<String>>> listAccountImageIds(
				@Path("username") String userName,
				@Path("page") int page
				);

		@GET("/3/account/{username}/images/count")
		Call<ImgurResponseWrapper<Integer>> getAccountImageCount(
				@Path("username") String userName);
		
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// IMAGE CALLS
	// ============================================================

	@GET("/3/image/{id}")
	Call<ImgurResponseWrapper<Image>> getImageInfo(
			@Path("id") String id );
	
	@Multipart
	@POST("/3/upload")
	Call<ImgurResponseWrapper<Image>> uploadLocalImage(
			@Part("album") String albumName, // id or delete hash
			@Part("type") String uploadType,  // use "URL"
			@Part("title") String title,
			@Part("description") String description,
			@Part("filename") String fileName,
			@Part("image\"; filename=\"foo.png") RequestBody imageFile);


	@POST("/3/upload")
	Call<ImgurResponseWrapper<Image>> uploadUrlImage(
			@Query("album") String albumName, // id or delete hash
			@Query("type") String uploadType,  // use "URL"
			@Query("title") String title,
			@Query("description") String description,
			@Body RequestBody imageUrl );

	// looking for download? it doesn't use the https://api.imgur.com/
	// base path used everywhere else, so we just used OkHttp directly.
	
	@FormUrlEncoded
	@POST("/3/image/{id}")
	Call<ImgurResponseWrapper<Boolean>> updateImageInfo(
			@Path("id") String idOrDeleteHash,
			@Field("title") String title,
			@Field("description") String description );

	@DELETE("/3/image/{id}")
	Call<ImgurResponseWrapper<Boolean>> deleteImage(
			@Path("id") String idOrDeleteHash);

	@GET("/3/image/{imageId}/favorite")
	Call<ImgurResponseWrapper<Image>> toggleImageFavorite(
			@Path("imageId") String idOrDeleteHash );
	
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// ============================================================
	// AUTHENTICATION CALLS
	// ============================================================
	@FormUrlEncoded
	@POST("/oauth2/token")
	Call<OAuth2> tradeAuthCodeForTokens(
			@Field("client_id") String clientId,
			@Field("client_secret") String clientSecret,
			@Field("grant_type") String grantType,
			@Field("code") String code );

	// returns 200 OK if the token is still good
	@HEAD("/oauth2/secret")
	Call<Object> validateToken();
	
	@FormUrlEncoded
	@POST("/oauth2/token")
	Call<OAuth2> refreshAccessToken(
			@Field("client_id") String clientId,
			@Field("client_secret") String clientSecret,
			@Field("grant_type") String grantType,
			@Field("refresh_token") String refreshToken );

} // interface RetrofittedImgur

