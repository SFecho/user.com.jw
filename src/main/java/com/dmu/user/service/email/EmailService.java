package com.dmu.user.service.email;

import org.springframework.stereotype.Service;

import javax.mail.internet.InternetAddress;

import javax.mail.internet.MimeMessage;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage.RecipientType;

@Service("emailService")
public class EmailService implements IEmailService{
    private String account;
    private String password;
    private String mailHost;

    @Override
    public void setEmailInfo(String account, String password, String mailHost){
        this.account = account;
        this.password = password;
        this.mailHost = mailHost;
    }

    @Override
    public boolean send163Mail(String strMail, String strTitle, String strText){
        boolean bret = false;
        try
        {
            final Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.host", mailHost);
            props.put("mail.user", account);        //你自己的邮箱
            props.put("mail.password", password);//你开启pop3/smtp时的验证码
            props.put("mail.smtp.port", "25");
            props.put("mail.smtp.starttls.enable", "true");
            Authenticator authenticator = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator); // 使用环境属性和授权信息，创建邮件会话
            MimeMessage message = new MimeMessage(mailSession);// 创建邮件消息
            String username = props.getProperty("mail.user");// 设置发件人
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);
            InternetAddress to = new InternetAddress(strMail);
            message.setRecipient(RecipientType.TO, to);
            message.setSubject(strTitle);            // 设置邮件标题
            message.setContent(strText, "text/html;charset=UTF-8");      // 设置邮件的内容体
            Transport.send(message);            // 发送邮件
            bret = true;
        }
        catch (AddressException e) {
            System.out.println("1");
            e.printStackTrace();
        }
        catch (MessagingException e) {
            System.out.println("2");
            e.printStackTrace();
        }
        catch (Exception e){
            System.out.println("3");
            e.printStackTrace();
        }
        return bret;
    }

}