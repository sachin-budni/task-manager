package com.org.models;


import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {
	@Id
	@Column(name = "role_id")
	private int id;
	
	@Column(unique = true,nullable = false)
	private String name;
	
//	@ManyToMany(mappedBy = "roles")
//	Set<User> users;
	
//	public Set<User> getUsers() {
//		return users;
//	}
//	public void setUsers(Set<User> users) {
//		this.users = users;
//	}
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
}
