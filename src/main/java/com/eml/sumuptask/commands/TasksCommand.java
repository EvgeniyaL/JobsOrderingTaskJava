package com.eml.sumuptask.commands;

import java.util.LinkedList;
import java.util.List;

public class TasksCommand {

	private List<TaskRequest> tasks;

	public List<TaskRequest> getTasks() {
		return new LinkedList<>(tasks);
	}

	public void setTasks(List<TaskRequest> tasks) {
		this.tasks = tasks;
	}
}
