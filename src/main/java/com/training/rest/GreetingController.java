package com.training.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.User;
import com.training.model.UserAggregator;
import com.training.repository.UserRepository;

@RestController
@RequestMapping("/index")
public class GreetingController {
	private UserAggregator userAggregator;

	public GreetingController(UserAggregator userAggregator) {
		super();
		this.userAggregator = userAggregator;
	}

	@GetMapping("/hi")
	public String hello() {
		return "hello";
	}

	@GetMapping("/{userId}")
	public User greetingById(@PathVariable("userId") String id) {
		return userAggregator.loadUser(id).orElseThrow(RuntimeException::new);
	}

	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		return userAggregator.loadAllUser();
	}

	@RequestMapping(value = "/addUser", method = { RequestMethod.POST })
	public User addUser(@Valid @RequestBody User user) {
		userAggregator.registerUser(user);
		return user;
	}

	@RequestMapping(value = "/deletUser/{id}", method = { RequestMethod.DELETE })
	public User deleteUser(@PathVariable("id") String id) {
		User user = userAggregator.loadUser(id).get();
		userAggregator.loadUser(id);
		return user;
	}

	@RequestMapping(value = "/updateUser", method = { RequestMethod.PUT })
	public boolean updateUser(@RequestBody User user) {
		if(userAggregator.update(user))
			return true;
		return false;
	}

}
