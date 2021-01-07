package com.nnk.springboot.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@NotBlank(message = "Username is mandatory")
	private String username;
	@NotBlank(message = "Password is mandatory")
	private String password;
	@NotBlank(message = "FullName is mandatory")
	private String fullname;
	@NotBlank(message = "Role is mandatory")
	private String role;

	public User() {
		super();
	}

	public User(@NotBlank(message = "Username is mandatory") String username,
			@NotBlank(message = "Password is mandatory") String password,
			@NotBlank(message = "FullName is mandatory") String fullname,
			@NotBlank(message = "Role is mandatory") String role) {
		super();
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	public User(Integer id, @NotBlank(message = "Username is mandatory") String username,
			@NotBlank(message = "Password is mandatory") String password,
			@NotBlank(message = "FullName is mandatory") String fullname,
			@NotBlank(message = "Role is mandatory") String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullname = fullname;
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fullname == null) ? 0 : fullname.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (fullname == null) {
			if (other.fullname != null)
				return false;
		} else if (!fullname.equals(other.fullname))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
