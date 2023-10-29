package com.example.web2_lab1;

import com.example.web2_lab1.service.CompetitionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.web2_lab1.domain.request.NewCompetitionRequest;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompetitionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompetitionService competitionService;

    @Test
    public void testAddCompetition() throws Exception {
        NewCompetitionRequest request = new NewCompetitionRequest(
                "Test Competition",
                "Competitor1, Competitor2, Competitor3",
                "1/2/3",
                "user123"
        );

        mockMvc.perform(post("/competition/api/newCompetition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(request)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}

