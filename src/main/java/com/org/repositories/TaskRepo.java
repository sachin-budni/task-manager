package com.org.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

import com.org.models.Task;

@Repository
public interface TaskRepo extends CrudRepository<Task, Integer> {
	public Iterable<Task> findByAssignto(int id);
	
}
