package com.nimblemodule.spring.service;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;




// This class can play the role of logging and exception handling and other cross cutting concern need of system.
// As well as enhancement can be made for monitoring the performance of call made to service .As well the caching solution can also be provided from here
public class CrossCuttingInterceptor implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice, MethodInterceptor {

//	@Autowired
//	private ErrorService errorservice;

	long startTime = 0;

	long finishTime = 0;
	 private Logger logger = LoggerFactory.getLogger(CrossCuttingInterceptor.class);

	public void before(Method method, Object[] args, Object target) throws Throwable {
		startTime = System.currentTimeMillis();
//		logger.debug("Executing method " + method.getName() + " on object " + target.getClass().getName());
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		finishTime = System.currentTimeMillis();
		double totalDuration = finishTime - startTime;
//		logger.debug("Finished executing method " + method.getName() + " on object " + target.getClass().getName() + " in " + totalDuration / 1000 + " seconds.");

	}

	public void afterThrowing(Method method, Object[] args, Object target, Exception exception) throws Exception {
		exception.printStackTrace();
		String errormessage = "";
		String messageid = "";// for i18N future enhancement
		String methodname = "";
		String serviceName = "";
		String applicaitonuserid;
		String useridentity = "";

		if (exception instanceof Exception) {

//			errormessage = exception.getMessage();
//			methodname = method.getName();
//			serviceName = target.getClass().getName();
//			ErrorManager errormanager = new ErrorManager();
//			errormanager.setErrormessage(errormessage);
//			errormanager.setMethodname(methodname);
//			errormanager.setServicename(serviceName);
//			errormanager.setCompleteerrorstack(getIEerrorSubSet(exception));
////			  logger.error("Error Thrown From"+serviceName+ methodname + "with"+ args +"as parameter", exception);
//			CUser currentUser = errorservice.getCurrentUser();
//			if (currentUser != null) {
//				CUser user = errorservice.getCurrentUser();
//				useridentity = user.getIdentity();
//				applicaitonuserid = user.getId();
//
//				errormanager.setApplicaitonuserid(applicaitonuserid);
//				errormanager.setIdentity(useridentity);
		}

//			errorservice.RegisterError(errormanager);
//
//		}
//
//		if (exception instanceof RuntimeException) {
//
//			errormessage = exception.getMessage();
//			methodname = method.getName();
//			serviceName = target.getClass().getName();
//			ErrorManager errormanager = new ErrorManager();
//			errormanager.setErrormessage(errormessage);
//			errormanager.setMethodname(methodname);
//			errormanager.setServicename(serviceName);
//			errormanager.setCompleteerrorstack(getIEerrorSubSet(exception));
////			 logger.error("Error Thrown From"+serviceName+ methodname + "with"+ args +"as parameter", exception);
//			CUser currentUser = errorservice.getCurrentUser();
//			if (currentUser != null) {
//				CUser user = errorservice.getCurrentUser();
//				useridentity = user.getIdentity();
//				applicaitonuserid = user.getId();
//
//				errormanager.setApplicaitonuserid(applicaitonuserid);
//				errormanager.setIdentity(useridentity);
//			}
//
//			errorservice.RegisterError(errormanager);
//		}
//
//		else if (exception instanceof ChameleonException) {
//
//			errormessage = exception.getMessage();
//			methodname = method.getName();
//			serviceName = target.getClass().getName();
//			ErrorManager errormanager = new ErrorManager();
//
//			errormanager.setErrormessage(errormessage);
//			errormanager.setMethodname(methodname);
//			errormanager.setServicename(serviceName);
//			errormanager.setCompleteerrorstack(getIEerrorSubSet(exception));
////			 logger.error("Error Thrown From"+serviceName+ methodname + "with"+ args +"as parameter", exception);
//			CUser currentUser = errorservice.getCurrentUser();
//			if (currentUser != null) {
//				CUser user = errorservice.getCurrentUser();
//				useridentity = user.getIdentity();
//				applicaitonuserid = user.getId();
//
//				errormanager.setApplicaitonuserid(applicaitonuserid);
//				errormanager.setIdentity(useridentity);
//			}
//
//			errorservice.RegisterError(errormanager);
//		}

	}

	public static String getIEerrorSubSet(Exception exception) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		Throwable th = exception;
		int count = 0;
		while (th.getCause() != null) {
			th = th.getCause();
			count++;
			if (count == 5) {
				break;
			}
		}
		th.printStackTrace(pw);
		String error = sw.toString();
		//System.out.println(error);
		String[] lines = error.split(System.getProperty("line.separator"));
		StringBuilder mainstack = new StringBuilder();
		int levelofexceptiontree=15;
		for(int i=0;i<=levelofexceptiontree;i++){
			mainstack.append(lines[i]);
			
		
			
		}
		
		return mainstack.toString();
	}

	public Object invoke(MethodInvocation arg0) throws Throwable {
		return arg0.proceed();
	}

}
