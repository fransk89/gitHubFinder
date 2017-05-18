package com.fjcaballero.gitHubFinder.model;

import java.io.Serializable;
import java.util.List;

public class GetProgrammingLanguagesResponse implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private List<String> listLanguages = null; 


	public List<String> getListLanguages() {
		return listLanguages;
	}


	public void setListLanguages(List<String> listLanguages) {
		this.listLanguages = listLanguages;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}

}
