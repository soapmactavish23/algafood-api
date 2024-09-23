package com.hkprogrammer.algafood.infraestructure.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.hkprogrammer.algafood.core.email.EmailProperties;
import com.hkprogrammer.algafood.domain.service.EnvioEmailService;


@Service
public class SmtpEnvioEmailService implements EnvioEmailService {

	@Autowired
	private JavaMailSender mailSander;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Override
	public void enviar(Message message) {
		
		try {
			MimeMessage mimeMessage = mailSander.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(emailProperties.getRemetente());
			helper.setSubject(message.getAssunto());
			helper.setTo(message.getDestinatarios().toArray(new String[0]));
			helper.setSubject(message.getAssunto());
			helper.setText(message.getCorpo(), true);
			
			mailSander.send(mimeMessage);
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail", e);
		}
		
	}

}
