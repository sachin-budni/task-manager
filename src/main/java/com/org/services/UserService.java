package com.org.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.org.configurations.JwtTokenUtil;
import com.org.models.Role;
import com.org.models.User;
import com.org.repositories.RoleRepo;
import com.org.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private NotificationService notificationService ;
	
	public User getUser(int id) throws Exception {
		return this.userRepo.findById(id).orElseThrow(() -> new Exception("Can not Find User"));
	}
	
	public User saveUser(User user) throws Exception {
		user.setId(new Random().nextInt(999999));
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<Role>();
		Role role = roleRepo.findById(3).orElseThrow(() -> new Exception("can you find role"));
		roles.add(role);
		user.setRoles(roles);
		try {
			notificationService.sendNotification(user);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return this.userRepo.save(user);
	}
	
	public User updateUser(User user) throws Exception {
		User user1 = this.getUser(user.getId());
		if(user1 != null) {			
			return this.userRepo.save(user);
		}
		return null;
	}
	
	public List<Object> getUsers() {
		List<Object> list = new ArrayList<Object>();
		Iterable<User> users= this.userRepo.findAll();
		for (User user : users) {
			HashMap use = new HashMap();
			use.put("id", user.getId());
			use.put("username",user.getUsername());
			list.add(use);
		}
		return list;
	}
	
	public Iterable<User> getAllUser() {
		return this.userRepo.findAll();
	}
	
	public Iterable<Role> getRoles() {
		return this.roleRepo.findAll();
	}
	
	public User getUser(String token) {
		String email = this.jwtTokenUtil.getEmailFromToken(token);
		return this.userRepo.findByEmail(email);
	}
}
