package com.huffman.code;

public class CharacterCode {
	private int weight;
	private char character;
	private String code;
	
	public CharacterCode(int w, char c){
		weight = w;
		character = c;
		code ="";
	}
	
	public void setWeight(int w){
		weight = w;
		return;
	}
	
	public void setChar(char c){
		character = c;
		return;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public char getChar(){
		return character;
	}
	
	public void setCode(String c){
		code = c;
	}
	
	public String getCode(){
		return code;
	}
}
