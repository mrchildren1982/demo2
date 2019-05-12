package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

//@Component
//@Aspect
@Order(99)
public class MethodAspect {

	public static final Logger logger = LoggerFactory.getLogger(MethodAspect.class);
//	@Before("execution(* *..*.*(..))")
	public void beforeMethod(JoinPoint joinPoint) {

		logger.debug("メソッド実行前： " + joinPoint.getSignature());

	}

}
