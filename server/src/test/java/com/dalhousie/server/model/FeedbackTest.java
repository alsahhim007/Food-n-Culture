package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class FeedbackTest {
    @Test
    void getValidIdTest() {
        Feedback feedback = new Feedback();
        feedback.setId(1);
        assertEquals(1, feedback.getId());
    }

    @Test
    void getInvalidIdTest() {
        Feedback feedback = new Feedback();
        assertEquals(0, feedback.getId());
    }

    @Test
    void getValidMemberIdTest() {
        Feedback feedback = new Feedback();
        feedback.setMemberId(1);
        assertEquals(1, feedback.getMemberId());
    }

    @Test
    void getInvalidMemberIdTest() {
        Feedback feedback = new Feedback();
        assertEquals(0, feedback.getMemberId());
    }

    @Test
    void getValidCommentTest() {
        Feedback feedback = new Feedback();
        feedback.setComment("Something to write");
        assertEquals("Something to write", feedback.getComment());
    }

    @Test
    void getInvalidCommentTest() {
        Feedback feedback = new Feedback();
        assertNull(feedback.getComment());
    }

    @Test
    void getValidStarsTest() {
        Feedback feedback = new Feedback();
        feedback.setStars("4.5");
        assertEquals("4.5", feedback.getStars());
    }

    @Test
    void getInvalidStarsTest() {
        Feedback feedback = new Feedback();
        assertNull(feedback.getStars());
    }

    @Test
    void getValidUpdatedAtTest() {
        Feedback feedback = new Feedback();
        feedback.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", feedback.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Feedback feedback = new Feedback();
        assertNull(feedback.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Feedback feedback = new Feedback();
        feedback.setCreatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", feedback.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Feedback feedback = new Feedback();
        assertNull(feedback.getCreatedAt());
    }
}
