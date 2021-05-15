package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserJoinInitiative implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237903622543173184L;
	@Id
	private long idInitiative;
	@Id
    private String userEmail;
	private String date;
    private int type;
    
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

	public String getIdUser() {
		return userEmail;
	}

	public void setIdUser(String idUser) {
		this.userEmail = idUser;
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
