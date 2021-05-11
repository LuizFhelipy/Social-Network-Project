package br.com.socialnetwork.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class CommentsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "luiz@email.com", password = "123456", roles = "USER")
	void ShouldRegisterComment() throws Exception {
		String data = "{\"message\":\"testMessage\"}";
		Long id = 1L;

		mockMvc.perform(MockMvcRequestBuilders.post("/publications/" + id + "/comments").content(data)
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().is(201))
				.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("title")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("message")))
				.andExpect(MockMvcResultMatchers.content().string(containsString("file")));
	}

}
