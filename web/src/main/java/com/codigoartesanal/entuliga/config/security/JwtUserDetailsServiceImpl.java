package com.codigoartesanal.entuliga.config.security;

import com.codigoartesanal.entuliga.model.User;
import com.codigoartesanal.entuliga.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by stephan on 20.03.16.
 */
@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usuario = userRepository.findByUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException(String.format("No usuario found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(usuario);
        }
    }
}
