package com.codigoartesanal.entuliga.services;

import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

/**
 * Created by betuzo on 15/02/16.
 */
public interface MailService {
    void sendTempleate(final String templeate, final Map<String, String> to, final Map<String, String> params);
}
