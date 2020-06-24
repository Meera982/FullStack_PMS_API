package com.pms.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pms.jpa.entity.ProjectEntity;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer>{

}
