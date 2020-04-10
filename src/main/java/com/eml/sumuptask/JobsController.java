package com.eml.sumuptask;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.eml.sumuptask.commands.TasksCommand;
import com.eml.sumuptask.contracts.TasksHandler;
import com.eml.sumuptask.responses.TasksResponse;

@RestController
public class JobsController {

	private final TasksHandler taskHandler;

	public JobsController(TasksHandler handler) {
		taskHandler = handler;
	}

	@GetMapping("/")
	public String index() {
		return "Greetings to Task Manager!";
	}

	@PostMapping("/tasks")
	public List<TasksResponse> OrderTasks(@RequestBody TasksCommand command) {

		List<TasksResponse> result = taskHandler.ProcessTasks(command);
		return result;
	}

	@PostMapping("/tasks/bash")
	public String GetBashTasks(@RequestBody TasksCommand command) {

		String result = taskHandler.ProcessTasksToBashScript(command);
		return result;
	}
}
