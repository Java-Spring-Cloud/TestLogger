package com.test.testlogger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Testanotherclass {

	//private static final Logger logger = LoggerFactory.getLogger(TestloggerApplication.class); 
	
	
	 @Autowired 
	 GlobalLogger logger;
	
	
    public void performSomeTask(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        //logger.fatal("This is a fatal message");
    }
    
    public String logHello(String name){
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
    		performSomeTask();
    		logger.info(mapper.writeValueAsString(name));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "returned";
    	
    }
}
