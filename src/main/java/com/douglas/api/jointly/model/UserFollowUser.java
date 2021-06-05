package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserFollowUser implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6546520414916730238L;
	@Id
	private String user;
	@Id
    private String userFollow;
	private boolean is_deleted;
	private boolean is_sync;
    
    public UserFollowUser() {
	}

	public UserFollowUser(String idUser, String idUserFollowed) {
		super();
		this.user = idUser;
		this.userFollow = idUserFollowed;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserFollow() {
		return userFollow;
	}

	public void setUserFollow(String userFollow) {
		this.userFollow = userFollow;
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
		result = prime * result + ((userFollow == null) ? 0 : userFollow.hashCode());
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
		if (userFollow == null) {
			if (other.userFollow != null)
				return false;
		} else if (!userFollow.equals(other.userFollow))
			return false;
		return true;
	}
}
