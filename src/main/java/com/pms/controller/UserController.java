package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.exception.RecordNotFoundException;
import com.pms.model.User;
import com.pms.service.UserService;

@RestController
@RequestMapping(path="/User")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(path="/listUsers", method=RequestMethod.GET)
	public @ResponseBody List<User> listUsers() throws RecordNotFoundException{
			return userService.getAllUsers();		
	}
	
	
	@RequestMapping(path="/deleteUser/{id}", method=RequestMethod.DELETE, produces="application/json")
	public @ResponseBody List<User> deleteUser(@PathVariable int id) throws RecordNotFoundException{	
			return userService.deleteUser(id);				
	}
	
	@RequestMapping(path="/createUser", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody List<User> createUser(@RequestBody User user) throws RecordNotFoundException{	
		return userService.createUser(user);	
	}
	
	@RequestMapping(path="/updateUser", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody List<User> updateUser(@RequestBody User user) throws RecordNotFoundException{	
		return userService.updateUser(user);					
	}

}
