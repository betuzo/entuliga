package com.codigoartesanal.entuliga.services;

import org.springframework.mail.SimpleMailMessage;

import java.util.Map;

/**
 * Created by betuzo on 15/02/16.
 */
public interface MailService {
    void send(SimpleMailMessage msg, Map<String, Object> hTemplateVariables);
}
