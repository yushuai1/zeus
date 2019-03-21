package com.huffman.decode;

public class HuffmanNode {
	private int parent;
	private int lChild;
	private int rChild;
	private int weight;
	private char CH;
	
	public HuffmanNode(int w) {
		parent = -1;
		lChild = -1;
		rChild = -1;
		weight = w;
	}
	
	public HuffmanNode(char c){
		parent = -1;
		lChild = -1;
		rChild = -1;
		CH =c ;
	}
	
	public void setWeight(int w){
		weight = w;
	}
	
	public void setParent(int n){
		parent = n;
	}
	
	public void setlChild(int l){
		lChild = l;
	}
	
	public void setrChild(int r){
		rChild = r;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public int getParent(){
		return parent;
	}
	
	public int getlChild(){
		return lChild;
	}
	
	public int getrChild(){
		return rChild;
	}
	
	public void setCH(char c){
		CH =c;
	}
	
	public char getCH(){
		return CH;
	}
}
