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
	private boolean is_sync;
	
	public chat() {
	}

	public chat(GregorianCalendar date, int idInitiative, String emailUser, String mensaje, boolean is_sync) {
		super();
		this.date = date;
		this.idInitiative = idInitiative;
		this.emailUser = emailUser;
		this.mensaje = mensaje;
		this.is_sync = is_sync;
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
