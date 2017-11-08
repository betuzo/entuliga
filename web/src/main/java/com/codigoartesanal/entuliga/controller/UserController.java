package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.UserService;
import com.codigoartesanal.entuliga.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static com.codigoartesanal.entuliga.services.UserService.PROPERTY_USERNAME;

/**
 * Created by betuzo on 10/02/16.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> createUser(@RequestBody Map<String, String> user, HttpServletRequest request) {
        boolean existsuser = false;
        Map<String, Object> userMap = new HashMap<String, Object>();
        user.put(UserService.PROPERTY_CONTEXT, request.getContextPath());
        existsuser = userService.existsUsername(user.get(PROPERTY_USERNAME));

        if (!existsuser){
            userMap = userService.createUser(user);
        } else {
            userMap.put("message", "La direccion de correo ya esta registrada");
        }
        return userMap;
    }

    @ResponseBody
    @RequestMapping(
            value = { "{username}" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateUser(@RequestBody Map<String, String> user, HttpServletRequest request) {
        if (user.containsKey(UserTokenService.PROPERTY_TOKEN)) {
            return userService.changePassword(user);
        }
        return userService.createUser(user);
    }

    @ResponseBody
    @RequestMapping(
            value = "/{username:.+}",
            method = {RequestMethod.GET},
            headers="Accept=*/*",
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> getUser(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }
}
