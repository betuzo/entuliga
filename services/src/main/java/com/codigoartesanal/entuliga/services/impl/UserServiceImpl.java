package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.model.UserRole;
import com.codigoartesanal.entuliga.repositories.UserRepository;
import com.codigoartesanal.entuliga.repositories.UserRoleRepository;
import com.codigoartesanal.entuliga.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by betuzo on 07/04/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public Map<String, Object> findByUsername(String username) {
        return convertUserToDTO(userRepository.findByUsername(username));
    }

    private Map<String, Object> convertUserToDTO(User user){
        Map<String, Object> sessionDTO = new HashMap<String, Object>();

        sessionDTO.put(PROPERTY_ROLES, getRolesByUser(user));
        sessionDTO.put(PROPERTY_ID, String.valueOf(user.getUsername()));
        sessionDTO.put(PROPERTY_USERNAME, user.getUsername());
        return sessionDTO;
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
}
