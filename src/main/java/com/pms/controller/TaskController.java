package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.exception.RecordNotFoundException;
import com.pms.model.ParentTask;
import com.pms.model.Task;
import com.pms.service.TaskService;

@RestController
@RequestMapping(path="/Task")
public class TaskController {
	
	@Autowired
	TaskService taskService;
	
	@RequestMapping(path="/listParentTasks", method=RequestMethod.GET)
	public @ResponseBody List<ParentTask> listParentTasks() throws RecordNotFoundException{
			return taskService.getAllParentTasks();	
	}
	
	
	@RequestMapping(path="/endTask/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String endTask(@PathVariable int id) throws RecordNotFoundException{	
		 taskService.endTask(id);	
		 return "success";
	}
	
	@RequestMapping(path="/createTask", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String createProject(@RequestBody Task task) throws RecordNotFoundException{	
			taskService.createTask(task);	
			return "success";
	}
	
	@RequestMapping(path="/updateTask", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody String updateTask(@RequestBody Task task) throws RecordNotFoundException{	
		taskService.updateTask(task);
		return "success";
	}

}
