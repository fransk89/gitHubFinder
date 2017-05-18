package com.fjcaballero.gitHubFinder.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fjcaballero.gitHubFinder.domain.GitHubBean;
import com.fjcaballero.gitHubFinder.model.GetProgrammingLanguagesRequest;
import com.fjcaballero.gitHubFinder.model.GetProgrammingLanguagesResponse;
import com.fjcaballero.gitHubFinder.service.IGitHubFinderService;
import com.fjcaballero.gitHubFinder.service.ISessionRepository;
import com.fjcaballero.gitHubFinder.util.Util;

@Controller
public class GitHubFinderController {
	
	private static final String PROGRAMMING_LANGUAGES = "programmingLanguages";
	
	/** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());
   
    @Autowired
    private IGitHubFinderService gitHubFinderService;
    
    @Autowired
	private ISessionRepository sessionRepository;
		

    @RequestMapping(value="/gitHubFinder")
    public ModelAndView loadGitHubFinder()
            throws ServletException, IOException {

    	logger.info("GitHubFinderController.loadGitHubFinder - Start");
    	
    	// Create an instance of SimpleDateFormat used for formatting 
    	// the string representation of date (month/day/year)
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    	
    	String now = df.format(new Date());
        logger.debug("Returning gitHubFinder view with date " + now);

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put("now", now);
        
        logger.info("GitHubFinderController.loadGitHubFinder - Finish");

        return new ModelAndView("gitHubFinder", "model", myModel);
    }
    
    
    @RequestMapping(value="/startGitHubFinder")
    @ResponseBody
    public GetProgrammingLanguagesResponse startGithubFinder() 
    		throws ServletException, IOException {

    	logger.info("GitHubFinderController.startGithubFinder - Start");
    	
    	GetProgrammingLanguagesResponse res = new GetProgrammingLanguagesResponse();
    	String gitHubId = java.util.UUID.randomUUID().toString();
    	GitHubBean gitHub = null;
    	HashMap <String, String> initialGitHubString = null;
    	
    	initialGitHubString = new HashMap<String, String>();
    	initialGitHubString.put(PROGRAMMING_LANGUAGES, "");
    	
    	gitHub = new GitHubBean();
    	gitHub.setId(gitHubId);
    	gitHub.setStartedAt(new Date());
    	gitHub.setProperties(initialGitHubString);
    	    	
    	sessionRepository.storeSession(gitHub);
    	
    	res.setId(gitHubId);
    	
    	logger.info("GitHubFinderController.startGithubFinder - Finish");
    	
    	return res;
    }
    
        
     @ResponseBody
     @RequestMapping(value="/getProgrammingLanguages")
     public GetProgrammingLanguagesResponse getProgrammingLanguages(@RequestBody GetProgrammingLanguagesRequest request)
             throws IllegalArgumentException, Exception {
     	
    	logger.info("GitHubFinderController.getProgrammingLanguages - Start"); 
    	
    	GetProgrammingLanguagesResponse res = new GetProgrammingLanguagesResponse();
     	List<String> listLanguages = null;
     	GitHubBean gitHub = sessionRepository.retrieveSession(request.getId());
     	 		
 		if (gitHub != null) {
 			
 			listLanguages = gitHubFinderService.getGitHubProgrammingLanguages(request.getUsername(), gitHub);
 			
 			logger.info(String.format(
					"GitHubFinderController.getProgrammingLanguages - username %s, listlanguages %s.", request.getUsername(), listLanguages)
 			);
 			
 			
 			res.setId(gitHub.getId());
 			res.setListLanguages(listLanguages);
 		}

 		logger.info("GitHubFinderController.getProgrammingLanguages - Finish"); 
        
 		return res;
     }
	 
	 @ResponseBody
	 @RequestMapping(value="/deleteSession")
	 public boolean deleteSession(@RequestBody GetProgrammingLanguagesRequest request)
            throws ServletException, IOException {
    	
		logger.info("GitHubFinderController.deleteSession - Start"); 
		 
    	boolean res = false;
    	
    	if (request != null
    			&& !Util.isEmpty(request.getId())) {
    		
    		GitHubBean gitHub = sessionRepository.retrieveSession(request.getId());
        	
        	logger.info("GitHubFinderController.deleteSession - id: " + request.getId());
    		
    		if (gitHub != null){
    			sessionRepository.deleteSession(request.getId());
    			res = true;
    		}
    	}
    	
		logger.info("GitHubFinderController.deleteSession - Finish"); 

        return res;
	 }
	
	
}
