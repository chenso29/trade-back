package src.test.java.com.trade_accounting.services.impl.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


@ExtendWith(MockitoExtension.class)
class MailServiceImplTest {
	
	@InjectMocks
	private MailServiceImpl mailService;
	
	@Mock
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String username;
	
	@Mock
	SimpleMailMessage simpleMailMessage;
	@Test
	void sendEmail() {
		mailService.sendEmail("","","");
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(username);
		simpleMailMessage.setTo("");
		simpleMailMessage.setSubject("");
		simpleMailMessage.setText("");
		Mockito.verify(javaMailSender, Mockito.times(1))
				.send(new SimpleMailMessage(simpleMailMessage));
	}
}