package com.org.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.models.Task;
import com.org.models.User;
import com.org.repositories.TaskRepo;
import com.org.repositories.UserRepo;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepo taskRepo;
	
	public Optional<Task> getTask(int id) {
		return this.taskRepo.findById(id);
	}
	
	public Iterable<Task> getTaskByUser(int id) {
		return this.taskRepo.findByAssignto(id);
	}
	
	public Iterable<Task> getAllTask() {
		return this.taskRepo.findAll();
	}
	
	public Task saveTask(Task task) {
		task.setId(new Random().nextInt(999999));
		return this.taskRepo.save(task);
	}
	
	public Task updateTask(Task task) {
		return this.taskRepo.save(task);
	}
	
	public HashMap<String, String> deleteTask(int id) throws Exception {
		try {
			Task task = this.taskRepo.findById(id).orElseThrow(() -> new Exception("Task is not found"));
			if(task != null) {
				this.taskRepo.delete(task);
//				this.taskRepo.deleteById(id);
			}
		} catch(Exception e) {
			throw new Exception(e);
		}
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("message", "delete successufully");
		return map;
	}
}
