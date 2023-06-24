package com.learning.core.services.email.adapters;

import com.learning.core.services.email.dtos.EmailParams;

public interface EmailService {

    void sendMailTemplateHtml(EmailParams params);
}
