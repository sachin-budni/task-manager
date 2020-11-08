package com.org.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.org.models.User;
import com.org.repositories.UserRepo;
import com.org.services.MyUserDetails;
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepo userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userDao.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
		return new MyUserDetails(user);
//		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
//				new ArrayList<>());
	}
}
