/** This file is released under the Apache License 2.0. See the LICENSE file for details. **/
package com.assignment.image.portal.util;

/**
 * This is for when an authentication problem occurs with
 * Imgur itself, such as invalid client credentials or
 * user tokens.  This also happens if a refresh token expires,
 * an authorization code is stale, or if the user has revoked
 * the application's access.
 * 
 * @author Kevin Kelm (triggur@gmail.com)
 *
 */
public class ImageAuthException extends ImageApiException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ImageAuthException(String message) {
		super( message );
	}

	public ImageAuthException(String message, int code) {
		super( message, code );
	}

}
