package br.com.btgtest.jokenpo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.btgtest.jokenpo.resources.dto.UserDTO;
import br.com.btgtest.jokenpo.services.UserService;

@RestController
@RequestMapping(value = "users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@SuppressWarnings("rawtypes")
	@PostMapping
	public ResponseEntity register(@RequestBody UserDTO user) {
		
		List<String> errors = service.validate(user);
		
		if(!errors.isEmpty()) {
			return ResponseEntity.badRequest().body(errors);
		}
		
		UserDTO registered = service.register(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(registered);
		
	}
	
	
}
