package br.com.xfrontier.housekeeper.core.services.email.providers;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.xfrontier.housekeeper.core.services.email.adapters.EmailService;
import br.com.xfrontier.housekeeper.core.services.email.dtos.EmailParams;
import br.com.xfrontier.housekeeper.core.services.email.exceptions.EmailServiceException;

import jakarta.mail.MessagingException;

@Service
public class JavaMailService implements EmailService {

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMailTemplateHtml(EmailParams params) {
        var mimeMessage= mailSender.createMimeMessage();
        var mimeMessageHelper= new MimeMessageHelper(mimeMessage);

        var context= new Context();
        context.setVariables(params.getProps());

        var html= templateEngine.process(params.getTemplate(), context);

        try {
            mimeMessageHelper.setFrom("not-answer@ehousekeeper.com", "E-Housekepper");
            mimeMessageHelper.setTo(params.getAddressee());
            mimeMessageHelper.setSubject(params.getSubject());
            mimeMessageHelper.setText(html, true);
        } catch (UnsupportedEncodingException exception) {
            throw new EmailServiceException(exception.getLocalizedMessage());
        } catch (MessagingException exception) {
            throw new EmailServiceException(exception.getLocalizedMessage());
        }

        mailSender.send(mimeMessage);

    }

}
