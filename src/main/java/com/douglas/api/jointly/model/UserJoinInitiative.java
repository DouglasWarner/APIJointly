package com.douglas.api.jointly.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserJoinInitiative implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4237903622543173184L;
	@Id
	private LocalDateTime date;
	@Id
	private int idInitiative;
	@Id
    private String userEmail;
    private byte type;
    
    public UserJoinInitiative() {
		// TODO Auto-generated constructor stub
	}

	public UserJoinInitiative(LocalDateTime date, int idInitiative, String userEmail, byte type) {
		super();
		this.date = date;
		this.idInitiative = idInitiative;
		this.userEmail = userEmail;
		this.type = type;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public int getIdInitiative() {
		return idInitiative;
	}

	public void setIdInitiative(int idInitiative) {
		this.idInitiative = idInitiative;
	}

	public String getIdUser() {
		return userEmail;
	}

	public void setIdUser(String idUser) {
		this.userEmail = idUser;
	}

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + idInitiative;
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
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
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
