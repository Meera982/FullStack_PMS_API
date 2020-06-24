package com.pms.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.exception.RecordNotFoundException;
import com.pms.jpa.entity.ProjectEntity;
import com.pms.jpa.entity.TaskEntity;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.ProjectRepository;
import com.pms.model.Project;
import com.pms.model.Task;
import com.pms.model.User;

@Service
public class ProjectService {

	@Autowired
	ProjectRepository projectRepository;

	public List<Project> getAllProjects() throws RecordNotFoundException {
		List<ProjectEntity> projectEntityList = projectRepository.findAll();
		List<Project> projectList = null;
		if (projectEntityList != null && !projectEntityList.isEmpty()) {
			projectList = new ArrayList<Project>();
			for (ProjectEntity projectEntity : projectEntityList) {
				Project projectModel = new Project();
				projectModel.setEndDate(projectEntity.getEndDate());
				projectModel.setPriority(projectEntity.getPriority());
				projectModel.setProjectName(projectEntity.getProjectName());
				projectModel.setStartDate(projectEntity.getStartDate());
				projectModel.setProjectId(projectEntity.getProjectId());
				User user = new User();
				user.setFirstName(projectEntity.getUser().getFirstName());
				user.setLastName(projectEntity.getUser().getLastName());
				user.setEmpId(projectEntity.getUser().getEmpId());
				user.setUserId(projectEntity.getUser().getUserId());
				projectModel.setUser(user);
				if(projectEntity.getTasks() != null && !projectEntity.getTasks().isEmpty()) {
					List<Task> taskList = new ArrayList<Task>();
					Iterator<TaskEntity> it = projectEntity.getTasks().iterator();
				     while(it.hasNext()){
				    	TaskEntity taskEntity = it.next();
				        Task task = new Task();
				        task.setEndDate(taskEntity.getEndDate());
				        task.setPriority(taskEntity.getPriority());
				        task.setStartDate(taskEntity.getStartDate());
				        task.setTaskId(taskEntity.getTaskId());
				        task.setTaskName(taskEntity.getTaskName());
				        task.setTaskStatus(taskEntity.getTaskStatus());
				        taskList.add(task);
				     }
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
		UserEntity userEntity = new UserEntity();
		userEntity.setEmpId(project.getUser().getEmpId());
		userEntity.setFirstName(project.getUser().getFirstName());
		userEntity.setLastName(project.getUser().getLastName());
		projectEntity.setUser(userEntity);
		projectRepository.save(projectEntity);
		return getAllProjects();
	}

	public ProjectEntity updateProject(Project project) throws RecordNotFoundException {
		Optional<ProjectEntity> projectEntityFrmDB = projectRepository.findById(project.getProjectId());
		if (projectEntityFrmDB.isPresent()) {
			ProjectEntity projectEntity = projectEntityFrmDB.get();
			projectEntity.setEndDate(project.getEndDate());
			projectEntity.setPriority(project.getPriority());
			projectEntity.setProjectName(project.getProjectName());
			projectEntity.setStartDate(project.getStartDate());
			UserEntity userEntity = new UserEntity();
			userEntity.setEmpId(project.getUser().getEmpId());
			userEntity.setFirstName(project.getUser().getFirstName());
			userEntity.setLastName(project.getUser().getLastName());
			projectEntity.setUser(userEntity);
			projectRepository.save(projectEntity);

			return projectEntity;
		} else {
			throw new RecordNotFoundException("Project does not exist");
		}
	}

	public void deleteProject(Integer id) throws RecordNotFoundException {
		Optional<ProjectEntity> projectEntityFrmDB = projectRepository.findById(id);
		if (projectEntityFrmDB.isPresent()) {
			ProjectEntity project = projectEntityFrmDB.get();
			projectRepository.delete(project);
		} else {
			throw new RecordNotFoundException("Project does not exist");
		}
	}

}
