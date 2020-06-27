package br.com.btgtest.jokenpo.resources.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ResultDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String result;
	private List<String> winners = new ArrayList<>();
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<String> getWinners() {
		return winners;
	}
	public void setWinners(List<String> winners) {
		this.winners = winners;
	}
	
	
	
	
}
