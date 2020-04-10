package com.eml.sumuptask.contracts;

import java.util.List;
import com.eml.sumuptask.commands.TasksCommand;
import com.eml.sumuptask.responses.TasksResponse;

public interface TasksHandler {

	public List<TasksResponse> ProcessTasks(TasksCommand command);
	
	public String ProcessTasksToBashScript(TasksCommand command);
}
