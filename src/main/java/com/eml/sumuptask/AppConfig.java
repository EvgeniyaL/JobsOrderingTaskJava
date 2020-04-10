package com.eml.sumuptask;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.eml.sumuptask.contracts.TasksHandler;
import com.eml.sumuptask.handlers.TaskHandlerImpl;

@Configuration
public class AppConfig {
	@Bean
	public TasksHandler tasksHandler() {
		return new TaskHandlerImpl();
	}
}
