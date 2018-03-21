package com.codigoartesanal.entuliga.services.impl;

import com.codigoartesanal.entuliga.services.MailService;
import com.microtripit.mandrillapp.lutung.MandrillApi;
import com.microtripit.mandrillapp.lutung.model.MandrillApiError;
import com.microtripit.mandrillapp.lutung.view.MandrillMessage;
import com.microtripit.mandrillapp.lutung.view.MandrillMessageStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@Service
public class MandrillMailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MandrillMailServiceImpl.class);

    @Autowired
    private MandrillApi mandrillApi;

    @Override
    public void sendTempleate(final String templeate, final Map<String, String> to, final Map<String, String> params) {
        MandrillMessage message = new MandrillMessage();

        ArrayList<MandrillMessage.Recipient> recipients = new ArrayList<>();
        to.forEach((key, value) -> {
            MandrillMessage.Recipient recipient = new MandrillMessage.Recipient();
            recipient.setEmail(key);
            recipient.setName(value);
            recipients.add(recipient);
        });
        message.setTo(recipients);
        message.setPreserveRecipients(true);

        try {
            MandrillMessageStatus[] messageStatusReports = mandrillApi
                    .messages().sendTemplate(templeate, params, message, false);
        } catch (MandrillApiError | IOException mandrillApiError) {
            logger.info("No se puede enviar el mail de registro", mandrillApiError);
        }
    }
}
