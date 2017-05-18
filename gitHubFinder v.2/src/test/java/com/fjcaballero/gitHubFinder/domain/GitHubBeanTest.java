package com.fjcaballero.gitHubFinder.domain;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class GitHubBeanTest {

	private GitHubBean gitHubBean;
	private static final String TEST_PROPERTIES = "TEST_PROPERTIES";
	private static final String RANDOM_VALUE = "RANDOM_VALUE";
	
	
	@Before
    public void setUp() throws Exception {
		gitHubBean = new GitHubBean();
    }

    @Test
    public void testSetAndGetId() {
        assertNull(gitHubBean.getId());
        
        
        String id = java.util.UUID.randomUUID().toString();
        gitHubBean.setId(id);
        assertEquals(id, gitHubBean.getId());
    }
    
    @Test
    public void testSetAndGetStartedAt() {
        assertNull(gitHubBean.getStartedAt());
        
        Date date = new Date();
        gitHubBean.setStartedAt(date);
        assertEquals(date, gitHubBean.getStartedAt());
    }
    
    @Test
    public void testSetAndGetProperties() {
        assertNull(gitHubBean.getProperties());
        
        HashMap<String, String> properties = new HashMap();
        properties.put(TEST_PROPERTIES, RANDOM_VALUE);
        gitHubBean.setProperties(properties);
        assertEquals(properties, gitHubBean.getProperties());
    }

}
