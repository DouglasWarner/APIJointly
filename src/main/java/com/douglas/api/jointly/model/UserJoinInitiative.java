package com.douglas.api.jointly.model;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserJoinInitiative {
	
	@Id
	private GregorianCalendar date;
	@Id
	private int idInitiative;
	@Id
    private String idUser;
    private byte type;
    
    public UserJoinInitiative() {
		// TODO Auto-generated constructor stub
	}

	public UserJoinInitiative(GregorianCalendar date, int idInitiative, String idUser, byte type) {
		super();
		this.date = date;
		this.idInitiative = idInitiative;
		this.idUser = idUser;
		this.type = type;
	}

	public GregorianCalendar getDate() {
		return date;
	}

	public void setDate(GregorianCalendar date) {
		this.date = date;
	}

	public int getIdInitiative() {
		return idInitiative;
	}

	public void setIdInitiative(int idInitiative) {
		this.idInitiative = idInitiative;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
    
}
