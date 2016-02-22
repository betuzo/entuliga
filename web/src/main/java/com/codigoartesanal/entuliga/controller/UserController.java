package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.services.UserService;
import com.codigoartesanal.entuliga.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        user.put(UserService.PROPERTY_CONTEXT, request.getContextPath());
        return userService.createUser(user);
    }

    @ResponseBody
    @RequestMapping(
            value = { "" },
            method = {RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> updateUser(@RequestBody Map<String, String> user, HttpServletRequest request) {
        if (user.containsKey(UserTokenService.PROPERTY_TOKEN)) {
            return userService.changePassword(user);
        }
        return userService.createUser(user);
    }
}
