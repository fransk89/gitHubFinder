package com.fjcaballero.gitHubFinder.service;

import com.fjcaballero.gitHubFinder.domain.GitHubBean;



/**
 * This repository will retrieve and store information about sessions in the
 * system.
 */
public interface ISessionRepository {
	/**
	 * This method stores the session information in a persistence media.
	 * 
	 * @param token
	 * @param properties
	 */
	public boolean storeSession(GitHubBean gitHubBean);

	/**
	 * This method deletes the session information.
	 * 
	 * @param id
	 * @param properties
	 */
	public boolean deleteSession(String id);

	/**
	 * This method retrieves the session information from the persistence media.
	 * 
	 * @param token
	 * @return
	 */
	public GitHubBean retrieveSession(String id);

	/**
	 * Reset all active sessions.
	 */
	public void resetSessions();

}
