package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.TipoToken;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.model.UserRole;
import com.codigoartesanal.entuliga.model.UserToken;
import com.codigoartesanal.entuliga.repositories.UserRepository;
import com.codigoartesanal.entuliga.repositories.UserRoleRepository;
import com.codigoartesanal.entuliga.repositories.UserTokenRepository;
import com.codigoartesanal.entuliga.services.MailService;
import com.codigoartesanal.entuliga.services.UserService;
import com.codigoartesanal.entuliga.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import static com.codigoartesanal.entuliga.model.TipoToken.*;

/**
 * Created by betuzo on 07/04/15.
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String PROPERTY_NAME_MAIL_TEMPLEATE_SIGNUP = "mail.templeate.signup";

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserTokenService userTokenService;

    @Autowired
    MailService mailService;

    @Resource
    Environment env;

    @Override
    public boolean existsUsername(String username) {
        boolean exists = false;
        User user = new User();
        user = userRepository.findByUsername(username);
        if (user != null)
            exists = true;
        return exists;
    }

    @Override
    public Map<String, Object> findByUsername(String username) {
        return convertUserToMap(userRepository.findByUsername(username));
    }

    @Override
    public Map<String, Object> createUser(Map<String, String> userMap) {
        User user = new User();
        if (userMap.get(PROPERTY_PASSWORD).equals(userMap.get(PROPERTY_PASSWORD_CONFIRM))){
            userMap.put(PROPERTY_ENABLED, String.valueOf(Boolean.FALSE));
            user = userRepository.save(convertMapToUser(userMap));
            TipoToken tipoToken = (get(user.getUsername())==null) ? VALID_EMAIL : CHANGE_PASSWORD;
            userRoleRepository.save(generateRoleDefaultByUser(user));
            UserToken userToken = userTokenService.createUserTokenByUserAndTipo(user, tipoToken);
            sendMailToken(userToken, userMap.get(PROPERTY_CONTEXT));
        }
        return convertUserToMap(user);
    }

    @Override
    public Map<String, Object> changePassword(Map<String, String> userMap) {
        User user = new User();
        Map<String, Object> userTokenMap = userTokenService.userTokenByIdAndTipo(
                userMap.get(UserTokenService.PROPERTY_TOKEN), TipoToken.CHANGE_PASSWORD);
        userMap.put(PROPERTY_USERNAME, (String) userTokenMap.get(PROPERTY_USERNAME));
        if (userMap.get(PROPERTY_PASSWORD).equals(userMap.get(PROPERTY_PASSWORD_CONFIRM))){
            userMap.put(PROPERTY_ENABLED, String.valueOf(Boolean.TRUE));
            user = userRepository.save(convertMapToUser(userMap));
        }
        return convertUserToMap(user);
    }

    private Map<String, Object> convertUserToMap(User user){
        Map<String, Object> sessionDTO = new HashMap<String, Object>();
        sessionDTO.put(PROPERTY_ROLES, getRolesByUser(user));
        sessionDTO.put(PROPERTY_ID, String.valueOf(user.getUsername()));
        sessionDTO.put(PROPERTY_USERNAME, user.getUsername());
        return sessionDTO;
    }

    private User convertMapToUser(Map<String, String> userMap) {
        User user = new User();
        user.setUsername(userMap.get(PROPERTY_USERNAME));
        user.setPassword(userMap.get(PROPERTY_PASSWORD));
        user.setEnabled(Boolean.parseBoolean(userMap.get(PROPERTY_ENABLED)));
        return user;
    }

    private List<String> getRolesByUser(User user){
        user.setUserRole(userRoleRepository.findAllByUser(user));
        Iterator<UserRole> iter = user.getUserRole().iterator();
        List<String> roles = new ArrayList<String>();
        while (iter.hasNext()) {
            UserRole userRole = iter.next();
            roles.add(userRole.getRole());
        }
        return roles;
    }

    private UserRole generateRoleDefaultByUser(User user) {
        UserRole role = new UserRole();
        role.setUser(user);
        role.setRole(PROPERTY_ROLE_DEFAULT);
        return role;
    }

    private User get(String username){
        return userRepository.findById(username).get();
    }

    private void sendMailToken(UserToken userToken, String context) {
        Map<String, String> to = new HashMap<>();
        to.put(userToken.getUser().getUsername(), userToken.getUser().getUsername());

        Map<String, String> props = new HashMap<>();
        props.put("PACTION", userToken.getTipo().getDescription());
        props.put("PLINK", context + "#token/" + userToken.getToken());

        mailService.sendTempleate(env.getRequiredProperty(PROPERTY_NAME_MAIL_TEMPLEATE_SIGNUP), to, props);
    }
}
