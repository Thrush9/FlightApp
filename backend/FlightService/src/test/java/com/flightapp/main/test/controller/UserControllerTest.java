package com.flightapp.main.test.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flightapp.main.controller.AuthController;
import com.flightapp.main.model.ERole;
import com.flightapp.main.model.Role;
import com.flightapp.main.model.User;
import com.flightapp.main.service.UserServiceImpl;

import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerTest {

	@LocalServerPort
	int randomServerPort;

	@Autowired
	private MockMvc mockMvc;
	private User user;
	private Role role;
	
	@MockBean
	private UserServiceImpl userService;
	
	@InjectMocks
	private AuthController userController;
	
	private List<User> userList = null;

	@BeforeEach
	public void setUp() throws Exception {

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		user = new User();
		user.setId(101L);
		user.setEmail("Test@gmail.com");
		user.setUsername("Test");
		user.setPassword("Test@123");
		
		Set<Role> roles = new HashSet<>();
		role = new Role();
		role.setId(1);
		role.setName(ERole.ROLE_USER);
		roles.add(role);
		
		user.setRoles(roles);
			
		userList = new ArrayList<>();
	        userList.add(user);
	}

	
	@Test
	public void fetchAllUsersSuccess() throws Exception {
		when(userService.fetchAllUsers()).thenReturn(userList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1.0/flight/fetchAllUsers")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
	}


	private static String asJsonString(final Object obj) {
		try {
			ObjectMapper objmapper = new ObjectMapper();
			objmapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objmapper.registerModule(new JavaTimeModule());
			return objmapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}