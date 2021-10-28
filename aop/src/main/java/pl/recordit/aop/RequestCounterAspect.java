package pl.recordit.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Aspect
@Slf4j
public class RequestCounterAspect {
    private AtomicInteger counter = new AtomicInteger();

    @Pointcut("execution(public * pl.recordit.aop.HomeController.*(Long))")
    public void homeFindById(){}

    @Pointcut("execution(public String pl.recordit.aop.HomeController.home())")
    public void home(){}

    @Around("home() || homeFindById()")
    public Object count(ProceedingJoinPoint point) throws Throwable {
        final Object proceed = point.proceed();
        counter.incrementAndGet();
        log.info("Both Request count: " + counter.get());
        return proceed;
    }

    @AfterReturning("home()")
    public Object countHome(){
        counter.incrementAndGet();
        log.info("Home Request count: " + counter.get());
        return null;
    }
}
