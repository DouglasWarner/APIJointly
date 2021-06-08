package com.douglas.api.jointly.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class User {

	@JsonProperty("id")
	@Id
	@GeneratedValue
	private int id;
	@JsonProperty("email")
    private String email;
	@JsonProperty("password")
    private String password;
	@JsonProperty("name")
    private String name;
	@JsonProperty("phone")
    private String phone;
	@JsonProperty("imagen")
    private byte[] imagen;
	@JsonProperty("location")
    private String location;
	@JsonProperty("description")
    private String description;
	@JsonProperty("created_at")
    private String createdAt;
	@JsonProperty("is_deleted")
    private boolean is_deleted;
	@JsonProperty("is_sync")
    private boolean is_sync;
    
    public User() {
	}

	public User(int id, String email, String password, String name, String phone, byte[] imagen, String location,
			String description, String createdAt) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.imagen = imagen;
		this.location = location;
		this.description = description;
		this.createdAt = createdAt;
	}

	public User(String email, String password, String name, String phone, byte[] imagen, String location,
			String description, String createdAt) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.imagen = imagen;
		this.location = location;
		this.description = description;
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
