package br.com.btgtest.jokenpo.util;

import java.util.EnumSet;

import br.com.btgtest.jokenpo.resources.Enums.MoveEnum;

public class Util {
	
	public static Boolean isNullOrEmpty (String value) {
		return (null == value || value.trim().isEmpty()); 
	}

	public static boolean isMoveEnumValid(MoveEnum move) {
		if(move!= null) {
			return EnumSet.allOf(MoveEnum.class).stream().map(MoveEnum::getValue).anyMatch(s->s.equals(move));
		}
		return false;
	}

}
