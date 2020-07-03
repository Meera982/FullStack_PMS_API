package com.pms.service.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pms.exception.RecordNotFoundException;
import com.pms.jpa.entity.ProjectEntity;
import com.pms.jpa.entity.TaskEntity;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.ProjectRepository;
import com.pms.jpa.repository.UserRepository;
import com.pms.model.Project;
import com.pms.model.User;
import com.pms.service.ProjectService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectService.class)
public class ProjectServiceTest {
	
	@Autowired
	ProjectService projectService;
	
	@MockBean
    private UserRepository userRepository;
	
	@MockBean
    private ProjectRepository projectRepository;
	
	private ProjectEntity project = new ProjectEntity();	
	private UserEntity user = new UserEntity(); 
	private TaskEntity task = new TaskEntity();
	private Set<TaskEntity> taskList = new HashSet<TaskEntity>();
	private Project projectModel = new Project();
	private User userModel = new User();
			
	@Before
	public void setUp() {
		
		user.setFirstName("Meera");
        user.setLastName("krishnan");
        user.setEmpId("527362");
        user.setUserId(1);
		
		project.setEndDate(new Date(02072020));
		project.setPriority(1);
		project.setProjectId(1);
		project.setProjectName("abc");
		project.setStartDate(new Date(03072020));
		project.setUserInProject(user);
		
		task.setEndDate(new Date(02072020));
		task.setPriority(1);
		task.setStartDate(new Date(03072020));
		task.setTaskId(1);
		task.setTaskName("task 1");
		task.setTaskStatus("Completed");
		
		taskList.add(task);
				
		project.setTasks(taskList);
		
		userModel.setFirstName("Meera");
		userModel.setLastName("krishnan");
		userModel.setEmpId("527362");
		userModel.setUserId(1);
		
		projectModel.setEndDate(new Date(02072020));
		projectModel.setPriority(1);
		projectModel.setProjectId(1);
		projectModel.setProjectName("abc");
		projectModel.setStartDate(new Date(03072020));
		
		userModel.setFirstName("Meera");
		userModel.setLastName("krishnan");
		userModel.setEmpId("527362");
		userModel.setUserId(1);
        
		projectModel.setUser(userModel);
		 
        
        List<ProjectEntity> projectList = new ArrayList<ProjectEntity>();
        
        projectList.add(project);
	 
	    Mockito.when(projectRepository.findAll()).thenReturn(projectList);
	    Mockito.when(projectRepository.findById(1)).thenReturn(Optional.of(project));
	    Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
	    Mockito.when(userRepository.getOne(1)).thenReturn(user);
	    
	}
    
    @Test
    public void testCreateProject() throws RecordNotFoundException { 
    	List<Project> userList = projectService.createProject(projectModel);
        assert(userList.get(0).getProjectId()==(project.getProjectId()));
    }
    
    @Test
    public void testUpdateUser() throws RecordNotFoundException { 
    	projectModel.setProjectId(1);
    	List<Project> projectList = projectService.updateProject(projectModel);
        assert(projectList.get(0).getProjectId()==(projectModel.getProjectId()));
    }
    
    @Test
    public void testDeleteUser() throws RecordNotFoundException { 
    	List<Project> projectList = projectService.deleteProject(1);
        assert(projectList.get(0).getProjectId()==(projectModel.getProjectId()));
    }
    
    @Test
    public void testGetAllUsers() throws RecordNotFoundException { 
    	List<Project> projectList = projectService.getAllProjects();
        assert(projectList.get(0).getProjectId()==(projectModel.getProjectId()));
    }

}
