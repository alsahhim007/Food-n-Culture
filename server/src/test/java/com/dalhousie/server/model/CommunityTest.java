package com.dalhousie.server.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class CommunityTest {
    @Test
    void getValidIdTest() {
        Community community = new Community();
        community.setId(1);
        assertEquals(1, community.getId());
    }

    @Test
    void getInvalidIdTest() {
        Community community = new Community();
        assertEquals(0, community.getId());
    }

    @Test
    void getValidTitleTest() {
        Community community = new Community();
        community.setTitle("Community Title");
        assertEquals("Community Title", community.getTitle());
    }

    @Test
    void getInvalidTitleTest() {
        Community community = new Community();
        assertNull(community.getTitle());
    }

    @Test
    void getValidDescriptionTest() {
        Community community = new Community();
        community.setDescription("Community Description");
        assertEquals("Community Description", community.getDescription());
    }

    @Test
    void getInvalidDescriptionTest() {
        Community community = new Community();
        assertNull(community.getDescription());
    }

    @Test
    void getValidUpdatedAtTest() {
        Community community = new Community();
        community.setUpdatedAt("2022-12-13 01:30:00");
        assertEquals("2022-12-13 01:30:00", community.getUpdatedAt());
    }

    @Test
    void getInvalidUpdatedAtTest() {
        Community community = new Community();
        assertNull(community.getUpdatedAt());
    }

    @Test
    void getValidCreatedAtTest() {
        Community community = new Community();
        community.setCreatedAt("Community Description");
        assertEquals("Community Description", community.getCreatedAt());
    }

    @Test
    void getInvalidCreatedAtTest() {
        Community community = new Community();
        assertNull(community.getCreatedAt());
    }
}
