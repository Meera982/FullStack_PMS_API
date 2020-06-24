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
import com.pms.model.Project;
import com.pms.model.User;
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
	
	
	@RequestMapping(path="/deleteProject/{id}", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody String deleteProject(@PathVariable int id) throws RecordNotFoundException{	
			projectService.deleteProject(id);
			return "success";		
	}
	
	@RequestMapping(path="/createProject", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody Project createProject(@RequestParam Project project) throws RecordNotFoundException{	
			projectService.createProject(project);
			return project;		
	}
	
	@RequestMapping(path="/updateProjects", method=RequestMethod.POST, produces="application/json")
	public @ResponseBody Project updateProjects(@RequestParam Project project) throws RecordNotFoundException{	
			projectService.updateProject(project);
			return project;		
	}
}
