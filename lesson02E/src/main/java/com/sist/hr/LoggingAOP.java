package com.sist.hr;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

public class LoggingAOP {
	private Logger log = Logger.getLogger(LoggingAOP.class);
	
	//JoinPoint: 메소드의 param,메소드 이름.
	public void logging(JoinPoint joinPoint) {
		Signature method  = joinPoint.getSignature();
		String methodNm = method.getName();
		
		log.debug(">>>>>---------------");
		log.debug(methodNm+"is calling.");
		log.debug(">>>>>---------------");
	}
}
