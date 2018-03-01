package com.codigoartesanal.entuliga.infrastructure;

import com.codigoartesanal.entuliga.config.security.JwtUser;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.UserRepository;
import com.codigoartesanal.entuliga.services.GeneralService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * Created by betuzo on 11/05/15.
 */
@Aspect
@Component
public class UserSessionAop {

    private static final Logger logger = LoggerFactory.getLogger(LogAop.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    HttpServletResponse response;

    @Pointcut("execution(* com.codigoartesanal.entuliga.controller.*Controller.*(..))")
    public void controllerLayer() {
    }

    @Pointcut("execution(* com.codigoartesanal.entuliga.controller.*Controller.delete*(..))")
    public void controllerLayerDeleteMethod() {
    }

    @Around("controllerLayer()")
    public Object aroundControllerMethod(ProceedingJoinPoint pjp) throws Throwable{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
        logger.info("Argumento " + jwtUser);

        for(Object arg :pjp.getArgs()){
            if(arg instanceof User && jwtUser != null) {
                Optional<User> opUser = userRepository.findById(jwtUser.getUsername());
                if (opUser.isPresent()) {
                    User user = opUser.get();
                    ((User) arg).setUsername(user.getUsername());
                    ((User) arg).setEnabled(user.isEnabled());
                    ((User) arg).setUserRole(user.getUserRole());
                }
            }
        }

        Object retVal = pjp.proceed();
        return retVal;
    }

    @AfterReturning(
            pointcut = "controllerLayerDeleteMethod())",
            returning= "result")
    public void deleteAfterReturning(JoinPoint joinPoint, Object result) {
        Map<String, Object> resultDelete = (Map<String, Object>) result;
        if (!(boolean)resultDelete.get(GeneralService.PROPERTY_RESULT)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
