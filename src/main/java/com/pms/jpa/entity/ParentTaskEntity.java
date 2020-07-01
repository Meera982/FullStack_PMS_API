package com.pms.jpa.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="parent_task")
public class ParentTaskEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="parent_id")
    private int parentId;
     
    @Column(name="parent_task_name", nullable=false)
    private String parentTaskName; 
    
    @OneToMany(mappedBy="parentTask", cascade = CascadeType.ALL)
    private Set<TaskEntity> tasks = new HashSet<TaskEntity>();
    

	public Set<TaskEntity> getTasks() {
		return tasks;
	}

	public void setTasks(Set<TaskEntity> tasks) {
		this.tasks = tasks;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTaskName;
	}

	public void setParentTask(String parentTask) {
		this.parentTaskName = parentTask;
	}
	
	@Override
    public String toString() {
        return "ParentTaskEntity [parentId=" + parentId + ", parentTask=" + parentTaskName +"]";
    }

}
