package com.learning.core.listeners;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.learning.core.events.NewUserEvent;
import com.learning.core.models.User;
import com.learning.core.services.email.adapters.EmailService;
import com.learning.core.services.email.dtos.EmailParams;

@Component
public class NewUserListener {

    @Autowired
    private EmailService emailService;

    @EventListener
    public void handleNewUserEvent(NewUserEvent event) {

        var user= event.getUser();

        if (user.isCustomer() || user.isHousekeeper()) {
            var emailParams= createEmailParams(user);
            emailService.sendMailTemplateHtml(emailParams);
        }
    }

    private EmailParams createEmailParams(User user) {
        var props= createEmailProps(user);
        var emailParams= new EmailParams();
        emailParams.setAddressee(user.getEmail());
        emailParams.setSubject("The registration has been successful completed");
        emailParams.setTemplate("emails/welcome");
        emailParams.setProps(props);
        return emailParams;
    }

    private HashMap<String, Object> createEmailProps(User user) {
        var props= new HashMap<String, Object>();
        props.put("name", user.getCompleteName());
        props.put("userType", user.getUserType().name());
        return props;
    }
}
