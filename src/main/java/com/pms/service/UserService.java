package com.pms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.exception.RecordNotFoundException;
import com.pms.jpa.entity.UserEntity;
import com.pms.jpa.repository.UserRepository;
import com.pms.model.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUsers() throws RecordNotFoundException {
		List<UserEntity> userEntityList = userRepository.findAll();
		List<User> userList = null;
		if (userEntityList != null && !userEntityList.isEmpty()) {
			userList = new ArrayList<User>();
			for(UserEntity userEntity : userEntityList) {
				User userModel = new User();
				userModel.setEmpId(userEntity.getEmpId());
				userModel.setUserId(userEntity.getUserId());
				userModel.setFirstName(userEntity.getFirstName());
				userModel.setLastName(userEntity.getLastName());
				userList.add(userModel);
			}
			return userList;
		} else {
			throw new RecordNotFoundException("User list is empty");
		}		
	}
	
	public List<User> createUser(User user) throws RecordNotFoundException {	
		UserEntity userEntity = new UserEntity();
		userEntity.setEmpId(user.getEmpId());
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		userRepository.save(userEntity);
		return getAllUsers();
	}
	
	public List<User> updateUser(User user) throws RecordNotFoundException {	
		Optional<UserEntity>  userEntityFrmDB = userRepository.findById(user.getUserId());
		if(userEntityFrmDB.isPresent()) {
			UserEntity userEntity = userEntityFrmDB.get();
			userEntity.setEmpId(user.getEmpId());
			userEntity.setFirstName(user.getFirstName());
			userEntity.setLastName(user.getLastName());
			userRepository.save(userEntity);
			
			return getAllUsers();
		}else {
			throw new RecordNotFoundException("User does not exist");
		}	
	}
	
	public List<User> deleteUser(Integer id) throws RecordNotFoundException {
		Optional<UserEntity>  userEntityFrmDB = userRepository.findById(id);
		if(userEntityFrmDB.isPresent()) {
			UserEntity user = userEntityFrmDB.get();
			userRepository.delete(user);
			return getAllUsers();
		}else {
			throw new RecordNotFoundException("User does not exist");
		}
	}
		
		
	

}
