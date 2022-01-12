package org.alkemy.disneyapi.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import lombok.RequiredArgsConstructor;


@Service @RequiredArgsConstructor

public class EmailService {
	
	private final SendGrid sendGrid;
	
	@Value("${app.sendgrid.myEmail}")
	private String myEmail;
	
	public String sendEmail(String email, String username) throws IOException {
		
		Email from = new Email(myEmail);
	    String subject = "Disney API";
	    Email to = new Email(email);
	    Content content = new Content("text/plain", "Dear "+ username + ", welcome to the Disney API. Now you can explore the World of Disney with a few clicks.");
	    Mail mail = new Mail(from, subject, to, content);
	    
	    Request request = new Request();
	    try {
	      request.setMethod(Method.POST);
	      request.setEndpoint("mail/send");
	      request.setBody(mail.build());
	      
	      Response response = sendGrid.api(request);
	      System.out.println(response.getStatusCode());
	      System.out.println(response.getBody());
	      System.out.println(response.getHeaders());
	    } catch (IOException ex) {
	      throw ex;
	    }

		return null;
	}

}
