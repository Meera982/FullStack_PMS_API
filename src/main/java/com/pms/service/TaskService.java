package com.pms.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pms.jpa.repository.TaskRespository;

@Service
public class TaskService {
	
	@Autowired
	TaskRespository taskRepository;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	

}
