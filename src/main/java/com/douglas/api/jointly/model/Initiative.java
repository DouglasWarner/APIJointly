package com.douglas.api.jointly.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Initiative {

	@JsonProperty("id")
	@Id
	@GeneratedValue
	private long id;
	@JsonProperty("name")
    private String name;
	@JsonProperty("created_at")
    private String createdAt;
	@JsonProperty("target_date")
    private String targetDate;
	@JsonProperty("description")
    private String description;
	@JsonProperty("target_area")
    private String targetArea;
	@JsonProperty("location")
    private String location;
	@JsonProperty("imagen")
    private byte[] imagen;
	@JsonProperty("target_amount")
    private int targetAmount;
	@JsonProperty("status")
    private String status;
	@JsonProperty("created_by")
    private String createdBy;
	@JsonProperty("ref_code")
	private String refCode;
	@JsonProperty("is_deleted")
	private boolean is_deleted;
	@JsonProperty("is_sync")
	private boolean is_sync;
    
    public Initiative() {
	}
        
    public Initiative(String name, String createdAt, String targetDate, String description,
    		String targetArea, String location, byte[] imagen, int targetAmount, String status, String createdBy,
    		String refCode) {
		super();
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

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
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
