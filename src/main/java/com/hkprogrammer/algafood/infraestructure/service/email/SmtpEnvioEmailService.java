package com.hkprogrammer.algafood.infraestructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.hkprogrammer.algafood.core.email.EmailProperties;
import com.hkprogrammer.algafood.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;


@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSander;
	
	@Autowired
	private EmailProperties emailProperties;
	
	private Configuration freemarkerConfig;
	
	@Override
	public void enviar(Mensagem message) {
		try {
			String corpo = processarTemplate(message);
			
			MimeMessage mimeMessage = mailSander.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(message.getAssunto());
			helper.setTo(message.getDestinatarios().toArray(new String[0]));
			helper.setSubject(message.getAssunto());
			helper.setText(corpo, true);
			
			mailSander.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
	}
	
	private String processarTemplate(Mensagem mensagem) {
		try {
			Template template = freemarkerConfig.getTemplate(mensagem.getCorpo());
			
			return FreeMarkerTemplateUtils.processTemplateIntoString(template, mensagem.getVariaveis());
		} catch (Exception e) {
			throw new EmailException("Não foi possível montar o template do e-mail", e);
		}
	}
	
}
