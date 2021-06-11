package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Countries implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1606604993207831170L;

	@Id
	private long id;
	private String name;
	private String ccaa;
	
	public Countries() {
		// TODO Auto-generated constructor stub
	}

	public Countries(int id, String name, String ccaa) {
		super();
		this.id = id;
		this.name = name;
		this.ccaa = ccaa;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCcaa() {
		return ccaa;
	}

	public void setCcaa(String ccaa) {
		this.ccaa = ccaa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Countries other = (Countries) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
