package com.psycho.backend.controller;

import com.psycho.backend.BackendApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BackendApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.yaml"
)
public class AuthRestControllerIntegrationTest {

    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

    @Before
    public void setMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .build();
    }

    @Test
    public void testSuccessRegisterEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/register")
                        .content("{\n" +
                                "    \"username\": \"abc\",\n" +
                                "    \"password\": \"123\",\n" +
                                "    \"firstname\": \"firstname\",\n" +
                                "    \"lastname\": \"lastname\",\n" +
                                "    \"email\": \"moseyarthas@gmail.com\",\n" +
                                "    \"phone\": \"+375333840226\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testSuccessLoginEndpoint() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .content("{\n" +
                                "    \"username\": \"abc\",\n" +
                                "    \"password\": \"123\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testLoginWithWrongPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
                        .content("{\n" +
                                "    \"username\": \"abc\",\n" +
                                "    \"password\": \"321\"\n" +
                                "}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
