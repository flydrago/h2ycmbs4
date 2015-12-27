package com.h2y.mail;

import java.io.Serializable;
import java.util.Properties;

/**
 * 发送邮件需要使用的基本信息

 * 作者：段晓刚

 * 时间：2014-3-4 下午7:28:48

 * Gmail :
 */
public class MailInfo implements Serializable{ 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 发送邮件的服务器的IP和端口 
	private String smtpHost = "smtp.exmail.qq.com"; 

	private int smtpPort = 25; 
	// 邮件发送者的地址 
	private String fromAddress; 
	// 邮件接收者的地址 
	private String toAddress; 
	// 登陆邮件发送服务器的用户名和密码 
	private String userName; 

	private String password; 

	// 是否需要身份验证 
	private boolean validate = false; 
	// 邮件主题 
	private String subject; 

	// 邮件的文本内容 
	private String content; 

	// 邮件附件的文件名 
	private String[] attachFileNames; 	
	/** 
	 * 获得邮件会话属性 
	 */ 
	public Properties getProperties(){ 
		Properties p = new Properties(); 
		p.put("mail.smtp.host", this.smtpHost); 
		p.put("mail.smtp.port", this.smtpPort); 
		p.put("mail.smtp.auth", validate ? "true" : "false"); 
		return p; 
	} 
	public String getSmtpHost() { 
		return smtpHost; 
	} 
	public void setSmtpHost(String smtpHost) { 
		this.smtpHost = smtpHost; 
	}
	
	public int getSmtpPort() { 
		return smtpPort; 
	}
	
	public void setSmtpPort(int smtpPort) { 
		this.smtpPort = smtpPort; 
	}
	
	public boolean isValidate() { 
		return validate; 
	}
	public void setValidate(boolean validate) { 
		this.validate = validate; 
	}
	public String[] getAttachFileNames() { 
		return attachFileNames; 
	}
	public void setAttachFileNames(String[] fileNames) { 
		this.attachFileNames = fileNames; 
	}
	public String getFromAddress() { 
		return fromAddress; 
	} 
	public void setFromAddress(String fromAddress) { 
		this.fromAddress = fromAddress; 
	}
	public String getPassword() { 
		return password; 
	}
	public void setPassword(String password) { 
		this.password = password; 
	}
	public String getToAddress() { 
		return toAddress; 
	} 
	public void setToAddress(String toAddress) { 
		this.toAddress = toAddress; 
	} 
	public String getUserName() { 
		return userName; 
	}
	public void setUserName(String userName) { 
		this.userName = userName; 
	}
	public String getSubject() { 
		return subject; 
	}
	public void setSubject(String subject) { 
		this.subject = subject; 
	}
	public String getContent() { 
		return content; 
	}
	public void setContent(String textContent) { 
		this.content = textContent; 
	} 
} 