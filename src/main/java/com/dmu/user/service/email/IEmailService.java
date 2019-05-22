package com.dmu.user.service.email;

public interface IEmailService {
    public void setEmailInfo(String account, String password, String mailHost);
    public boolean send163Mail(String strMail, String strTitle, String strText);
}
