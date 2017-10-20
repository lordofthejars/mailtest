package org.superbiz.cube.mail;

import org.junit.ClassRule;
import org.junit.Test;

public class MailServiceTest {

    @ClassRule
    public static MailhogContainer mailhog = MailhogContainer.createDefaultMailhogContainer();

    @Test
    public void should_send_an_email() throws Exception {

        // given
        final MailService mailService = new MailService(
            mailhog.getIpAddress(),
            mailhog.getBindPort(1025));

        // when
        mailService.send("lollypop", "lolly.pop@mymail.com", "Invoice: 1234",
            "Thank you very much for buying this product. Here's your invoice");

        // then
        mailhog.should_receive_email_with_subject("Invoice: 1234");
    }

}
