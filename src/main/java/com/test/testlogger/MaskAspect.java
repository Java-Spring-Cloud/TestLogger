/**
 * 
 */
package com.test.testlogger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Anjan
 *
 */

@Aspect
@Component
public class MaskAspect {

	private static final String ELEMENT_FIELD_VALUE = "\":";

	private static final Logger LOG = LoggerFactory.getLogger(MaskAspect.class);

	private static final String[] secureFields = {
            "ChargeAccountNumber",
            "ChargeCVN",
            "accountNumber",
            "cardNumber",
            "DebitAccountNumber",
            "DebitCVN",
            "CheckAcountNumber",
            "Password",
            "cvv"
  };

	private Object[] ExecutePointCut(Object[] args){
		
		if (args.length > 0) {
			//LOG.info("Arguments passed: ");
			for (int i = 0; i < args.length; i++) {
				// LOG.info("Arg"+(i+1)+":"+args[i]);
				if (args[i] != null)
					args[i] = maskfields(args[i].toString());
			}
		}
		return args;
	}
	
	@Around("execution(* com.test.testlogger.GlobalLogger.*(..))")
	public void info(ProceedingJoinPoint jp) throws Throwable {

		org.aspectj.lang.Signature sign = jp.getSignature();
		String concreteClass = sign.getDeclaringType().getName();
		LOG.info("Executing info PointCut "+concreteClass);
		Object[] args = ExecutePointCut(jp.getArgs());
		jp.proceed(args);
	}

	/*@Around("execution(* org.slf4j.Logger.*(..))")
	public void infosl4j(ProceedingJoinPoint jp) throws Throwable {
		LOG.info("Executing infoSl4j PointCut ");

		Object[] args = ExecutePointCut(jp.getArgs());
		jp.proceed(args);
	}
	
	@Around("execution(* com.tmobile.eus.digitalservices.testlogger.GlobalLogger.debug(..))")
	public void debug(ProceedingJoinPoint jp) throws Throwable {
		LOG.info("Executing Debug PointCut ");
		Object[] args = ExecutePointCut(jp.getArgs());
		jp.proceed(args);
	}
	
	@Around("execution(* com.tmobile.eus.digitalservices.testlogger.GlobalLogger.error(..))")
	public void error(ProceedingJoinPoint jp) throws Throwable {
		LOG.info("Executing Debug PointCut ");
		Object[] args = ExecutePointCut(jp.getArgs());
		jp.proceed(args);
	}
	
	@Around("execution(* com.tmobile.eus.digitalservices.testlogger.GlobalLogger.warn(..))")
	public void warn(ProceedingJoinPoint jp) throws Throwable {
		LOG.info("Executing Debug PointCut ");
		Object[] args = ExecutePointCut(jp.getArgs());
		jp.proceed(args);
	}	*/

	private static String maskfields(String stringVesta) {
		StringBuffer sb = new StringBuffer(stringVesta);
		char delim;
		for (int i = secureFields.length - 1; i >= 0; i--) {
			String currentElement = secureFields[i].toString() + ELEMENT_FIELD_VALUE;
			int index = stringVesta.indexOf(currentElement);
			if (index > 0) {
				index += secureFields[i].length() + 3;
				delim = sb.charAt(index - 1);
				while (sb.charAt(index) != delim) {
					sb.setCharAt(index, '*');
					++index;
				}
			}
		}

		return sb.toString();

	}
	
}
