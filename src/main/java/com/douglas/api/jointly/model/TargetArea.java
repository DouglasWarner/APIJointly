package com.douglas.api.jointly.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TargetArea implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1606604993207831170L;

	@Id
	private long idTargetArea;
	private String name;
	private String description;
	
	public TargetArea() {
		// TODO Auto-generated constructor stub
	}

	public TargetArea(int idTargetArea, String name, String description) {
		super();
		this.idTargetArea = idTargetArea;
		this.name = name;
		this.description = description;
	}

	public long getIdTargetArea() {
		return idTargetArea;
	}

	public void setIdTargetArea(long idTargetArea) {
		this.idTargetArea = idTargetArea;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idTargetArea ^ (idTargetArea >>> 32));
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
		TargetArea other = (TargetArea) obj;
		if (idTargetArea != other.idTargetArea)
			return false;
		return true;
	}
}
