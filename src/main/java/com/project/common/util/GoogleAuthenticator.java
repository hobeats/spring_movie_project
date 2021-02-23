package com.project.common.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthenticator extends Authenticator {
	
	PasswordAuthentication pa;
	
	public GoogleAuthenticator(){
		
		try {
			String path 
				= getClass().getResource("/prop/google_mail.properties").getPath();
			Properties prop = new Properties();
			prop.load(new FileReader(path));
			String id = prop.getProperty("id");
			String pass = prop.getProperty("pass");
			System.out.println(id+" : "+pass);
			
			pa = new PasswordAuthentication(id, pass);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(" 파일을 찾을 수 없음");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("properties 파일이 읽을 수 없는 파일");
		}
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return pa;
	}
	
	public Properties getProperties() {
		Properties prop = new Properties();
		prop.put("mail.smtp.starttls.enable","true");
		prop.put("mail.smtp.host","smtp.gmail.com");
		prop.put("mail.smtp.auth","true");
		prop.put("mail.smtp.port","587");
		return prop;
	}
}










