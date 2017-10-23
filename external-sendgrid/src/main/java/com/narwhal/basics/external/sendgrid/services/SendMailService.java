package com.narwhal.basics.external.sendgrid.services;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.narwhal.basics.core.rest.exceptions.api.ApiException;
import com.narwhal.basics.core.rest.utils.ApiPreconditions;
import com.narwhal.basics.external.core.model.SendgridSettings;
import com.narwhal.basics.external.core.services.ApplicationSettingsCachedService;
import com.narwhal.basics.external.core.utils.EnvironmentContext;
import com.narwhal.basics.external.sendgrid.dto.MailDTO;
import com.narwhal.basics.external.sendgrid.dto.SendEmailMessage;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailAddress;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailContent;
import com.narwhal.basics.external.sendgrid.dto.endpoint.MailPersonalizations;
import com.narwhal.basics.external.sendgrid.endpoint.SendgridMailEndpoint;
import com.narwhal.basics.external.sendgrid.exceptions.EmailNotSendException;
import com.narwhal.basics.external.sendgrid.types.MailContentTypes;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;
import org.apache.velocity.tools.generic.RenderTool;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Tomas de Priede
 */
@Singleton
public class SendMailService {

    private Logger logger = Logger.getLogger(SendMailService.class.getSimpleName());

    @Inject
    private ApplicationSettingsCachedService settingsCachedService;
    @Inject
    private SendgridMailEndpoint sendgridMailAPI;
    private VelocityEngine velocityEngine;

    public void sendMail(String namespaceId, SendEmailMessage emailMessage) {
        ApiPreconditions.checkNotNull(namespaceId, "namespaceId");
        logger.log(Level.INFO, "Task to send email in progress");
        //
        SendgridSettings sendgridSettings = settingsCachedService.getCachedApplicationSettings(namespaceId);
        sendgridSettings.checkSendgridData();
        //
        MailDTO mailDTO = new MailDTO();
        mailDTO.setFrom(new MailAddress(sendgridSettings.getEmailSender()));
        //
        mailDTO.getPersonalizations().add(new MailPersonalizations(new MailAddress(emailMessage.getTo())));
        //
        Context map = new VelocityContext();
        map.put("ctx", new EnvironmentContext(sendgridSettings.getSendgridAppUrl()));
        map.put("model", emailMessage.getModel());
        //
        RenderTool renderTool = new RenderTool();
        renderTool.setVelocityEngine(velocityEngine);
        renderTool.setCatchExceptions(false);
        //
        try {
            String subject = renderTool.eval(map, emailMessage.getSubject());
            mailDTO.setSubject(subject);
            //
            String bodyPlain = renderTool.eval(map, emailMessage.getPlainTemplate());
            mailDTO.getContent().add(new MailContent(MailContentTypes.PLAIN, bodyPlain));
            //
            String bodyHtml = renderTool.eval(map, emailMessage.getHtmlTemplate());
            mailDTO.getContent().add(new MailContent(MailContentTypes.HTML, bodyHtml));
            //
            sendgridMailAPI.sendMail(namespaceId, mailDTO);
            //
        } catch (ApiException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to render template", e);
            throw new EmailNotSendException(e.getMessage(), e);
        }
    }

    @Inject
    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }
}
