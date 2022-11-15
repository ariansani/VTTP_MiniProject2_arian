package vttp.nusiss.arian.MiniProject2.mail;

import org.springframework.stereotype.Service;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Service
public class EmailServiceImpl implements EmailService{


    @Value("${spring.mail.sender}") 
    private String sender;
    @Value("${spring.mail.host}")
    private String mailHost;
    @Value("${spring.mail.port}")
    private String mailPort;
    @Value("${spring.mail.username}")
    private String mailUserName;
    @Value("${spring.mail.password}")
    private String mailPassword;
    @Value("${spring.mail.properties.mail.smtp.auth}")
    private boolean mailPropertiesAuth;
    @Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private boolean mailStartTLS;
    @Value("${spring.mail.properties.mail.transport.protocol}")
    private String mailSMTP;
    

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailHost);
        mailSender.setPort(Integer.parseInt(mailPort));
        
        mailSender.setUsername(mailUserName);
        mailSender.setPassword(mailPassword);
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailSMTP);
        props.put("mail.smtp.auth", mailPropertiesAuth);
        props.put("mail.smtp.starttls.enable", mailStartTLS);
        props.put("mail.debug", "true");
        
        return mailSender;
    }
 
    // Method 1
    // To send a simple email
    public String sendSimpleMail(EmailDetails details)
    {
 
        // Try block to check for exceptions
        try {
 
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                = new SimpleMailMessage();
 
            // Setting up necessary details
            mailMessage.setFrom(sender);

            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
 
            JavaMailSender javaMailSender = getJavaMailSender();
            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
 
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
