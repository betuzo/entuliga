package com.codigoartesanal.entuliga.controller;

import com.codigoartesanal.entuliga.config.security.JwtTokenUtil;
import com.codigoartesanal.entuliga.config.security.JwtUserFactory;
import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.mobile.device.DeviceUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codigoartesanal.entuliga.services.UserService.*;
import static com.codigoartesanal.entuliga.services.UserTokenService.PROPERTY_TOKEN;

/**
 * Created by betuzo on 07/04/15.
 */
@Controller
@RequestMapping("/session")
public class SessionController {

    @Autowired
    UserRepository userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @ResponseBody
    @RequestMapping(
            value = { "/login" },
            method = {RequestMethod.POST, RequestMethod.PUT},
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> login(
            @RequestBody Map<String, String> user, HttpServletRequest httpRequest) {
        Device device = DeviceUtils.getCurrentDevice(httpRequest);

        if (!user.containsKey(PROPERTY_USERNAME) || !user.containsKey(PROPERTY_PASSWORD)){
            throw new BadCredentialsException("Username or password not found.");
        }

        final User usuario = userService.findByUsername(user.get(PROPERTY_USERNAME));
        if (usuario == null || !passwordEncoder.matches(user.get(PROPERTY_PASSWORD), usuario.getPassword())){
            throw new BadCredentialsException("Username or password incorrect.");
        }
        List<GrantedAuthority> roles = JwtUserFactory.mapToGrantedAuthorities(usuario.getUserRole());
        final String token = jwtTokenUtil.generateToken(usuario.getUsername(), device, roles);
        final Map<String, Object> sessionDTO = new HashMap<>();
        sessionDTO.put(PROPERTY_USERNAME, usuario.getUsername());
        sessionDTO.put(PROPERTY_EMAIL, usuario.getUsername());
        sessionDTO.put(PROPERTY_ROLES, roles);
        sessionDTO.put(PROPERTY_TOKEN, token);

        return sessionDTO;
    }

    @ResponseBody
    @RequestMapping(
            value = { "/valid/{token}" },
            method = RequestMethod.GET,
            produces = {"application/json;charset=UTF-8"})
    public Map<String, Object> isValidToken(@PathVariable("token") String token) {
        Map<String, Object> response = new HashMap<>();
        response.put("token", "token");
        return response;
    }


}
