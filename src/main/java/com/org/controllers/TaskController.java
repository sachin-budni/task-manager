package com.org.controllers;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.models.Task;
import com.org.models.User;
import com.org.services.TaskService;
import com.org.services.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/add-task/{id}")
	public Task saveTask(@RequestBody Task task, @PathVariable int id) throws Exception {
		User user = this.userService.getUser(id);
		task.setUser(user);
		return this.taskService.saveTask(task);
	}
	
	@PutMapping("/update-task/{id}")
	public Task updateTask(@RequestBody Task task, @PathVariable int id) throws Exception {
		User user = this.userService.getUser(id);
		task.setUser(user);
		return this.taskService.updateTask(task);
	}
	
	@DeleteMapping("/delete-task/{id}")
	public HashMap<String, String> deleteTask(@PathVariable int id) throws Exception {
		return this.taskService.deleteTask(id);
	}
	
	@GetMapping("/tasks/{id}")
	public Iterable<Task> getTasks(@PathVariable int id) {
		return this.taskService.getTaskByUser(id);
	}
	
	@GetMapping("/task/{id}")
	public Optional<Task> getTask(@PathVariable int id) {
		return this.taskService.getTask(id);
	}
	
	@GetMapping("/tasks")
	public Iterable<Task> getTask(){
		return this.taskService.getAllTask();
	}
}
