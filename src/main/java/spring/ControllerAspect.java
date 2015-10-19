package spring;


import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by pengzhanlee on 10/14/15.
 */

@Aspect
@Component
public class ControllerAspect {

    private Logger logger = Logger.getLogger(ControllerAspect.class);

    @Pointcut("execution(* spring..*.*(..))")
    private void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        Boolean isRequestMethod = method.isAnnotationPresent(org.springframework.web.bind.annotation.RequestMapping.class);
//        if(isRequestMethod){
        System.out.println("controller aspect, method =================== " + method.getName());
        try {
            return proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logger.error(e.getMessage());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
//        } else {
//            logger.error("controller aspect, methodxxxxxxxxxx =================== " + method.getName());
//        }

        return null;
    }

}
