package com.flightapp.main.test.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.flightapp.main.model.ERole;
import com.flightapp.main.model.Role;
import com.flightapp.main.model.User;
import com.flightapp.main.repository.UserRepository;
import com.flightapp.main.service.UserServiceImpl;


public class UserServiceImplTest {
	@Mock
	UserRepository userRepo;

	User user;
	
	Role role;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
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
	}

	@Test
	public void saveUserSuccessTest()  {
		when(userRepo.findById(101L)).thenReturn(Optional.empty());
		when(userRepo.save(Mockito.any(User.class))).thenReturn(new User());
		Mockito.when(userRepo.save(user)).thenReturn(user);
		User status = userServiceImpl.saveUser(user);
		assertThat(user, is(status));

	}
	
	@Test
	public void findByUsernameSuccessTest()  {
		Mockito.when(userRepo.save(user)).thenReturn(user);
		userServiceImpl.saveUser(user);
		Mockito.when(userRepo.existsByUsername(user.getUsername())).thenReturn(true);
		Boolean status = userServiceImpl.findByUsername("Test");
		assertThat(status, is(true));
	}
	
	@Test
	public void findByEmailSuccessTest()  {
		Mockito.when(userRepo.save(user)).thenReturn(user);
		userServiceImpl.saveUser(user);
		Mockito.when(userRepo.existsByEmail(user.getEmail())).thenReturn(true);
		Boolean status = userServiceImpl.findByEmail("Test@gmail.com");
		assertThat(status, is(true));
	}
	
	@Test
	public void findUserSuccessTest()  {
		Optional<User> optUser = Optional.of(user);
		Mockito.when(userRepo.save(user)).thenReturn(user);
		userServiceImpl.saveUser(user);
	    Mockito.when(userRepo.findByUsername(user.getUsername())).thenReturn(optUser);
		Optional<User> status = userServiceImpl.findUser("Test");
		assertThat(optUser, is(status));
	}

    @Test
    public void fetchAllUsersSucessTest() {
    	List<User> usersList = new ArrayList<>();
    	usersList.add(user);
    	Mockito.when(userRepo.save(user)).thenReturn(user);
		userServiceImpl.saveUser(user);
		Mockito.when(userRepo.findAll()).thenReturn(usersList);
		List<User> users = userServiceImpl.fetchAllUsers();
		assertThat(usersList, is(users));
    }

}