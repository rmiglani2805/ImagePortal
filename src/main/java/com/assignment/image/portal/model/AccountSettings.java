package com.assignment.image.portal.model;

import java.util.ArrayList;
import java.util.Date;

import com.assignment.image.portal.util.Utils;
import com.google.gson.annotations.SerializedName;

/**
 * Settings on Imgur user accounts.
 * This is a different
 * class from @see ChangeAccountSettings because the Imgur
 * API 3 getter/setter endpoints are asymmetrical:
 * <ul>
 *   <li>bio is set here but returned in account, not account settings</li>
 *
 *   <li>username here is url in account and account_url in account settings.
  * </ul>
 */

public class AccountSettings {

	/**
	 * Get the username of the account
	 * @return the accountUrl
	 */
	public String getUserName() {
		return userName;
	}


	/**
	 * Get the email address of the account
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * Returns whether or not the user is able to upload higher quality images with less compression
	 * @return the highQuality
	 */
	public boolean isHighQuality() {
		return highQuality;
	}


	/**
	 * Returns whether or not the user's new images are automatically publicly accessible
	 * @return the publicImages
	 */
	public boolean isPublicImages() {
		return publicImages;
	}


	/**
	 * The date on which this accounts professional status expires, or null if not pro
	 * @return the proExpiration
	 */
	public Date getProExpiration() {
		return proExpiration;
	}


	/**
	 * Get whether or not this user has accepted the gallery terms
	 * @return the acceptedGalleryTerms
	 */
	public boolean isAcceptedGalleryTerms() {
		return acceptedGalleryTerms;
	}


	/**
	 * Get an list of the email addresses that have been activated to allow uploading
	 * @return the activeEmails
	 */
	public ArrayList<String> getActiveEmails() {
		return activeEmails;
	}


	/**
	 * Get whether the user is accepting incoming messages or not
	 * @return the messagingEnabled
	 */
	public boolean isMessagingEnabled() {
		return messagingEnabled;
	}




	/**
	 * Get whether or not this account has opted to see mature content in the gallery lists.
	 * @return the showMature
	 */
	public boolean isShowMature() {
		return showMature;
	}


	// ============================================================
	@SerializedName("account_url")
	private String userName;
	private String email;
	@SerializedName("high_quality")
	private boolean highQuality;
	@SerializedName("public_images")
	private boolean publicImages;
	@SerializedName("pro_expiration")
	private Date proExpiration;
	@SerializedName("accepted_gallery_terms")
	private boolean acceptedGalleryTerms;
	@SerializedName("active_emails")
	private ArrayList<String> activeEmails;
	@SerializedName("messaging_enabled")
	private boolean messagingEnabled;
	@SerializedName("show_mature")
	private boolean showMature;

	private AccountSettings() {}

	@Override
	public String toString() {
		return Utils.toString( this );
	}

} // class AccountSettings
