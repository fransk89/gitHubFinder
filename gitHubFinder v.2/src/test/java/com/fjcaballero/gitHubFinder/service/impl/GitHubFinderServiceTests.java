package com.fjcaballero.gitHubFinder.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.Before;
import org.junit.Test;

import com.fjcaballero.gitHubFinder.domain.GitHubBean;

public class GitHubFinderServiceTests {
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    
    private static final String USER_NO_REPOSITORIES = "norepositoryuser";
    private static final String USER_REPOSITORY_NO_LANGUAGES = "usernolanguages";
    private static final String USER_NO_EXIST = "usernoexistusernoexist";
    private static final String TEST_USER = "fransk89";
    private static final String TEST_PROGRAMMING_LANGUAGE = "JavaScript";
    
    private RepositoryService repositoryService;
    private GitHubFinderService gitHubFinderService;
    

    @Before
    public void setUp() throws Exception {
    	repositoryService = new RepositoryService();
    	gitHubFinderService = new GitHubFinderService(repositoryService);
    }
	
	@Test
    public void testGetGitHubProgrammingLanguages() throws  IOException, IllegalArgumentException, Exception {		
		
		List<String> listLanguages = null;
		GitHubBean gitHubBean = null;
		String username = null;
		
		try {
			gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean);
		} catch (IllegalArgumentException e) {
			logger.warn("Expected");
		} catch (Exception e) {
			logger.warn("No expected");
			throw e;
		}
		
		try {
			username = "";
			gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean);
		} catch (IllegalArgumentException e) {
			logger.warn("Expected");
		} catch (Exception e) {
			logger.warn("No Expected");
			throw e;
		}
		
		try {
			username = null;
			gitHubBean = new GitHubBean();
			gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean);
		} catch (IllegalArgumentException e) {
			logger.warn("Expected");
		} catch (Exception e) {
			logger.warn("No expected");
			throw e;
		}
		
		try {
			gitHubBean.setId("");
			gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean);
		} catch (IllegalArgumentException e) {
			logger.warn("Expected");
		} catch (Exception e) {
			logger.warn("No expected");
			throw e;
		}
		
		
		gitHubBean.setId(java.util.UUID.randomUUID().toString());		
		username = USER_NO_EXIST;
		assertNull(gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean));
		
		username = USER_NO_REPOSITORIES;
		assertNull(gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean));
		
		username = USER_REPOSITORY_NO_LANGUAGES;
		listLanguages = gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean);
		assertNull(listLanguages.get(0));	
		
		username = TEST_USER;
		listLanguages = gitHubFinderService.getGitHubProgrammingLanguages(username, gitHubBean);
		assertEquals(TEST_PROGRAMMING_LANGUAGE, (String)listLanguages.get(0));
	}
}
