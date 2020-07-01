package com.pms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.jpa.entity.ParentTaskEntity;
import com.pms.jpa.entity.ProjectEntity;
import com.pms.jpa.entity.TaskEntity;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.ParentTaskRepository;
import com.pms.jpa.repository.ProjectRepository;
import com.pms.jpa.repository.TaskRespository;
import com.pms.jpa.repository.UserRepository;
import com.pms.model.ParentTask;
import com.pms.model.Task;

@Service
public class TaskService {
	
	@Autowired
	TaskRespository taskRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ParentTaskRepository parentTaskRepository;
	
	/*public List<Task> getAllTasks() throws RecordNotFoundException{
		List<TaskEntity> taskEntityList = taskRepository.findById(id)
		List<Task> taskList = null;
		if(taskEntityList != null && !taskEntityList.isEmpty()) {
			taskList = new ArrayList<Task>();
			for(TaskEntity taskEntity:  taskEntityList) {
				Task taskModel = new Task();
				taskModel.setEndDate(taskEntity.getEndDate());
				taskModel.setParentTask(false);
				if(taskEntity.getParentTask() != null) {
					ParentTaskEntity parentTaskEntity = taskEntity.getParentTask();
					Task parentTask = new Task();
					parentTask.setTaskId(parentTaskEntity.getParentId());
					parentTask.setTaskName(parentTaskEntity.getParentTask());
					taskModel.setParentTask(parentTask);
				}
				taskModel.setPriority(taskEntity.getPriority());
				taskModel.setStartDate(taskEntity.getStartDate());
				taskModel.setTaskId(taskEntity.getTaskId());
				taskModel.setTaskName(taskEntity.getTaskName());
				taskModel.setTaskStatus(taskEntity.getTaskStatus());
				if(taskEntity.getUserInTask() != null) {
					User user = new User();
					user.setEmpId(taskEntity.getUserInTask().getEmpId());
					user.setFirstName(taskEntity.getUserInTask().getFirstName());
					user.setLastName(taskEntity.getUserInTask().getLastName());
					user.setUserId(taskEntity.getUserInTask().getUserId());
					taskModel.setUser(user);
				}
				
				taskList.add(taskModel);
			}
		}
		return taskList;
	}*/
	
	public void createTask(Task task) {
		if(task.getEndDate() != null && task.getStartDate() != null) {
			TaskEntity taskEntity = new TaskEntity();
			taskEntity.setEndDate(task.getEndDate());			
			taskEntity.setPriority(task.getPriority());
			taskEntity.setStartDate(task.getStartDate());
			taskEntity.setTaskName(task.getTaskName());
			taskEntity.setTaskStatus("In Progress");
			if(task.getParentTask() != null) {
				ParentTaskEntity parentTaskEntity = parentTaskRepository.getOne(task.getParentTask().getParentTaskId());
				parentTaskEntity.getTasks().add(taskEntity);
				taskEntity.setParentTask(parentTaskEntity);
			}
			if(task.getUser() != null) {
				UserEntity userInTask = userRepository.getOne(task.getUser().getUserId()); 
				userInTask.setTask(taskEntity);
				taskEntity.setUserInTask(userInTask);
			}
			
			if(task.getProject() != null) {
				ProjectEntity projectInTask = projectRepository.getOne(task.getProject().getProjectId()); 
				projectInTask.getTasks().add(taskEntity);
				taskEntity.setProject(projectInTask);
			}
			taskRepository.save(taskEntity);			
		}else {
			ParentTaskEntity parentTaskEntity = new ParentTaskEntity();
			parentTaskEntity.setParentTask(task.getTaskName());
			parentTaskRepository.save(parentTaskEntity);
		}
		
	}
	
	public void updateTask(Task task) {
		if(task.getEndDate() != null && task.getStartDate() != null) {
			TaskEntity taskEntity = taskRepository.getOne(task.getTaskId());
			taskEntity.setEndDate(task.getEndDate());			
			taskEntity.setPriority(task.getPriority());
			taskEntity.setStartDate(task.getStartDate());
			taskEntity.setTaskName(task.getTaskName());
			taskEntity.setTaskStatus("In Progress");
			if(task.getParentTask() != null) {
				ParentTaskEntity oldParentTaskEntity = taskEntity.getParentTask();
				oldParentTaskEntity.getTasks().remove(taskEntity);
				
				ParentTaskEntity parentTaskEntity = parentTaskRepository.getOne(task.getParentTask().getParentTaskId());
				parentTaskEntity.getTasks().add(taskEntity);
				taskEntity.setParentTask(parentTaskEntity);
			}
			if(task.getUser() != null) {
				UserEntity oldUserInTask = userRepository.getOne(taskEntity.getUserInTask().getUserId()); 
				oldUserInTask.setTask(null);
				
				UserEntity userInTask = userRepository.getOne(task.getUser().getUserId()); 
				userInTask.setTask(taskEntity);
				taskEntity.setUserInTask(userInTask);
			}
			
			if(task.getProject() != null) {
				ProjectEntity oldProjectInTask = taskEntity.getProject();
				oldProjectInTask.getTasks().remove(taskEntity);
				
				ProjectEntity projectInTask = projectRepository.getOne(task.getProject().getProjectId()); 
				projectInTask.getTasks().add(taskEntity);
				taskEntity.setProject(projectInTask);
			}
			taskRepository.save(taskEntity);			
		}else {
			ParentTaskEntity parentTaskEntity = new ParentTaskEntity();
			parentTaskEntity.setParentTask(task.getTaskName());
			parentTaskRepository.save(parentTaskEntity);
		}
		
	}
	
	
	public void endTask(int id) {
		TaskEntity taskEntity = taskRepository.getOne(id);
		taskEntity.setTaskStatus("Completed");
		taskRepository.save(taskEntity);
	}
	
	public List<ParentTask> getAllParentTasks() {
		List<ParentTaskEntity> parentTaskList = parentTaskRepository.findAll();
		List<ParentTask> taskList = new ArrayList<ParentTask>();
		if(parentTaskList != null) {
			for(ParentTaskEntity parenTaskEntity : parentTaskList) {
				ParentTask task = new ParentTask();
				task.setParentTaskName(parenTaskEntity.getParentTask());
				task.setParentTaskId(parenTaskEntity.getParentId());
				taskList.add(task);
			}
		 
		}
		return taskList;
	}

	
	
	

}
