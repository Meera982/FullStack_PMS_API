package com.pms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pms.exception.RecordNotFoundException;
import com.pms.model.Project;
import com.pms.service.ProjectService;

@RestController
@RequestMapping(path="/Project")
public class ProjectController {
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(path="/listProjects", method=RequestMethod.GET)
	public @ResponseBody List<Project> listProjects() throws RecordNotFoundException{
			return projectService.getAllProjects();		
	}
	
	
	@RequestMapping(path="/deleteProject/{id}", method=RequestMethod.DELETE, produces="application/json")
	public @ResponseBody List<Project> deleteProject(@PathVariable int id) throws RecordNotFoundException{	
		return projectService.deleteProject(id);					
	}
	
	@RequestMapping(path="/createProject", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody List<Project> createProject(@RequestBody Project project) throws RecordNotFoundException{	
			return projectService.createProject(project);				
	}
	
	@RequestMapping(path="/updateProjects", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody List<Project> updateProjects(@RequestBody Project project) throws RecordNotFoundException{	
		return projectService.updateProject(project);					
	}
}
