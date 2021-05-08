package com.douglas.api.jointly.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserReviewUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6139906187554854544L;
	@Id
	private LocalDateTime date;
	@Id
	private String user;
	@Id
    private String userReview;
    private String review;
    private int stars;
    
    public UserReviewUser() {
		// TODO Auto-generated constructor stub
	}

	public UserReviewUser(LocalDateTime date, String idUser, String idUserReview, String review, int stars) {
		super();
		this.date = date;
		this.user = idUser;
		this.userReview = idUserReview;
		this.review = review;
		this.stars = stars;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
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
