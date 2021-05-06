package com.douglas.api.jointly.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserFollowUser {
	
	@Id
	private String user;
	@Id
    private String userFollow;
    
    public UserFollowUser() {
		// TODO Auto-generated constructor stub
	}

	public UserFollowUser(String idUser, String idUserFollowed) {
		super();
		this.user = idUser;
		this.userFollow = idUserFollowed;
	}

	public String getIdUser() {
		return user;
	}

	public void setIdUser(String idUser) {
		this.user = idUser;
	}

	public String getIdUserFollowed() {
		return userFollow;
	}

	public void setIdUserFollowed(String idUserFollowed) {
		this.userFollow = idUserFollowed;
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
