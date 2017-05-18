package com.fjcaballero.gitHubFinder.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.junit.Before;
import org.junit.Test;

import com.fjcaballero.gitHubFinder.domain.GitHubBean;

public class MemorySessionServiceTest {
	
	private static final int HOURS = 4;
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
    private static Map<String, GitHubBean> sessions;
    MemorySessionService memorySessionService;
    

    @Before
    public void setUp() throws Exception {
    	memorySessionService = new MemorySessionService (HOURS);
    }
    
    @Test
    public void testSetAndGetHours() {
        assertEquals(HOURS, memorySessionService.getHours());
    }
	
	@Test
    public void testStoreSession() throws  IOException, IllegalArgumentException, Exception {		
		
		GitHubBean gitHub = null;		
		assertEquals(false, memorySessionService.storeSession(gitHub));
		
		gitHub = new GitHubBean();
		assertEquals(false, memorySessionService.storeSession(gitHub));
		
		gitHub.setId(java.util.UUID.randomUUID().toString());
		assertEquals(true, memorySessionService.storeSession(gitHub));
		
	}
	
    @Test
    public void testRetrieveSession() {
    	
    	GitHubBean gitHubToCompare = null;
    	GitHubBean gitHub = new GitHubBean();
    	String id = java.util.UUID.randomUUID().toString();
    	gitHub.setId(id);
    	gitHub.setStartedAt(new Date());
    	
    	memorySessionService.storeSession(gitHub);
    	gitHubToCompare = memorySessionService.retrieveSession(id);
    	assertEquals(gitHubToCompare, memorySessionService.retrieveSession(id));
    	
    	try {
    		assertEquals(null, memorySessionService.retrieveSession(null));
		}  catch (Exception e) {
			logger.warn("Expected");
		}
    	assertEquals(null, memorySessionService.retrieveSession(""));
    }
    
    @Test
    public void testDeleteSession() {
    	
    	GitHubBean gitHub = new GitHubBean();
    	String id = java.util.UUID.randomUUID().toString();
    	gitHub.setId(id);
    	gitHub.setStartedAt(new Date());
    	
    	memorySessionService.storeSession(gitHub);
    	
    	assertEquals(true, memorySessionService.deleteSession(id));
    }
    
    @Test
    public void testResetSession() {
    	
    	GitHubBean gitHub = new GitHubBean();
    	Map<String, GitHubBean> sessionsAux = new ConcurrentHashMap<String, GitHubBean>();
    	String id = java.util.UUID.randomUUID().toString();
    	gitHub.setId(id);
    	gitHub.setStartedAt(new Date());
    	
    	memorySessionService.storeSession(gitHub);
    	memorySessionService.resetSessions();
    	
    	assertEquals(sessionsAux, memorySessionService.retrieveSessions());
    }
}
