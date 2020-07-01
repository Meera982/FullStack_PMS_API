package com.pms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.exception.RecordNotFoundException;
import com.pms.jpa.entity.ParentTaskEntity;
import com.pms.jpa.entity.ProjectEntity;
import com.pms.jpa.entity.TaskEntity;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.ProjectRepository;
import com.pms.jpa.repository.UserRepository;
import com.pms.model.ParentTask;
import com.pms.model.Project;
import com.pms.model.Task;
import com.pms.model.User;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;
	
	@Autowired
	UserRepository userRepository;

	public List<Project> getAllProjects() throws RecordNotFoundException {
		List<ProjectEntity> projectEntityList = projectRepository.findAll();
		List<Project> projectList = null;
		Date todayDate = new Date();
		if (projectEntityList != null && !projectEntityList.isEmpty()) {
			projectList = new ArrayList<Project>();
			for (ProjectEntity projectEntity : projectEntityList) {
				Project projectModel = new Project();
				projectModel.setEndDate(projectEntity.getEndDate());
				projectModel.setPriority(projectEntity.getPriority());
				projectModel.setProjectName(projectEntity.getProjectName());
				projectModel.setStartDate(projectEntity.getStartDate());
				projectModel.setProjectId(projectEntity.getProjectId());
				if(projectEntity.getEndDate() != null) {
					projectModel.setStatus(projectEntity.getEndDate().before(todayDate)?"Yes":"No");
				}else {
					projectModel.setStatus("No");
				}
				
				if(projectEntity.getUserInProject() != null) {
					projectModel.setManagerName(projectEntity.getUserInProject().getFirstName()+" "+projectEntity.getUserInProject().getLastName());
				}
				
				if(projectEntity.getTasks() != null && !projectEntity.getTasks().isEmpty()) {
					List<Task> taskList = new ArrayList<Task>();
					int count = 0;
					Iterator<TaskEntity> it = projectEntity.getTasks().iterator();
				     while(it.hasNext()){
				    	 Task taskModel = new Task();
				    	 TaskEntity taskEntity = it.next();
							taskModel.setEndDate(taskEntity.getEndDate());
							if(taskEntity.getParentTask() != null) {
								ParentTaskEntity parentTaskEntity = taskEntity.getParentTask();
								ParentTask parentTask = new ParentTask();
								parentTask.setParentTaskId(parentTaskEntity.getParentId());
								parentTask.setParentTaskName(parentTaskEntity.getParentTask());
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
				        count++;
				     }
				     projectModel.setTaskList(taskList);	
				     projectModel.setTaskCount(count);
				     
				}
				projectList.add(projectModel);
			}
			return projectList;
		} else {
			throw new RecordNotFoundException("Project list is empty");
		}
	}

	public List<Project> createProject(Project project) throws RecordNotFoundException {
		ProjectEntity projectEntity = new ProjectEntity();
		projectEntity.setEndDate(project.getEndDate());
		projectEntity.setPriority(project.getPriority());
		projectEntity.setProjectName(project.getProjectName());
		projectEntity.setStartDate(project.getStartDate());
		if(project.getUser() != null) {
			Optional<UserEntity> user = userRepository.findById(project.getUser().getUserId());
			UserEntity userEntity = user.get();
			userEntity.setProject(projectEntity);
			//userRepository.save(userEntity);
			projectEntity.setUserInProject(userEntity);
		}
		projectRepository.save(projectEntity);
		return getAllProjects();
	}

	public List<Project> updateProject(Project project) throws RecordNotFoundException {
		Optional<ProjectEntity> projectEntityFrmDB = projectRepository.findById(project.getProjectId());
		if (projectEntityFrmDB.isPresent()) {
			ProjectEntity projectEntity = projectEntityFrmDB.get();
			projectEntity.setEndDate(project.getEndDate());
			projectEntity.setPriority(project.getPriority());
			projectEntity.setProjectName(project.getProjectName());
			projectEntity.setStartDate(project.getStartDate());
			
			if(project.getUser() != null) {
				UserEntity user = userRepository.getOne(projectEntity.getUserInProject().getUserId());
				user.setProject(null);
				
				UserEntity newUser = userRepository.getOne(project.getUser().getUserId());
				newUser.setProject(projectEntity);
				projectEntity.setUserInProject(newUser);				
			}
			
			projectRepository.save(projectEntity);

			return getAllProjects();
		} else {
			throw new RecordNotFoundException("Project does not exist");
		}
	}

	public List<Project> deleteProject(Integer id) throws RecordNotFoundException {
		Optional<ProjectEntity> projectEntityFrmDB = projectRepository.findById(id);
		Optional<UserEntity> userEntityFrmDB = userRepository.findById(projectEntityFrmDB.get().getUserInProject().getUserId());
		if (projectEntityFrmDB.isPresent()) {
			UserEntity user = userEntityFrmDB.get();
			user.setProject(null);
			userRepository.save(user);
			ProjectEntity project = projectEntityFrmDB.get();
			projectRepository.delete(project);
			return getAllProjects();
		} else {
			throw new RecordNotFoundException("Project does not exist");
		}
	}

}
