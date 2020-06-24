package com.pms.jpa.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="parent_task")
public class ParentTaskEntity {
	
	@Id
    @GeneratedValue
    private int parentId;
     
    @Column(name="parent_task", nullable=false)
    private String parentTask; 
    
    @OneToMany(mappedBy="parentTask")
    private Set<TaskEntity> tasks;
    

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}
	
	@Override
    public String toString() {
        return "ParentTaskEntity [parentId=" + parentId + ", parentTask=" + parentTask +"]";
    }

}
