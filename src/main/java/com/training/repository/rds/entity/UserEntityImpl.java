package com.training.repository.rds.entity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import com.training.model.User;
import com.training.repository.UserRepository;

@Component
@ConditionalOnProperty(prefix = "com.aspire.traiing.jpa", name = "enabled", matchIfMissing = false)
public class UserEntityImpl implements UserRepository {

	private final UserJpaRepo repo;

	public UserEntityImpl(UserJpaRepo repo) {
		this.repo = repo;
	}

	public UserEntity mapper(User user) {
		return new UserEntity(user.getId(), user.getName(), user.getAge());
	}

	public User mapper2(UserEntity entity) {
		return new User(entity.getId(), entity.getName(), entity.getAge());
	}

	public List<User> flatMapper() {
		return repo.findAll().stream().map(user -> new User(user.getId(), user.getName(), user.getAge()))
				.collect(Collectors.toList());
	}

	@Override
	public void adduser(User user) {
		repo.save(mapper(user));

	}

	@Override
	public Optional<User> getuserById(String id) {
		if (repo.existsById(id)) {
			return Optional.of(mapper2(repo.getOne(id)));
		}

		return Optional.empty();
	}

	@Override
	public void deleteuserById(String id) {
		repo.delete(repo.getOne(id));

	}

	@Override
	public List<User> getAll() {
		return flatMapper();
	}

	@Override
	public boolean updateUser(User user) {
		if (user == null || repo.existsById(user.getId())) {
			repo.save(mapper(user));
			return true;
		} else
			return false;
	}

}
