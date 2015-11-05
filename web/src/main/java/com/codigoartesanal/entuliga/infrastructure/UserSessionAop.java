package com.codigoartesanal.entuliga.infrastructure;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.UserRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by betuzo on 11/05/15.
 */
@Aspect
@Component
public class UserSessionAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    UserRepository userRepository;

    @Pointcut("execution(* com.codigoartesanal.entuliga.controller.*Controller.*(..))")
    public void controllerLayer() {
    }

    @Around("controllerLayer()")
    public Object aroundControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) authentication.getPrincipal();
        logger.info("Argumento " + username);

        for(Object arg :pjp.getArgs()){
            if(arg instanceof User && username != null && !username.isEmpty()) {
                User user = userRepository.findOne(username);
                if (user != null) {
                    ((User) arg).setUsername(user.getUsername());
                    ((User) arg).setEnabled(user.isEnabled());
                    ((User) arg).setUserRole(user.getUserRole());
                }
            }
        }

        Object retVal = pjp.proceed();
        return retVal;
    }
}
