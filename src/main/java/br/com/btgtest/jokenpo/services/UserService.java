package br.com.btgtest.jokenpo.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.btgtest.jokenpo.resources.dto.UserDTO;
import br.com.btgtest.jokenpo.scopes.UserStorage;
import br.com.btgtest.jokenpo.util.Util;

@Service
public class UserService {
	
    @Resource(name = "storedUsers")
    private UserStorage storedUsers;
	
	public List<String> validate (UserDTO user){
		
		List<String> error= new ArrayList<>();
		
		isValid(user, error);
		contains(user, error);
		
		return error;	
	}

	private void contains(UserDTO user, List<String> error) {
		if(this.storedUsers.contains(user)) {
			error.add("User already exists.");
		}
	}
	
	public Boolean contains(UserDTO user) {
		return this.storedUsers.contains(user);
	}

	private void isValid(UserDTO user, List<String> error) {
		if(Util.isNullOrEmpty(user.getLogin()) || Util.isNullOrEmpty(user.getPassword())) {
			error.add("Login or Password are empty");
		}
	}
	
	public UserDTO register(UserDTO user) {
		this.storedUsers.add(user);
		return user;
	}

}
