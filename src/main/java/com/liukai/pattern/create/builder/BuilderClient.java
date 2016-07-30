package com.liukai.pattern.create.builder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

/**
 * 建造者模式————发送邮件DEMO
 * Created by Administrator on 2016/7/30 0030.
 */
public class BuilderClient {

    public static void main(String[] args) {

        String fromAddress = "xxx@xx.com";
        String toAddress = "xxx@xx.com";
        WelcomeBuilder welcomeBuilder = new WelcomeBuilder();
        Director director = new Director(welcomeBuilder);
        director.construct(toAddress, fromAddress);
    }
}

/**
 * 导演者角色————负责指挥建造者角色建造零件以及产品
 */
class Director {

    private Builder builder;

    public Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构造方法，负责调用各零件的建造方法
     */
    public void construct(String toAddress, String fromAddress) {
        builder.buildTo(toAddress);
        builder.buildFrom(fromAddress);
        builder.buildBody();
        builder.buildSendDate();
        builder.buildSubject();
        builder.sendMessage();
    }
}

/**
 * 具体建造者角色————欢送邮件的建造者
 */
class GoodByeBuilder extends Builder {

    private static final String subject = "Thank you for being with us!";

    public GoodByeBuilder() {
        this.message = new GoodByeMessage();
    }

    /**
     * 主题零件的建造方法
     */
    public void buildSubject() {
        this.message.setSubject(subject);
    }

    /**
     * 消息主体零件的建造方法
     */
    public void buildBody() {
        this.message.setBody("Oops! You have chosen to leave.");
    }
}

/**
 * 具体建造者角色————欢迎邮件的建造者
 */
class WelcomeBuilder extends Builder {

    private static final String subject = "Welcome to philharmonic news group!";

    public WelcomeBuilder() {
        this.message = new WelcomeMessage();
    }

    /**
     * 主题零件的建造方法
     */
    public void buildSubject() {
        message.setSubject(subject);
    }

    /**
     * 消息主体零件的建造方法
     */
    public void buildBody() {
        message.setBody("Congratulations for making the right choice!");
    }
}

/**
 * 抽象建造者角色————负责建造产品的零件以及返回产品
 */
abstract class Builder {

    protected AutoMessage message;

    /**
     * 主题零件的建造的方法
     */
    public abstract void buildSubject();

    /**
     * 消息主体零件的建造方法
     */
    public abstract void buildBody();

    /**
     * 发件人零件的建造方法
     *
     * @param from
     */
    public void buildFrom(String from) {
        message.setFrom(from);
    }

    /**
     * 收件人零件的建造方法
     *
     * @param to
     */
    public void buildTo(String to) {
        message.setTo(to);
    }

    /**
     * 发件时间的建造方法
     */
    public void buildSendDate() {
        message.setSendDate(new Date());
    }

    /**
     * 邮件产品完成后，用此方法发送邮件
     * 此方法相当于产品返回方法
     */
    public void sendMessage() {
        message.send();
    }
}


/**
 * 具体产品角色————欢送邮件
 */
class GoodByeMessage extends AutoMessage {

    public GoodByeMessage() {
        System.out.println("Entering GoodBye Message.");
    }

    /**
     * 某个商业方法
     */
    public void sayGoodBye() {
        System.out.println("GoodBye.");
    }
}

/**
 * 具体产品角色————欢迎邮件
 */
class WelcomeMessage extends AutoMessage {

    public WelcomeMessage() {
        System.out.println("Entering Welcome Message.");
    }

    /**
     * 某个商业方法
     */
    public void sayWelcome() {
        System.out.println("Welcome.");
    }
}

/**
 * 抽象产品角色————自动邮件
 */
abstract class AutoMessage {

    private static final String MAIL_TRANSPORT_PROTOCOL = "mail.transport.protocol";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_DEBUG = "mail.debug";

    // 邮件发送协议
    private final static String PROTOCOL = "smtp";
    // SMTP邮件服务器
    private final static String HOST = "smtp.163.com";
    // SMTP邮件服务器默认端口
    private final static String PORT = "25";
    // 是否要求身份认证
    private final static String IS_AUTH = "true";
    // 是否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息）
    private final static String IS_ENABLED_DEBUG_MOD = "true";
    // 初始化连接邮件服务器的会话信息
    private static Properties props = null;

    static {
        props = new Properties();
        props.setProperty(MAIL_TRANSPORT_PROTOCOL, PROTOCOL);
        props.setProperty(MAIL_SMTP_HOST, HOST);
        props.setProperty(MAIL_SMTP_PORT, PORT);
        props.setProperty(MAIL_SMTP_AUTH, IS_AUTH);
        props.setProperty(MAIL_DEBUG, IS_ENABLED_DEBUG_MOD);
    }

    private String from = "jeff.yan@mycompany.com";//发送者地址
    private String to = "ni.hao@yourcompany.com";//收件者地址
    private String subject = "";//邮件主题
    private String body = "";//邮件主体
    private Date sendDate;//发送日期

    /**
     * 发送邮件
     */
    public void send() {

        Session session = Session.getInstance(props, new MyAuthenticator());
        try {
            InternetAddress[] address = {new InternetAddress(to)};
            //创建message对象
            MimeMessage message = new MimeMessage(session);
            //建造发件人地址零件
            message.setFrom(new InternetAddress(from));
            //建造收件人地址零件
            message.setRecipients(Message.RecipientType.TO, to);
            //建造主题零件
            message.setSubject(subject);
            //建造发送时间零件
            message.setSentDate(sendDate);
            //建造内容零件
            message.setText(body);
            //发送邮件，相当于产品接收方法
            Transport.send(message, address);
            System.out.println("email has been sent.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    /**
     * 邮件服务器的认证类
     */
    static class MyAuthenticator extends Authenticator {

        private String userName = "xxx";//邮件服务器的用户名
        private String password = "xxx";//邮件服务器的密码

        public MyAuthenticator() {
            super();
        }

        public MyAuthenticator(String userName, String password) {
            super();
            this.userName = userName;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
    }
}



