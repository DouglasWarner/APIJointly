package com.douglas.api.jointly.model;

import java.io.Serializable;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class chat implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1012319594635181992L;
	@Id
	private GregorianCalendar date;
	@Id
	private int idInitiative;
	@Id
	private String emailUser;
	private String mensaje;
	
	public chat() {
		// TODO Auto-generated constructor stub
	}

	public chat(GregorianCalendar date, int idInitiative, String emailUser, String mensaje) {
		super();
		this.date = date;
		this.idInitiative = idInitiative;
		this.emailUser = emailUser;
		this.mensaje = mensaje;
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

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
		chat other = (chat) obj;
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
