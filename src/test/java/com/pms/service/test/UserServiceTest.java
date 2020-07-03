package com.pms.service.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.pms.exception.RecordNotFoundException;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.UserRepository;
import com.pms.model.User;
import com.pms.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserService.class)
public class UserServiceTest {
	
	@Autowired
	UserService userService;
	
	@MockBean
    private UserRepository userRepository;
	
	private UserEntity user = new UserEntity(); 
	private User userModel = new User();  
		
	@Before
	public void setUp() {
		 
        user.setFirstName("Meera");
        user.setLastName("krishnan");
        user.setEmpId("527362");
        user.setUserId(1);
        
        userModel.setFirstName("Meera");
        userModel.setLastName("krishnan");
        userModel.setEmpId("527362");
        
        List<UserEntity> userList = new ArrayList<UserEntity>();
        
        userList.add(user);
	 
	    Mockito.when(userRepository.findAll()).thenReturn(userList);
	    Mockito.when(userRepository.findById(1)).thenReturn(Optional.of(user));
	    
	}
    
    @Test
    public void testCreateUser() throws RecordNotFoundException { 
    	List<User> userList = userService.createUser(userModel);
        assert(userList.get(0).getEmpId().equals(userModel.getEmpId()));
    }
    
    @Test
    public void testUpdateUser() throws RecordNotFoundException { 
    	userModel.setUserId(1);
    	List<User> userList = userService.updateUser(userModel);
        assert(userList.get(0).getEmpId().equals(userModel.getEmpId()));
    }
    
    @Test
    public void testDeleteUser() throws RecordNotFoundException { 
    	List<User> userList = userService.deleteUser(1);
        assert(userList.get(0).getEmpId().equals(userModel.getEmpId()));
    }
    
    @Test
    public void testGetAllUsers() throws RecordNotFoundException { 
    	List<User> userList = userService.getAllUsers();
        assert(userList.get(0).getEmpId().equals(userModel.getEmpId()));
    }

}
