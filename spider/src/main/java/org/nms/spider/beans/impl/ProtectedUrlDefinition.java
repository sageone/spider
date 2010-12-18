package org.nms.spider.beans.impl;

import java.io.Serializable;

/**
 * A definition for a protected url.
 * <p>
 * The url, the user and the password.
 * </p>
 * @author daviz
 *
 */
public class ProtectedUrlDefinition 
implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7494345040572283408L;

	/**
	 * The url.
	 */
	private String url;
	
	/**
	 * The user.
	 */
	private String user;
	
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		
		sb.append(url)
			.append("[").append(user)
			.append("/").append(password)
			.append("]");
		
		return sb.toString();
	}
	/**
	 * The password.
	 */
	private String password;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
