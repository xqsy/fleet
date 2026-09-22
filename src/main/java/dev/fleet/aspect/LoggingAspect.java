package dev.fleet.aspect;

import dev.fleet.exception.DriverNotFoundException;
import dev.fleet.exception.TransportRequestNotFoundException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("execution(* dev.fleet.service.TripService.assignDriver(..))")
    public void assignDriverPointcut() {}

    @Around("assignDriverPointcut()")
    public Object logAssignDriver(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();

        long start = System.currentTimeMillis();

        try {
            Object result = joinPoint.proceed();

            long executionTime = System.currentTimeMillis() - start;

            log.info(
                    "Method: {}, parameters: {}, return value: {}, execution time: {} ms",
                    methodName,
                    Arrays.toString(args),
                    result,
                    executionTime
            );

            return result;

        } catch (DriverNotFoundException | TransportRequestNotFoundException e) {

            log.error("Method: {}, parameters: {}, exception: {}", methodName, Arrays.toString(args), e.getMessage());

            throw e;
        }
    }
}
