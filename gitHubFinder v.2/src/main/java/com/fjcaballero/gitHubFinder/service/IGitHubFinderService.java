package com.fjcaballero.gitHubFinder.service;

import java.util.List;

import com.fjcaballero.gitHubFinder.domain.GitHubBean;

public interface IGitHubFinderService {
	
	/**
	 * Get the user´s favourite progamming languages in GitHub.
	 * 
	 * @param String userName
	 * @param GitHubBean gitHub
	 * 
	 * @return List<String>
	 *  
	 * @throws Exception 
	 * @throws IllegalArgumentException 
	 */
	public List<String> getGitHubProgrammingLanguages(String userName, GitHubBean gitHub) throws IllegalArgumentException, Exception;
	
}
