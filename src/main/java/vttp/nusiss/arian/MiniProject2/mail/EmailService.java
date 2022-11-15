package vttp.nusiss.arian.MiniProject2.mail;

import org.springframework.stereotype.Service;


public interface EmailService {
     // Method
    // To send a simple email
    String sendSimpleMail(EmailDetails details);
 
}
