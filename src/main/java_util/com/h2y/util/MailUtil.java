package com.h2y.util;

import com.h2y.mail.MailInfo;

/**
 * 邮件发送工具

 * @author：段晓刚

 * @update：2015年4月7日 上午11:20:31

 * @Email：
 */
public class MailUtil {

    private static MailUtil mMailUtil;

    private PropertiesUtil mPropertiesUtil;

    //private String MyMailName = "魔力邮箱";
    private String MyMailAddr = "moolly@imooly.com";
    private String MyMailCode = "ml2013";
    private String SendTilte = "魔力邮箱验证";
    private String SendingMailServer = "smtp.exmail.qq.com";

    private MailUtil() {
        mPropertiesUtil = PropertiesUtil.getInstance("/config.properties");
        mPropertiesUtil.getValueByKey("mail_url");
    }

    public static MailUtil getInstance() {
        if (mMailUtil == null)
            mMailUtil = new MailUtil();

        return mMailUtil;
    }

    /**
     * 发送邮箱验证码
     *
     * @param toAddress
     * @param code
     * @return
     */
    public int sendMail(String toAddress, String code) {
        //MailInfo mMailInfo = getMailInfo(toAddress, code);
        //使用JavaMail进行短信发送
        //if(MailSend.sendTextMail(mMailInfo))
        //	return 1;

        //调用统一的url接口进行短信发送


        return 0;
    }

    public int sendMailByUrl(String url, String toAddress, String code) {


        return 0;
    }


    public MailInfo getMailInfo(String toAddress, String code) {
        MailInfo mMailInfo = new MailInfo();
        mMailInfo.setSmtpHost(SendingMailServer);
        //mMailInfo.setSmtpPort(465);
        mMailInfo.setSmtpPort(25);
        mMailInfo.setValidate(true);
        mMailInfo.setFromAddress(MyMailAddr);
        mMailInfo.setUserName(MyMailAddr);
        mMailInfo.setPassword(MyMailCode);
        mMailInfo.setSubject(SendTilte);
        mMailInfo.setToAddress(toAddress);
        mMailInfo.setContent("你好，你的验证码为：" + code);

        return mMailInfo;
    }

    //	public static void main(String args[]){
    //		MailUtil mMailUtil = MailUtil.getInstance();
    //		int flg = mMailUtil.sendMail("java198921@163.com", "456789");
    //		System.out.println(flg);
    //	}
}
