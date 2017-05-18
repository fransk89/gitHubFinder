package com.fjcaballero.gitHubFinder.domain;

import java.util.Date;
import java.util.HashMap;


public class GitHubBean{
	
	private String id;
		
	private Date startedAt;
	
	private HashMap<String, String> properties;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public Date getStartedAt() {
		return startedAt;
	}

	public void setStartedAt(Date startedAt) {
		this.startedAt = startedAt;
	}

	public HashMap<String, String> getProperties() {
		return properties;
	}

	public void setProperties(HashMap<String, String> properties) {
		this.properties = properties;
	}
	
	@Override
	public String toString() {
		return "GitHubBean [id=" + id + ", startedAt="
				+ startedAt + ", properties=" + properties + ", toString()="
				+ super.toString() + "]";
	}
	
}
