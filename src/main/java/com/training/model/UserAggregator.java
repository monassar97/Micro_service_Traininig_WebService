package com.training.model;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.training.repository.UserRepositoryImpl;

@Component
public class UserAggregator {
	private final UserRepositoryImpl userRepository;

	public UserAggregator(UserRepositoryImpl userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public Optional<User> loadUser(String id) {
		return userRepository.getuserById(id);
	}

	public List<User> loadAllUser() {
		return userRepository.getAll();
	}

	public User registerUser(User user) {
		if (user.getAge() < 20) {
			throw new RuntimeException("User's age should be over 18");
		}
		userRepository.adduser(user);
		return user;
	}

	public boolean update(User user) {
		userRepository.updateUser(user);
		return true;
	}

}
