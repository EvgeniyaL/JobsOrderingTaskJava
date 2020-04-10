package com.eml.sumuptask.commands;

import java.util.List;
import java.util.Collections;

public class TaskRequest {

	private String name;
	private String command;
	private List<String> requires = Collections.emptyList();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCommand() {
		return this.command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public List<String> getRequires() {
		return requires;
	}

	public void setRequires(List<String> requires) {
		this.requires = requires;
	}
}
