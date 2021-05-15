package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserReviewUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6139906187554854544L;
	@Id
	private String user;
	@Id
    private String userReview;
	private String date;
    private String review;
    private int stars;
    
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getIdUser() {
		return user;
	}

	public void setIdUser(String idUser) {
		this.user = idUser;
	}

	public String getIdUserReview() {
		return userReview;
	}

	public void setIdUserReview(String idUserReview) {
		this.userReview = idUserReview;
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
