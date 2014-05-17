
package com.github.indication.proportionaldivision.enumurations;

public enum CalcType {
	None(""),
	Plus("+"),
	Minus("-"),
	Devide("/"),
	Multiple("*"),
	;

	private String symbol;
	CalcType(String s){
		symbol = s;
	}
	public String getSymbol(){
		return symbol;
	}
}

