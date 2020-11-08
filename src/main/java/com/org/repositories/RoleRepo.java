package com.org.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.org.models.Role;

@Repository
public interface RoleRepo extends CrudRepository<Role, Integer>{
	
}
