package com.pms.jpa.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@OneToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "project_id", referencedColumnName = "project_id")
	private ProjectEntity project;

	@OneToOne(fetch=FetchType.EAGER ,cascade = CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "task_id", referencedColumnName = "task_id")
	private TaskEntity task;

	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "employee_id", nullable = false)
	private String empId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ProjectEntity getProject() {
		return project;
	}

	public void setProject(ProjectEntity project) {
		this.project = project;
	}

	public TaskEntity getTask() {
		return task;
	}

	public void setTask(TaskEntity task) {
		this.task = task;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", project=" + project + ", task=" + task + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", empId=" + empId + "]";
	}

	

}
