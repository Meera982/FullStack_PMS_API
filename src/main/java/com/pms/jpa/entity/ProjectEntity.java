package com.pms.jpa.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class ProjectEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
    private int projectId;
     
    @Column(name="project_name", nullable=false)
    private String projectName;
    
    @Column(name="startDate")
    private Date startDate;
    
    @Column(name="endDate")
    private Date endDate;
    
    @Column(name="priority")
    private int priority;
    
    @OneToMany(mappedBy="project", cascade = CascadeType.ALL)
    private Set<TaskEntity> tasks;
    
    @OneToOne(mappedBy="project")
	private UserEntity userInProject;
    
        
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

	
	public UserEntity getUserInProject() {
		return userInProject;
	}

	public void setUserInProject(UserEntity userInProject) {
		this.userInProject = userInProject;
	}

	@Override
    public String toString() {
        return "ProjectEntity [projectId=" + projectId + ", projectName=" + projectName 
        		+"startDate="+startDate+"endDate="+endDate+"priority="+priority+ "]";
    }

}
