package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserFollowUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6546520414916730238L;
	@JsonProperty("user")
	@Id
	private String user;
	@JsonProperty("user_follow")
	@Id
    private String user_follow;
	@JsonProperty("is_deleted")
	private boolean is_deleted;
	@JsonProperty("is_sync")
	private boolean is_sync;
    
    public UserFollowUser() {
	}

	public UserFollowUser(String user, String user_follow) {
		super();
		this.user = user;
		this.user_follow = user_follow;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUser_follow() {
		return user_follow;
	}

	public void setUser_follow(String user_follow) {
		this.user_follow = user_follow;
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
		result = prime * result + ((user_follow == null) ? 0 : user_follow.hashCode());
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
		UserFollowUser other = (UserFollowUser) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (user_follow == null) {
			if (other.user_follow != null)
				return false;
		} else if (!user_follow.equals(other.user_follow))
			return false;
		return true;
	}
}
