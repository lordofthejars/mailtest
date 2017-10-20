package org.superbiz.cube.mail;

import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.config.ServerConfig;
import org.simplejavamail.mailer.config.TransportStrategy;

public class MailService {

    private ServerConfig serverConfig;

    public MailService(String smtpServer, int port) {
        this.serverConfig = new ServerConfig(smtpServer, port);
    }

    public void send(String name, String address, String subject, String content) {
        final Email email = new Email();
        email.setFromAddress("lollypop", "lolly.pop@mymail.com");
        email.addNamedToRecipients(name, address);

        email.setSubject(subject);
        email.setText(content);

        new Mailer(this.serverConfig, TransportStrategy.SMTP_PLAIN).sendMail(email);

    }

}
