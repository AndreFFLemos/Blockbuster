package Blockbuster.Service;

import Blockbuster.Model.Email;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(Email email) throws MessagingException{
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper= new MimeMessageHelper(mimeMessage,"utf-8");

        mimeMessageHelper.setFrom(email.getSender());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setText(email.getBody(),true);
        mimeMessageHelper.setTo(email.getReceivers().toArray(new String[email.getReceivers().size()]));

    javaMailSender.send(mimeMessage);
    }
}
