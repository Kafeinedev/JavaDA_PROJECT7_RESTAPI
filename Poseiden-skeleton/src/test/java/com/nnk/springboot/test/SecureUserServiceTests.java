package com.nnk.springboot.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.impl.SecureUserService;

@ExtendWith(MockitoExtension.class)
class SecureUserServiceTests {

	@Mock
	private UserRepository mockRepository;

	@Mock
	private PasswordEncoder mockEncoder;

	@InjectMocks
	private SecureUserService userService;

	private User user;

	@BeforeEach
	void setUp() {
		user = new User("username", "password", "fullName", "role");
	}

	@Test
	void createUser_whenCalled_returnCreatedUser() {
		when(mockRepository.save(user)).thenReturn(user);

		User test = userService.createUser(user);

		assertThat(test).isEqualTo(user);
	}

	@Test
	void createUser_whenCalled_useRepository() {
		userService.createUser(user);

		verify(mockRepository, times(1)).save(user);
	}

	@Test
	void createUser_whenCalled_encryptPassword() {
		when(mockRepository.save(user)).thenReturn(user);
		when(mockEncoder.encode("password")).thenReturn("encoded");

		User test = userService.createUser(user);

		assertThat(test.getPassword()).isEqualTo("encoded");

	}

	@Test
	void createUser_whenCalled_useEncoder() {
		userService.createUser(user);

		verify(mockEncoder, times(1)).encode("password");
	}

	@Test
	void findAllUsers_whenNoUsersAreFound_returnEmptyList() {
		when(mockRepository.findAll()).thenReturn(new ArrayList<User>());

		List<User> test = userService.findAllUsers();

		assertThat(test).isEmpty();
	}

	@Test
	void findAllUsers_whenUsersAreFound_returnListOfUser() {
		when(mockRepository.findAll()).thenReturn(List.of(user));

		List<User> test = userService.findAllUsers();

		assertThat(test.size()).isEqualTo(1);
	}

	@Test
	void findAllUsers_whenCalled_useRepository() {
		userService.findAllUsers();

		verify(mockRepository, times(1)).findAll();
	}

	@Test
	void findById_whenUserIsNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> userService.findUserById(1));
	}

	@Test
	void findById_whenUserIsFound_returnUser() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));

		User test = userService.findUserById(1);

		assertThat(test).isEqualTo(user);
	}

	@Test
	void findById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));

		userService.findUserById(1);

		verify(mockRepository, times(1)).findById(1);
	}

	@Test
	void updateUser_whenCalled_returnUpdatedUser() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));
		when(mockRepository.save(user)).thenReturn(user);

		User test = userService.updateUser(1, user);

		assertThat(test).isEqualTo(user);
	}

	@Test
	void updateUser_whenUserDoesntExist_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> userService.updateUser(1, user));
	}

	@Test
	void updateUser_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));

		userService.updateUser(1, user);

		verify(mockRepository, times(1)).findById(1);
		verify(mockRepository, times(1)).save(user);
	}

	@Test
	void updateUser_whenCalled_encryptPassword() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));
		when(mockRepository.save(user)).thenReturn(user);
		when(mockEncoder.encode("password")).thenReturn("encoded");

		User test = userService.updateUser(1, user);

		assertThat(test.getPassword()).isEqualTo("encoded");

	}

	@Test
	void updateUser_whenCalled_useEncoder() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));

		userService.updateUser(1, user);

		verify(mockEncoder, times(1)).encode("password");
	}

	@Test
	void deleteUserById_whenCalled_useRepository() {
		when(mockRepository.findById(1)).thenReturn(Optional.of(user));

		userService.deleteUserById(1);

		verify(mockRepository, times(1)).deleteById(1);
	}

	@Test
	void deleteUserById_whenUserNotFound_throw() {
		when(mockRepository.findById(1)).thenReturn(Optional.empty());

		assertThrows(NoSuchElementException.class, () -> userService.deleteUserById(1));
	}

	@Test
	void loadUserByUsername_whenUserIsNotFound_throw() {
		when(mockRepository.findByUsername("user")).thenReturn(Optional.empty());

		assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("user"));
	}

	@Test
	void loadUserByUsername_whenUserIsFound_returnUser() {
		when(mockRepository.findByUsername("user")).thenReturn(Optional.of(user));

		UserDetails test = userService.loadUserByUsername("user");

		assertThat(test.getUsername()).isEqualTo("username");
		assertThat(test.getPassword()).isEqualTo("password");
		assertThat(test.getAuthorities().toString()).isEqualTo("[role]");
	}

	@Test
	void loadUserByUsername_whenCalled_useRepository() {
		when(mockRepository.findByUsername("user")).thenReturn(Optional.of(user));

		userService.loadUserByUsername("user");

		verify(mockRepository, times(1)).findByUsername("user");
	}
}
