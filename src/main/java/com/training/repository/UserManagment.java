package com.training.repository;

import java.util.List;
import java.util.Optional;

import com.training.model.User;

public interface UserManagment {
	public void adduser(User user);

	public Optional<User> getuserById(String id);

	public void deleteuserById(String id);

	public List<User> getAll();
}
