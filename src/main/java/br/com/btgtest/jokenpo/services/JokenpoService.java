package br.com.btgtest.jokenpo.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.btgtest.jokenpo.resources.Enums.MoveEnum;
import br.com.btgtest.jokenpo.resources.dto.MoveDTO;
import br.com.btgtest.jokenpo.resources.dto.ResultDTO;
import br.com.btgtest.jokenpo.scopes.MoveStorage;
import br.com.btgtest.jokenpo.util.Util;

@Service
public class JokenpoService {
	
	@Autowired
	private UserService userService;
	
    @Resource(name = "storedMoves")
    private MoveStorage storedMoves;

	public List<String> validate(MoveDTO move) {
		List<String> errors = new ArrayList<>();
		
		isValid(move, errors);
		
		return errors;
	}

	private void isValid(MoveDTO move, List<String> errors) {
		
		if(Util.isMoveEnumValid(move.getMove())) {
			errors.add("Invalid move.");
		}
		
		if(!userService.contains(move.getUser())) {
			errors.add("User either absent or invalid");
		}
		
		if(!this.storedMoves.canPlay(move.getUser())) {
			errors.add("Player already made a move");
		}
		
	}

	public MoveDTO setMove(MoveDTO move) {
		this.storedMoves.add(move);
		return move;
	}

	public List<String> validateChange(MoveDTO move) {
		List<String> errors = new ArrayList<>();
		
		if(!this.storedMoves.hasMoves(move.getUser())) {
			errors.add("There are no moves for this player");
		}
		return errors;
	}

	public MoveDTO changeMove(MoveDTO move) {
		this.storedMoves.removeByUser(move.getUser());
		this.storedMoves.add(move);
		return move;
	}

	public void deleteMove(MoveDTO move) {
		this.storedMoves.removeByUser(move.getUser());
	}

	public boolean canPlay() {
		
		return this.storedMoves.count() > 0;
	}

	public ResultDTO play() {
		
		
		int i = 0;
		Boolean winner = Boolean.FALSE;
		int winnerIndex = 0;
		while(i < 3) {
			winner = getWinner(i, winner);
			winnerIndex = winner ? i : winnerIndex;
			
			if(winner) break;
			
			i++;
		}
		
		ResultDTO result = new ResultDTO();
		
		if(winner) {
			result.setWinners(getWinners(winnerIndex));
			
		}
		
		result.setResult(result.getWinners().size()!= 1 ? "Its a TIE" : "Someone Won");
		return result;
	}

	private Boolean getWinner(int i, Boolean winner) {
		switch(i) {
		case 0:
			winner = checkWinner(MoveEnum.ROCK, MoveEnum.PAPER);
			break;
		case 1:
			winner = checkWinner(MoveEnum.PAPER, MoveEnum.SCISSOR);
			break;
		case 2:
			winner = checkWinner(MoveEnum.SCISSOR, MoveEnum.ROCK);
			break;
		}
		return winner;
	}

	private List<String> getWinners(int i) {
		List<String> winners = new ArrayList<>();
		switch(i) {
		case 0:
			winners = this.storedMoves.getWinners(MoveEnum.ROCK);
			break;
		case 1:
			winners = this.storedMoves.getWinners(MoveEnum.PAPER);
			break;
		case 2:
			winners = this.storedMoves.getWinners(MoveEnum.SCISSOR);
			break;
		}
		return winners;
	}

	private Boolean checkWinner(MoveEnum chosed, MoveEnum weakPoint) {
		
		return this.storedMoves.contains(chosed) && !this.storedMoves.contains(weakPoint);
	}

	public List<MoveDTO> getAll() {
		
		return this.storedMoves.getAll();
	}

}
