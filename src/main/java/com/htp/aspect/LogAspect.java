package com.htp.aspect;

import com.htp.JdbcTemplateDemo;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Component
@Aspect
public class LogAspect {

    private static final Logger log = Logger.getLogger(LogAspect.class);

    private StopWatch watch = new StopWatch();

    @Pointcut("execution(* com.htp.dao..*(..))")
    public void aroundRepositoryPointcut() {
    }

    @Around("aroundRepositoryPointcut()")
    public Object logAroundMethods(ProceedingJoinPoint joinPoint) throws Throwable {

        String mapKey = "  " + joinPoint.getSignature().getDeclaringTypeName() + "  " + joinPoint.getSignature().getName();
        Integer prevValue = JdbcTemplateDemo.methodsCount.get(mapKey);
        JdbcTemplateDemo.methodsCount.put(mapKey, prevValue == null ? 1 : prevValue + 1);

        watch.start();
        Object proceed = joinPoint.proceed();
        watch.stop();
        log.info(joinPoint.getSignature().getName() + " Time elapsed: " + watch.getLastTaskTimeNanos());

        return proceed;
    }

}
