package br.com.btgtest.jokenpo.scopes;

import java.util.ArrayList;
import java.util.List;

import br.com.btgtest.jokenpo.resources.dto.UserDTO;

public class UserStorage {
	
	private List<UserDTO> users = new ArrayList<>();
	
	public void add(UserDTO user) {
		if(!contains(user)) {
			this.users.add(user);
			
		}
	}
	
	public void remove(UserDTO user) {
		if(contains(user)) {
			this.users.remove(user);
		}
	}
	
	public Boolean contains(UserDTO user) {
		return this.users.stream().anyMatch(u->u.getLogin().equals(user.getLogin()));
	}
}
