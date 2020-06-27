package br.com.btgtest.jokenpo.resources.Enums;

public enum MoveEnum {

	ROCK("ROCK"), PAPER("PAPER"), SCISSOR("SCISSOR");
	
	private MoveEnum(String value) {
		this.value = value;
	}
	
	private String value;
	
	public String getValue() {
		return this.value;
	}
}
