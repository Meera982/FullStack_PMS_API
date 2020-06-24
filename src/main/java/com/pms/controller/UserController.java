package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	
	@RequestMapping(path="/deleteUser/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String deleteUser(@PathVariable int id) throws RecordNotFoundException{	
			userService.deleteUser(id);
			return "success";		
	}
	
	@RequestMapping(path="/createUser", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody User createUser(@RequestParam User user) throws RecordNotFoundException{	
			userService.createUser(user);
			return user;		
	}
	
	@RequestMapping(path="/updateUser", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody User updateUser(@RequestParam User user) throws RecordNotFoundException{	
			userService.updateUser(user);
			return user;		
	}

}
