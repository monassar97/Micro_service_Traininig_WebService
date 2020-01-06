package com.training.session2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.model.User;
import com.training.repository.UserManagment;

@RestController
@RequestMapping("/index")
public class GreetingController {
	private UserManagment userManagment;

	public GreetingController(UserManagment userManagment) {
		super();
		this.userManagment = userManagment;
	}

	@GetMapping("/hi")
	public String hello() {
		return "hello";
	}

	@GetMapping("/{userId}")
	public User greetingById(@PathVariable("userId") String id) {
		return userManagment.getuserById(id).orElseThrow(RuntimeException::new);
	}

	@GetMapping("/getAll")
	public List<User> getAllUsers() {
		return userManagment.getAll();
	}

	@RequestMapping(value = "/addUser", method = { RequestMethod.POST })
	public ResponseEntity<String> addUser(@RequestBody User user) {
		userManagment.adduser(user);
		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@RequestMapping(value = "/deletUser/{id}", method = { RequestMethod.DELETE })
	public void deleteUser(@PathVariable("id") String id) {
		userManagment.deleteuserById(id);
	}

	@RequestMapping(value = "/updateUser", method = { RequestMethod.PUT })
	public void updateUser(@RequestBody User user) {
		userManagment.adduser(user);
	}

}
