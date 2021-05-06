package com.douglas.api.jointly.model;

import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Initiative {
	
	@Id
	@GeneratedValue
	private int id;
    private String name;
    private GregorianCalendar createdAt;
    private GregorianCalendar targetDate;
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
		// TODO Auto-generated constructor stub
	}

    public Initiative(int id, String name, GregorianCalendar createdAt, GregorianCalendar targetDate, String description, String targetArea,
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GregorianCalendar getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(GregorianCalendar createdAt) {
		this.createdAt = createdAt;
	}

	public GregorianCalendar getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(GregorianCalendar targetDate) {
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
		result = prime * result + id;
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
