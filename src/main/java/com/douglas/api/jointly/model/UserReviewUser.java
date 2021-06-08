package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserReviewUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6139906187554854544L;
	
	@JsonProperty("user")
	@Id
	private String user;
	@JsonProperty("user_review")
	@Id
    private String userReview;
	@JsonProperty("date")
	private String date;
	@JsonProperty("review")
    private String review;
	@JsonProperty("stars")
    private int stars;
	@JsonProperty("is_deleted")
    private boolean is_deleted;
	@JsonProperty("is_sync")
    private boolean is_sync;
    
    public UserReviewUser() {
	}

	public UserReviewUser(String user, String userReview, String date, String review, int stars) {
		super();
		this.user = user;
		this.userReview = userReview;
		this.date = date;
		this.review = review;
		this.stars = stars;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserReview() {
		return userReview;
	}

	public void setUserReview(String userReview) {
		this.userReview = userReview;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public boolean isIs_sync() {
		return is_sync;
	}

	public void setIs_sync(boolean is_sync) {
		this.is_sync = is_sync;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userReview == null) ? 0 : userReview.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserReviewUser other = (UserReviewUser) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userReview == null) {
			if (other.userReview != null)
				return false;
		} else if (!userReview.equals(other.userReview))
			return false;
		return true;
	} 
}
