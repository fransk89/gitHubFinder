package com.fjcaballero.gitHubFinder.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GetProgrammingLanguagesRequestTest {

	private GetProgrammingLanguagesRequest getProgrammingLanguagesRequest;
	private static final String TEST_USER = "fransk89";
	
	
	@Before
    public void setUp() throws Exception {
		getProgrammingLanguagesRequest = new GetProgrammingLanguagesRequest();
    }

    @Test
    public void testSetAndGetId() {
        assertNull(getProgrammingLanguagesRequest.getId());
        
        
        String id = java.util.UUID.randomUUID().toString();
        getProgrammingLanguagesRequest.setId(id);
        assertEquals(id, getProgrammingLanguagesRequest.getId());
    }
    
    @Test
    public void testSetAndGetUsername() {
        assertNull(getProgrammingLanguagesRequest.getUsername());
        
        getProgrammingLanguagesRequest.setUsername(TEST_USER);
        assertEquals(TEST_USER, getProgrammingLanguagesRequest.getUsername());
    }

}
