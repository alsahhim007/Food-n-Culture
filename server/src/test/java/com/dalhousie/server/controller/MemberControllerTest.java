package com.dalhousie.server.controller;

import com.dalhousie.server.AbstractTest;
import com.dalhousie.server.model.EventMember;
import com.dalhousie.server.repository.EventMemberRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class MemberControllerTest extends AbstractTest {

    @MockBean
    EventMemberRepository eventMemberRepository;
    
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
    }
    
    private EventMember getMember() {
        EventMember member = new EventMember();
        member.setStatus("active");
        member.setUpdatedAt("2022-10-11 00:00:00");
        member.setCreatedAt("2022-10-11 00:00:00");
        return member;
    }

    @Test
    void addMemberTest() throws Exception {
        Mockito.doReturn(1).when(eventMemberRepository).save(any());

        mvc.perform(MockMvcRequestBuilders.post("/api/members/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMember())))
            .andExpect(status().isCreated())
            .andExpect(content().string("Member created successfully"));
    }

    @Test
    void addMemberFailedTest() throws Exception {
        Mockito.when(eventMemberRepository.save(getMember())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.post("/api/members/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMember())))
            .andExpect(status().isBadRequest());
    }

    @Test
    void getAllMembersTest() throws Exception {
        List<EventMember> eventMembers = new ArrayList<>();
        eventMembers.add(getMember());
        Mockito.when(eventMemberRepository.findAll()).thenReturn(eventMembers);

        mvc.perform(MockMvcRequestBuilders.get("/api/members/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].status").value("active"));
    }

    @Test
    void getMemberTest() throws Exception {
        Mockito.when(eventMemberRepository.getById(99)).thenReturn(Optional.of(getMember()));
        mvc.perform(MockMvcRequestBuilders.get("/api/members/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.status").value("active"));
    }

    @Test
    void getMemberNotFoundTest() throws Exception {
        Mockito.when(eventMemberRepository.getById(999)).thenReturn(Optional.empty());
        mvc.perform(MockMvcRequestBuilders.get("/api/members/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    void updateMemberTest() throws Exception {
        Mockito.when(eventMemberRepository.getById(99)).thenReturn(Optional.of(getMember()));
        Mockito.when(eventMemberRepository.update(getMember())).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.put("/api/members/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMember())))
            .andExpect(status().isOk())
            .andExpect(content().string("Member updated successfully"));
    }

    @Test
    void updateMemberNotFoundTest() throws Exception {
        Mockito.when(eventMemberRepository.getById(999)).thenReturn(Optional.empty());
        Mockito.when(eventMemberRepository.update(getMember())).thenReturn(0);

        mvc.perform(MockMvcRequestBuilders.put("/api/members/{id}", 999)
            .contentType(MediaType.APPLICATION_JSON)
            .content(super.mapToJson(getMember())))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteMemberTest() throws Exception {
        Mockito.when(eventMemberRepository.deleteById(99)).thenReturn(1);

        mvc.perform(MockMvcRequestBuilders.delete("/api/members/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Member deleted successfully"));
    }
    
    @Test
    void getMembersByEventIdTest() throws Exception {
        List<EventMember> eventMembers = new ArrayList<>();
        eventMembers.add(getMember());
        Mockito.when(eventMemberRepository.getMembersByEventId(99)).thenReturn(eventMembers);

        mvc.perform(MockMvcRequestBuilders.get("/api/members/events/{id}", 99)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].status").value("active"));
    }
}
