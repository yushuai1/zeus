package com.发送邮件.javaxmail;

public class Test {

    public static void main(String[] asd) throws Exception {
        MailSender mailSender = new MailSender();
        mailSender.sendTextMail("18513504157@163.com");
    }
}
