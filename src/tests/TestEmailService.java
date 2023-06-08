package tests;

import domain.User;
import email.Email;
import email.EmailException;
import email.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestEmailService {

    private static final int NO_EMAILS = 5;
    private User newUser;
    private User recipient;

    @BeforeEach
    public void setup() {
        newUser = new User("alice230", "alice230@cookbook.ro");
        recipient = new User("bob231", "bob231@cookbook.ro");
    }

    @Test
    public void testSendMail() throws InterruptedException {
        EmailService emailService = new EmailService();
        ArrayList<User> recipients = new ArrayList<>();
        recipient = new User("bob231", "bob231@cookbook.ro");
        recipients.add(recipient);

        for (int i = 0; i < NO_EMAILS; i++) {
            try {
                emailService.sendNotificationEmail(
                        new Email()
                                .setTitle("New User has Joined CookBookApp")
                                .setBody("A new user " + newUser.getUsername() + " has joined CookBookApp.")
                                .setFrom(newUser)
                                .setTo(recipients));
            } catch (EmailException e) {
                e.printStackTrace();
            }
            Thread.sleep(1000);
        }

        assertAll("EmailService",
                () -> assertEquals(NO_EMAILS, emailService.getSentEmails(), "Number of sent emails")
        );
    }
}
