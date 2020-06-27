package br.com.btgtest.jokenpo.resources.dto;

import java.io.Serializable;

import br.com.btgtest.jokenpo.resources.Enums.MoveEnum;

public class MoveDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private UserDTO user;
	private MoveEnum move;
	
	public UserDTO getUser() {
		return user;
	}
	
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
	public MoveEnum getMove() {
		return move;
	}
	
	public void setMove(MoveEnum move) {
		this.move = move;
	}
}
