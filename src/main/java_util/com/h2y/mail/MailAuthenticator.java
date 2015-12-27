package com.h2y.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 进行邮件验证

 * 作者：段晓刚

 * 时间：2014-3-4 下午7:27:44

 * Gmail :
 */
public class MailAuthenticator extends Authenticator{
	
	String userName=null;
	String password=null;
	 
	public MailAuthenticator(){
	}
	public MailAuthenticator(String username, String password) { 
		this.userName = username; 
		this.password = password; 
	} 
	protected PasswordAuthentication getPasswordAuthentication(){
		return new PasswordAuthentication(userName, password);
	}
}
 
