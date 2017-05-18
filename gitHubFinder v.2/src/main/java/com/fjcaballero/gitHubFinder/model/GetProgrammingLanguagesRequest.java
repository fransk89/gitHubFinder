package com.fjcaballero.gitHubFinder.model;

import java.io.Serializable;

public class GetProgrammingLanguagesRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	
	private String username;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
