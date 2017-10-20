package org.superbiz.cube.mail;

import io.restassured.RestAssured;
import java.util.ArrayList;
import org.arquillian.cube.docker.junit.rule.ContainerDslRule;
import org.hamcrest.CoreMatchers;

public class MailhogContainer extends ContainerDslRule {
    protected MailhogContainer(String image) {
        super(image);
    }

    public static MailhogContainer createDefaultMailhogContainer() {
        MailhogContainer mailhogContainer = new MailhogContainer("mailhog/mailhog:v1.0.0");
        mailhogContainer.withPortBinding(1025, 8025);
        return mailhogContainer;
    }

    public void should_receive_email_with_subject(String subject) {
        final ArrayList<String> expected = new ArrayList<>();
        expected.add(subject);

        RestAssured.given()
            .when()
            .get("http://" + this.getIpAddress() + ":" + this.getBindPort(8025) + "/api/v1/messages")
            .then()
            .assertThat()
            .body("Content.Headers.Subject", CoreMatchers.hasItems(expected));
    }

}
