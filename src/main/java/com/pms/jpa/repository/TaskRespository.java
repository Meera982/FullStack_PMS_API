package com.pms.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.jpa.entity.TaskEntity;

public interface TaskRespository extends JpaRepository<TaskEntity, Integer>{
	
}
