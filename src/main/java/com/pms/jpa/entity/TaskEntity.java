package com.pms.jpa.entity;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "task")
public class TaskEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="task_id")
	private int taskId;

	@ManyToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinColumn(name = "project_id", referencedColumnName = "project_id")
	private ProjectEntity project;

	@ManyToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL)
	@JoinColumn(name = "parent_id", referencedColumnName = "parent_id")
	private ParentTaskEntity parentTask;

	@Column(name = "task_name", nullable = false)
	private String taskName;

	@Column(name = "startDate")
	private Date startDate;

	@Column(name = "endDate")
	private Date endDate;

	@Column(name = "priority")
	private int priority;

	@Column(name = "task_status", nullable = false)
	private String taskStatus;
	
	@OneToOne(mappedBy="task")
    private UserEntity userInTask;
	
	
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

	public UserEntity getUserInTask() {
		return userInTask;
	}

	public void setUserInTask(UserEntity userInTask) {
		this.userInTask = userInTask;
	}

	@Override
	public String toString() {
		return "TaskEntity [taskId=" + taskId + ", project=" + project + ", parentTask=" + parentTask + ", taskName="
				+ taskName + ", startDate=" + startDate + ", endDate=" + endDate + ", priority=" + priority
				+ ", taskStatus=" + taskStatus + "]";
	}

}
