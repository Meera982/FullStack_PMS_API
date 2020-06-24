package com.pms.jpa.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskEntity {
	@Id
	@GeneratedValue
	private int taskId;

	@ManyToOne
	@JoinColumn(name = "projectId")
	private ProjectEntity project;

	@ManyToOne
	@JoinColumn(name = "parentId")
	private ParentTaskEntity parentTask;

	@Column(name = "task", nullable = false)
	private String taskName;

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "task_status", nullable = false)
	private String taskStatus;
	
	@OneToOne
    private UserEntity user;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public ParentTaskEntity getParentTask() {
		return parentTask;
	}

	public void setParentTask(ParentTaskEntity parentTask) {
		this.parentTask = parentTask;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
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

	public String getTaskStatus() {
		return taskStatus;
	}

	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TaskEntity [taskId=" + taskId + ", project=" + project + ", parentTask=" + parentTask + ", taskName="
				+ taskName + ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority
				+ ", taskStatus=" + taskStatus + "]";
	}

}
