package br.com.xfrontier.housekeeper.core.services.email.adapters;

import br.com.xfrontier.housekeeper.core.services.email.dtos.EmailParams;

public interface EmailService {

    void sendMailTemplateHtml(EmailParams params);
}
