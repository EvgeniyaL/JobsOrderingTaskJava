package com.eml.sumuptask.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.eml.sumuptask.commands.TaskRequest;
import com.eml.sumuptask.commands.TasksCommand;
import com.eml.sumuptask.contracts.TasksHandler;
import com.eml.sumuptask.responses.TasksResponse;

public class TaskHandlerImpl implements TasksHandler {

	@Override
	public List<TasksResponse> ProcessTasks(TasksCommand command) {

		HashSet<String> taskLookUp = new HashSet<>();
		HashSet<String> addedTasks = new HashSet<>();
		List<TaskRequest> tasks = command.getTasks();
		List<TasksResponse> result = new ArrayList<>();

		for (TaskRequest item : tasks) {

			taskLookUp.add(item.getName());
		}

		while (tasks.size() > 0) {

			for (int i = 0; i < tasks.size();) {

				TaskRequest item = tasks.get(i);
				if (checkRequired(taskLookUp, addedTasks, item)) {

					addedTasks.add(item.getName());

					TasksResponse response = createTaskResponse(item);

					result.add(response);

					tasks.remove(i);

				} else {
					i++;
				}
			}
		}

		return result;
	}

	@Override
	public String ProcessTasksToBashScript(TasksCommand command) {

		List<TasksResponse> tasks = this.ProcessTasks(command);
		StringBuilder builder = new StringBuilder();
		builder.append("#!/usr/bin/env bash\n\n");
		for (TasksResponse task : tasks) {
			builder.append(task.getCommand() + "\n");
		}
		return builder.toString();
	}

	private boolean checkRequired(HashSet<String> taskLookUp, HashSet<String> addedTasks, TaskRequest item) {
		for (String requiredTaskName : item.getRequires()) {

			if (!taskLookUp.contains(requiredTaskName)) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
						"Required task " + requiredTaskName + " does not exist.");
			}
			if (!addedTasks.contains(requiredTaskName)) {
				return false;
			}
		}
		return true;
	}

	private TasksResponse createTaskResponse(TaskRequest item) {
		TasksResponse response = new TasksResponse();
		response.setName(item.getName());
		response.setCommand(item.getCommand());
		return response;
	}
}
