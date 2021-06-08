package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class UserJoinInitiative implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237903622543173184L;

	@JsonProperty("id_initiative")
	@Id
	private long idInitiative;
	@JsonProperty("user_email")
	@Id
    private String userEmail;
	@JsonProperty("date")
	private String date;
	@JsonProperty("type")
    private int type;
	@JsonProperty("is_deleted")
    private boolean is_deleted;
	@JsonProperty("is_sync")
    private boolean is_sync;
    
    public UserJoinInitiative() {
	}

	public UserJoinInitiative(long idInitiative, String userEmail, String date, int type) {
		super();
		this.idInitiative = idInitiative;
		this.userEmail = userEmail;
		this.date = date;
		this.type = type;
	}

	public long getIdInitiative() {
		return idInitiative;
	}

	public void setIdInitiative(long idInitiative) {
		this.idInitiative = idInitiative;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
		result = prime * result + (int) (idInitiative ^ (idInitiative >>> 32));
		result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
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
		UserJoinInitiative other = (UserJoinInitiative) obj;
		if (idInitiative != other.idInitiative)
			return false;
		if (userEmail == null) {
			if (other.userEmail != null)
				return false;
		} else if (!userEmail.equals(other.userEmail))
			return false;
		return true;
	}   
}
