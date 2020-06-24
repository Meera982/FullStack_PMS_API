package com.pms.jpa.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class ProjectEntity {
	
	@Id
    @GeneratedValue
    private int projectId;
     
    @Column(name="project", nullable=false)
    private String projectName;
    
    @Column(name="startDate")
    private Date startDate;
    
    @Column(name="endDate")
    private Date endDate;
    
    @Column(name="priority")
    private int priority;
    
    @OneToMany(mappedBy="task")
    private Set<TaskEntity> tasks;
    
    @OneToOne
    private UserEntity user;
    
    public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Set<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskEntity> tasks) {
		this.tasks = tasks;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
    public String toString() {
        return "ProjectEntity [projectId=" + projectId + ", projectName=" + projectName 
        		+"startDate="+startDate+"endDate="+endDate+"priority="+priority+ "]";
    }

}
