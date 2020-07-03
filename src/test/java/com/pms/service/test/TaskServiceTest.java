package com.pms.service.test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.pms.jpa.entity.ParentTaskEntity;
import com.pms.jpa.entity.ProjectEntity;
import com.pms.jpa.entity.TaskEntity;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.ParentTaskRepository;
import com.pms.jpa.repository.ProjectRepository;
import com.pms.jpa.repository.TaskRespository;
import com.pms.jpa.repository.UserRepository;
import com.pms.model.ParentTask;
import com.pms.model.Project;
import com.pms.model.Task;
import com.pms.model.User;
import com.pms.service.TaskService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TaskService.class)
public class TaskServiceTest {
	
	@Autowired
	TaskService taskService;
	
	@MockBean
    private UserRepository userRepository;
	
	@MockBean
    private ProjectRepository projectRepository;
	
	@MockBean
    private TaskRespository taskRepository;
	
	@MockBean
    private ParentTaskRepository parentTaskRepository;
	
	private ProjectEntity project = new ProjectEntity();	
	private UserEntity user = new UserEntity(); 
	private TaskEntity task = new TaskEntity();
	private Set<TaskEntity> taskList = new HashSet<TaskEntity>();
	private Project projectModel = new Project();
	private User userModel = new User();
	private Task taskModel = new Task();
	private ParentTask parentTaskModel = new ParentTask();
	private ParentTaskEntity parentTaskEntity = new ParentTaskEntity();
			
	@Before
	public void setUp() {
		
		parentTaskModel.setParentTaskId(1);
		parentTaskModel.setParentTaskName("parentTAsk 1");
		
		parentTaskEntity.setParentId(1);
		parentTaskEntity.setParentTask("parentTAsk 1");
		
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
		task.setParentTask(parentTaskEntity);
		task.setProject(project);
		task.setUserInTask(user);
		
		taskList.add(task);
				
		project.setTasks(taskList);
		
		userModel.setFirstName("Meera");
		userModel.setLastName("krishnan");
		userModel.setEmpId("527362");
		userModel.setUserId(1);
		
		taskModel.setEndDate(new Date(02072020));
		taskModel.setPriority(1);
		taskModel.setParentTask(parentTaskModel);
		taskModel.setProject(projectModel);
		taskModel.setTaskName("abc");
		taskModel.setTaskId(1);
		taskModel.setTaskStatus("In Progresss");
		taskModel.setUser(userModel);
		taskModel.setStartDate(new Date(03072020));
		
		userModel.setFirstName("Meera");
		userModel.setLastName("krishnan");
		userModel.setEmpId("527362");
		userModel.setUserId(1);
        
		projectModel.setUser(userModel);
		
		List<Task> taskModelList = new ArrayList<Task>();
		
		projectModel.setTaskList(taskModelList);
		 
        List<ParentTaskEntity> parentTaskEntityList = new ArrayList<ParentTaskEntity>();
        
        parentTaskEntityList.add(parentTaskEntity);
        
	    Mockito.when(parentTaskRepository.findAll()).thenReturn(parentTaskEntityList);
	    Mockito.when(userRepository.getOne(1)).thenReturn(user);
	    Mockito.when(parentTaskRepository.getOne(1)).thenReturn(parentTaskEntity);
	    Mockito.when(projectRepository.getOne(1)).thenReturn(project);
	    Mockito.when(projectRepository.getOne(0)).thenReturn(project);
	    Mockito.when(taskRepository.getOne(1)).thenReturn(task);
	    
	}
    
    @Test
    public void testCreateTask() throws RecordNotFoundException { 
    	assert(taskService.createTask(taskModel));
    }
    
    @Test
    public void testUpdateTask() throws RecordNotFoundException { 
    	taskModel.setTaskId(1);
    	assert(taskService.updateTask(taskModel));
    }
    
    @Test
    public void testDeleteTask() throws RecordNotFoundException { 
    	assert(taskService.endTask(1));
    }
    
    @Test
    public void testGetAllUsers() throws RecordNotFoundException { 
    	List<ParentTask> projectList = taskService.getAllParentTasks();
        assert(projectList.size()==1);
    }

}
