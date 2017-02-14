package com.test.testlogger;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@RestController
@EnableSwagger2
public class TestloggerApplication {

	//private static final Logger logger = LoggerFactory.getLogger(TestloggerApplication.class); 
	
	
	 @Autowired 
	 GlobalLogger logger;
	
	@Autowired Testanotherclass testanotherclass;
	
	public static void main(String[] args) {
		SpringApplication.run(TestloggerApplication.class, args);
	}
	
	
    public void performSomeTask(){
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
        //logger.fatal("This is a fatal message");
    }
    
    @RequestMapping(value="sayHello")
    public String logHello(@RequestBody SampleJson sampleJson){
    	
    	ObjectMapper mapper = new ObjectMapper();
    	
    	try {
    		performSomeTask();
    		testanotherclass.logHello("Sample");
    		logger.info(mapper.writeValueAsString(sampleJson));
    		logger.debug(mapper.writeValueAsString(sampleJson));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "returned";
    	
    }
}
