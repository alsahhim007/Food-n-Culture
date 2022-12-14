package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class EmailDetailsTest {
    @Test
    void getValidRecipientTest() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setRecipient("test1@test.ca");
        assertEquals("test1@test.ca", emailDetails.getRecipient());
    }

    @Test
    void getInvalidRecipientTest() {
        EmailDetails emailDetails = new EmailDetails();
        assertNull(emailDetails.getRecipient());
    }

    @Test
    void getValidMessageTest() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setMessage("Testing");
        assertEquals("Testing", emailDetails.getMessage());
    }

    @Test
    void getInvalidMessageTest() {
        EmailDetails emailDetails = new EmailDetails();
        assertNull(emailDetails.getMessage());
    }

    @Test
    void getValidSubjectTest() {
        EmailDetails emailDetails = new EmailDetails();
        emailDetails.setSubject("test2@test.ca");
        assertEquals("test2@test.ca", emailDetails.getSubject());
    }

    @Test
    void getInvalidSubjectTest() {
        EmailDetails emailDetails = new EmailDetails();
        assertNull(emailDetails.getSubject());
    }

    @Test
    void getValidEmailObjectTest() {
        EmailDetails emailDetails = new EmailDetails("test1@test.ca", "Testing", "test2@test.ca");
        assertEquals("test1@test.ca", emailDetails.getRecipient());
        assertEquals("Testing", emailDetails.getMessage());
        assertEquals("test2@test.ca", emailDetails.getSubject());
    }
}
