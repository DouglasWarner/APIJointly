package com.douglas.api.jointly.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Initiative {
	
	@Id
	@GeneratedValue
	private long id;
    private String name;
    private String createdAt;
    private String targetDate;
    private String description;
    private String targetArea;
    private String location;
    private byte[] imagen;
    private int targetAmount;
    private String status;
    private String createdBy;
	private String refCode;
    
    //private int countUserJoined;
    
    public Initiative() {
	}
        
    public Initiative(String name, String targetDate, String description,
			String targetArea, String location, byte[] imagen, int targetAmount, String status, String createdBy,
			String refCode) {
		super();
		this.name = name;
		this.targetDate = targetDate;
		this.description = description;
		this.targetArea = targetArea;
		this.location = location;
		this.imagen = imagen;
		this.targetAmount = targetAmount;
		this.status = status;
		this.createdBy = createdBy;
		this.refCode = refCode;
	}

	public Initiative(long id, String name, String createdAt, String targetDate, String description, String targetArea,
			String location, byte[] imagen, int targetAmount, String status, String createdBy, String refCode) {
		super();
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.targetDate = targetDate;
		this.description = description;
		this.targetArea = targetArea;
		this.location = location;
		this.imagen = imagen;
		this.targetAmount = targetAmount;
		this.status = status;
		this.createdBy = createdBy;
		this.refCode = refCode;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(String targetDate) {
		this.targetDate = targetDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTargetArea() {
		return targetArea;
	}

	public void setTargetArea(String targetArea) {
		this.targetArea = targetArea;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public int getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(int targetAmount) {
		this.targetAmount = targetAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRefCode() {
		return refCode;
	}

	public void setRefCode(String refCode) {
		this.refCode = refCode;
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
		Initiative other = (Initiative) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
