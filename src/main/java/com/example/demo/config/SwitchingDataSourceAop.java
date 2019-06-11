package com.example.demo.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(99)
public class SwitchingDataSourceAop {

	@Around("@annotation(swds)")
	 public Object switchingForMethod(ProceedingJoinPoint pjp, SwitchingDataSource swds) throws Throwable {
        try {
            MultipleDataSourceContextHolder.setDataSourceType(swds.value());
            return pjp.proceed();
        } finally {
        	MultipleDataSourceContextHolder.clearTenantType();
        }
    }


}
