package br.com.btgtest.jokenpo.scopes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.btgtest.jokenpo.resources.Enums.MoveEnum;
import br.com.btgtest.jokenpo.resources.dto.MoveDTO;
import br.com.btgtest.jokenpo.resources.dto.UserDTO;

public class MoveStorage {
	
	private List<MoveDTO> moves = new ArrayList<>();
	
	public void add(MoveDTO move) {
		if(!contains(move)) {
			this.moves.add(move);
			
		}
	}
	
	public void remove(MoveDTO move) {
		if(contains(move)) {
			this.moves.remove(move);
		}
	}
	
	public Boolean contains(MoveDTO move) {
		return this.moves.stream().anyMatch(m-> m.getMove().equals(move.getMove()) 
				&& m.getUser().getLogin().equals(move.getUser().getLogin()));
	}

	public boolean canPlay(UserDTO user) {		
		return !this.moves.stream().anyMatch(m-> m.getUser().getLogin().equals(user.getLogin()));
	}

	public boolean hasMoves(UserDTO user) {
		
		return this.moves.stream().anyMatch(m-> m.getUser().getLogin().equals(user.getLogin()));
	}

	public void removeByUser(UserDTO user) {
		List<MoveDTO> toRemove = this.moves.stream().filter(m-> m.getUser().getLogin().equals(user.getLogin())).collect(Collectors.toList());
		this.moves.removeAll(toRemove);
		
	}

	public int count() {
		return this.moves.size();
	}

	public boolean contains(MoveEnum target) {	
		return this.moves.stream().anyMatch(m->m.getMove().equals(target));
	}

	public List<String> getWinners(MoveEnum target) {
		
		return this.moves.stream().filter(m->m.getMove().equals(target))
				.map(m->m.getUser().getLogin())
				.collect(Collectors.toList());
	}

	public List<MoveDTO> getAll() {
		return this.moves;
	}
}
