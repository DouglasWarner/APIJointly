package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Chat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1012319594635181992L;
	@Id
	private String date;
	@Id
	private int idInitiative;
	@Id
	private String emailUser;
	private String message;
    private boolean read;
	private boolean is_sync;
	
	public Chat() {
	}

	public Chat(String date, int idInitiative, String emailUser, boolean read, String message, boolean is_sync) {
		super();
		this.date = date;
		this.idInitiative = idInitiative;
		this.emailUser = emailUser;
		this.message = message;
		this.read = read;
		this.is_sync = is_sync;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdInitiative() {
		return idInitiative;
	}

	public void setIdInitiative(int idInitiative) {
		this.idInitiative = idInitiative;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
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
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((emailUser == null) ? 0 : emailUser.hashCode());
		result = prime * result + idInitiative;
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
		Chat other = (Chat) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (emailUser == null) {
			if (other.emailUser != null)
				return false;
		} else if (!emailUser.equals(other.emailUser))
			return false;
		if (idInitiative != other.idInitiative)
			return false;
		return true;
	}
}
