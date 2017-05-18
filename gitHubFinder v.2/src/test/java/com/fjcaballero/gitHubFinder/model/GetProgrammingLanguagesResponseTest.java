package com.fjcaballero.gitHubFinder.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GetProgrammingLanguagesResponseTest {

	private GetProgrammingLanguagesResponse getProgrammingLanguagesResponse;
	private static final String TEST_USER = "fransk89";
	private static final String TEST_PROGRAMMING_LANGUAGE = "JavaScript";
	
	
	@Before
    public void setUp() throws Exception {
		getProgrammingLanguagesResponse = new GetProgrammingLanguagesResponse();
    }

    @Test
    public void testSetAndGetId() {
        assertNull(getProgrammingLanguagesResponse.getId());
        
        
        String id = java.util.UUID.randomUUID().toString();
        getProgrammingLanguagesResponse.setId(id);
        assertEquals(id, getProgrammingLanguagesResponse.getId());
    }
    
    @Test
    public void testSetAndGetListLanguages() {
        assertNull(getProgrammingLanguagesResponse.getListLanguages());
        
        List<String> listLanguages = new ArrayList();
        listLanguages.add(TEST_PROGRAMMING_LANGUAGE);
        
        getProgrammingLanguagesResponse.setListLanguages(listLanguages);
        assertEquals(TEST_PROGRAMMING_LANGUAGE, (String) listLanguages.get(0));
    }

}
