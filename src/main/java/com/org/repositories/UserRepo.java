package com.org.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.models.User;

@Repository
public interface UserRepo extends CrudRepository<User, Integer> {
	public User findByEmail(String email);
}
