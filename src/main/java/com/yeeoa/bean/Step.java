package com.yeeoa.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Step implements Serializable{

	public Step() {
	}

	private static final long serialVersionUID = -339516038496531943L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private int taskID;
	private String stepID;
	private String estimatedTimes;
	private String stepActions;
	private String stepDetails;
	private String displayPage;
	private String page;
	private String tableResource;
	private String status;
	private Task task;
	private int id;
	private Timestamp createTime;
	private byte enabled;

	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public String getStepID() {
		return stepID;
	}

	public void setStepID(String stepID) {
		this.stepID = stepID;
	}

	public String getEstimatedTimes() {
		return estimatedTimes;
	}

	public void setEstimatedTimes(String estimatedTimes) {
		this.estimatedTimes = estimatedTimes;
	}

	public String getStepActions() {
		return stepActions;
	}

	public void setStepActions(String stepActions) {
		this.stepActions = stepActions;
	}

	public String getStepDetails() {
		return stepDetails;
	}

	public void setStepDetails(String stepDetails) {
		this.stepDetails = stepDetails;
	}

	public String getDisplayPage() {
		return displayPage;
	}

	public void setDisplayPage(String displayPage) {
		this.displayPage = displayPage;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getTableResource() {
		return tableResource;
	}

	public void setTableResource(String tableResource) {
		this.tableResource = tableResource;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public byte getEnabled() {
		return enabled;
	}

	public void setEnabled(byte enabled) {
		this.enabled = enabled;
	}

}
