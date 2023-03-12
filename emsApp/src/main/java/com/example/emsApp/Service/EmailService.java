package com.example.emsApp.Service;

import com.example.emsApp.Entity.EmailRequest;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
public class EmailService {

    @Value("${sendgrid.api.key}")
    private String sendgridApiKey;

    public ResponseEntity<String> sendEmail(EmailRequest emailRequest) throws IOException {

        Email from = new Email(emailRequest.getFrom());
        String subject = emailRequest.getSubject();
        Email to = new Email(emailRequest.getTo());
        Content content = new Content("text/plain", emailRequest.getBody()); // Content represents the body of an email
        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(sendgridApiKey);
        Request request = new Request();
        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");

        request.setBody(mail.build());
        Response response = sg.api(request);


        if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
            return ResponseEntity.ok("Email sent successfully.");
        } else {
            return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
        }
    }
}
