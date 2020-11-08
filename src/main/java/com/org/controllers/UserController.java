package com.org.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.models.Role;
import com.org.models.User;
import com.org.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserService userService;	
	
	@PostMapping("/user")
	public User addUser(@RequestBody User user) throws Exception {
		return this.userService.saveUser(user);
	}
	
	@PutMapping("/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User user) throws Exception {
		return this.userService.updateUser(user);
	}
	
	@GetMapping("/user/{id}")
	public User getUser(@PathVariable int id) throws Exception {
		return this.userService.getUser(id);
	}
	
	@GetMapping("/users")
	public List<Object> getUsers() {
		return this.userService.getUsers();
	}
	
	@GetMapping("/user")
	public Iterable<User> getUser() {
		return this.userService.getAllUser();
	}
	
	@GetMapping("/current/{token}")
	public User getUserByToken(@PathVariable String token) throws Exception {
		return this.userService.getUser(token);
	}
	
	@GetMapping("/roles")
	public Iterable<Role> getRoles() {
		return this.userService.getRoles();
	}
}
