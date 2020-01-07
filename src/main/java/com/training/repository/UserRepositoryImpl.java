package com.training.repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.training.model.User;

@Component
public class UserRepositoryImpl implements UserRepository {

	Map<String, User> users = new HashMap<String, User>() {
		{
			put("1", new User("1", "ahmad", 20));
		}
	};

	@Override
	public void adduser(User user) {
		users.put(user.getId(), user);

	}

	@Override
	public Optional<User> getuserById(String id) {
		User user = users.get(id);
		return user == null ? Optional.empty() : Optional.of(user);
	}

	@Override
	public void deleteuserById(String id) {
		users.remove(id);
	}

	@Override
	public List<User> getAll() {
		List<User> allusers = new LinkedList<User>();
		for (String key : users.keySet()) {
			allusers.add(users.get(key));
		}
		return allusers;
	}

	@Override
	public boolean updateUser(User user) {
		if (!users.containsKey(user.getId())) {
			return false;
		} else {
			users.put(user.getId(), user);
			return true;
		}
	}
}
