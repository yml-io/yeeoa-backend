package com.yeeoa.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class Unit implements Serializable{

	public Unit() {
	}

	private static final long serialVersionUID = -339516038496531943L;
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	private int programID;
	private String unitID;
	private String label;
	private String name;
	private String estimatedHours;
	private String actualHours;
	private String description;
	private String targetedProblems;
	private String createdProducts;
	private String sharedProblem;
	private String objectives;
	private String overviews;
	private String status;
	private Program program;
	private int id;
	private Timestamp createTime;
	private byte enabled;


	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}



	public int getProgramID() {
		return programID;
	}

	public void setProgramID(int programID) {
		this.programID = programID;
	}

	public String getUnitID() {
		return unitID;
	}

	public void setUnitID(String unitID) {
		this.unitID = unitID;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEstimatedHours() {
		return estimatedHours;
	}

	public void setEstimatedHours(String estimatedHours) {
		this.estimatedHours = estimatedHours;
	}

	public String getActualHours() {
		return actualHours;
	}

	public void setActualHours(String actualHours) {
		this.actualHours = actualHours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTargetedProblems() {
		return targetedProblems;
	}

	public void setTargetedProblems(String targetedProblems) {
		this.targetedProblems = targetedProblems;
	}

	public String getCreatedProducts() {
		return createdProducts;
	}

	public void setCreatedProducts(String createdProducts) {
		this.createdProducts = createdProducts;
	}

	public String getOverviews() {
		return overviews;
	}

	public void setOverviews(String overviews) {
		this.overviews = overviews;
	}


	public String getSharedProblem() {
		return sharedProblem;
	}

	public void setSharedProblem(String sharedProblem) {
		this.sharedProblem = sharedProblem;
	}

	public String getObjectives() {
		return objectives;
	}

	public void setObjectives(String objectives) {
		this.objectives = objectives;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
