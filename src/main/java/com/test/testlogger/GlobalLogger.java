/**
 * 
 */
package com.test.testlogger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Anjan
 * @param <T>
 *
 */
@Component
public class GlobalLogger {
	private static final Logger logger = LoggerFactory.getLogger(GlobalLogger.class); 
	
/*	String className = null;

	
	*//**
	 * @param name
	 *//*
	public GlobalLogger(String className) {
		// TODO Auto-generated constructor stub
		this.className = className;
	}*/

	/*public GlobalLogger() {
		// TODO Auto-generated constructor stub
		this.className = this.getClass().getName();;
	}*/	

	public void info(String name){
		logger.info(""+" : "+name);
	}
	
	public void debug(String name){
		logger.info(name);
	}
	
	public void error(String name){
		logger.info(name);
	}
	
	public void warn(String name){
		logger.info(name);
	}	

}
