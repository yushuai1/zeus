package com.huffman.code;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Hashtable;

public class HuffmanCoder {
	public static int MAX_VALUE = 2147483647;  
	//public static String readFile = "F:/android/HuffmanCoder/src/1.txt";
	//public static String writeFile = "F:/android/HuffmanCoder/src/1.huffman";
	/***
	 * 
	 * @param args the filepath to be compressed
	 */
	public static void main(String [] args){
		String compress = "";
		String compressed = "";
		if(args.length==0){
			System.out.println("Please input the filepath to be compressed!");
			Scanner scanner = new Scanner(System.in);
			compress = scanner.next();
			scanner.close();
			compressed=compress.replaceAll("\\..*$", ".huffman");
		}
		if(args.length>=1){
			compress = args[0];
			compressed=compress.replaceAll("\\..*$", ".huffman");
		}
		ArrayList<CharacterCode> list = new ArrayList<CharacterCode>();
		// get the weight list of the file. It's stored in the list
		
		System.out.println("Compress start");
		long startTime = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			System.out.print("=");
		}
		System.out.println("");
		int count=0;
		try {
			count = getWeight(compress,list);
			System.out.print("=");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Failed to open the file, please check the path!");
			return;
		}
		//build the huffman tree
		ArrayList<HuffmanNode>  tree = getHuffmanCode(list);
		System.out.print("=");
		if(tree==null){
			System.out.print("Comress failed!");
			return;
		}
		//build the coding hash table
		Hashtable<Character,String> codeTable = new Hashtable<Character, String>();
		for(int i=0;i<list.size();i++){
			codeTable.put(list.get(i).getChar(), list.get(i).getCode());
		}
		//write the file head
		System.out.print("=");

		try {
			writeHead(list, tree,compressed, count);
			System.out.print("=");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println("Failed to open the file, please check the path!");
			return;
		}
		//write the data compressed of the file data.
		try {
			writeFile(codeTable,count,compress, compressed);
		} catch (IOException e) {
			System.out.println("Failed to open the file, please check the path!");
			return;
		}
		System.out.println("\r\nCompress finished!");
		long endTime = System.currentTimeMillis();
		long time = endTime - startTime;
		System.out.println("Encoding time: "+1.0*time/1000);
		return;
		
	}
/***
 * Description: write the compressed data to the file
 * @author kris
 * @param codeTable code hash table
 * @param count the file character count
 * @param compress the filepath to be compressed
 * @param compressed the filepath compressed
 * @throws IOException 
 */
	
	public static void writeFile(Hashtable<Character, String> codeTable, int count, String compress, String compressed) throws IOException{
		
		File readFile = new File(compress);
		File writeFile = new File(compressed);
		Reader reader = null;
		FileOutputStream out = null;
		StringBuilder outputStringBuffer = new StringBuilder(255);
		int c=0; //  the character counter
		try{
			int temp=-1;
			reader = new InputStreamReader(new FileInputStream(readFile));
			out = new FileOutputStream(writeFile, true);
			while((temp=reader.read())!=-1){ //!= EOF	
				// get the code from the code table
				String code = codeTable.get((char)temp);
				c++;
				if(c>=count/96){
					System.out.print("=");
					c=0;
				}
				try{
					StringBuilder codeString = new StringBuilder(code);
					outputStringBuffer.append(codeString);
					while(outputStringBuffer.length()>8){
						out.write(Short.parseShort(outputStringBuffer.substring(0, 8),2));
						outputStringBuffer.delete(0, 8);
					}
				} catch(Exception e){
					e.printStackTrace();
				}

			}
			// the character read finish, but there may be data released in the buff, so we 
			//need to clear the buff
			while(outputStringBuffer.length()>0){

				String end = outputStringBuffer.substring(0, outputStringBuffer.length());
				while(end.length()<8){
					end=end+"0";
				}
				out.write(Short.parseShort(end,2));
				c++;
				if(c>=count/96){
					System.out.print("=");
					c=0;
				}
				outputStringBuffer.delete(0, outputStringBuffer.length());

			}
			
			//when we decode, we use read readInt, so we need to add more than 3 bytes to ensure
			//all the data will be read in decoding.
			for(int i=0;i<3;i++){
				String string="11111111";
				out.write(Short.parseShort(string,2));
			}
			
		} catch(IOException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				reader.close();
				out.close();
			} catch (IOException e) {
				throw e;
			}

		}
	}
/***
 * Description: get the Huffman code for every character in the file
 * @param list the weight list
 * @return the Huffman tree list
 */
	
	public static ArrayList<HuffmanNode> getHuffmanCode(ArrayList<CharacterCode> list){
		ArrayList<HuffmanNode> tree = new ArrayList<HuffmanNode>();
		
		//initialize the tree;
		for(int i=0;i<list.size();i++){
			HuffmanNode node = new HuffmanNode(list.get(i).getWeight());
			tree.add(node);
		}
		
		for(int i=0;i<list.size()-1;i++){
			//w1 : the first min weight w2: the second min weight 
			//i1 : the first min weight index, i2:��the second min weight index
			int w1 = MAX_VALUE, w2=MAX_VALUE; 
			int i1 = 0, i2 = 0;
			// find the two node with the minimum weight
			for(int j=0;j<tree.size();j++){
				HuffmanNode node = tree.get(j);
				if(node.getWeight()< w1 && node.getParent()==-1){
					w2 = w1;
					w1 = node.getWeight();
					i2 = i1;
					i1 = j;
				}
				else if(node.getWeight()<w2 && node.getParent()==-1){
					w2 = node.getWeight();
					i2 = j;
				}
			}
			//set the two node to be the children of a new node, and add the new node to the tree
			HuffmanNode pNode = new HuffmanNode(w1+w2);
			pNode.setlChild(i1);
			pNode.setrChild(i2);
			tree.add(pNode);
			tree.get(i1).setParent(tree.indexOf(pNode));
			tree.get(i2).setParent(tree.indexOf(pNode));
		}
		
		//get the code according to the tree
		for(int i=0;i<list.size();i++){
			HuffmanNode node = tree.get(i);
			HuffmanNode pNode = tree.get(node.getParent());
			String code ="";
			while(true){
				if(pNode.getlChild()==tree.indexOf(node)){
					code = "0"+code;
				}
				else if(pNode.getrChild() == tree.indexOf(node)){
					code = "1"+code;
				}
				else {
					System.out.println("Tree Node Error!!!");
					return null;
				}
				node=pNode;
				if(node.getParent()!=-1)
					pNode=tree.get(node.getParent());
				else 
					break;
			}
			list.get(i).setCode(new String(code));
		}
		return tree;
	}

/***
 * 
 * @param filename the file path to be compressed.
 * @param list the list to store the weight
 * @return the count of the character
 * @throws IOException 
 */
	public static int getWeight(String filename,ArrayList<CharacterCode> list) throws IOException{
		int count = 0;
		File file = new File(filename);
		Reader reader = null;
		try{
			reader = new InputStreamReader(new FileInputStream(file));
			int temp;
			while((temp = reader.read())!=-1){
				char charTemp = (char) temp;
				int i;
				for(i = 0;i<list.size();i++){
					if(list.get(i).getChar()==charTemp)
						break;
				}
				if(i>=list.size()){  //indicate that there are no the character
					list.add(new CharacterCode(1, charTemp));
				}
				else{  //there is already the character in the list;  
					list.get(i).setWeight(list.get(i).getWeight()+1);
				}
				
				count++;
			}
		} catch(IOException e){
			throw e;
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			if(reader != null){
				try{
					reader.close();
				} catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		return count;
	}
	
/***
 * Description : to write the file head containing the huffman coding table
 * @param list the code list
 * @param tree the huffman tree
 * @param file the file to be compressed
 * @param count the file character count
 * @throws IOException 
 */
	public static void writeHead(ArrayList<CharacterCode> list, ArrayList<HuffmanNode> tree, String file,int count) throws IOException{
		DataOutputStream out = null;
		BufferedOutputStream clearBuff = null;
		//to clear the old file
		try{
			clearBuff=new BufferedOutputStream( new FileOutputStream(file));
			String temp ="";
			clearBuff.write(temp.getBytes());
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				clearBuff.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		try{
			out = new DataOutputStream(new FileOutputStream(new File(file)));
			out.writeInt(count+1); //write the character count
			out.writeShort(list.size()); //write the size of the coding table
			for(int i=0;i<list.size();i++){ // write the leaf node
				out.writeByte(list.get(i).getChar());
				out.writeShort(tree.get(i).getParent());
				
			}
			for(int i=list.size();i<tree.size();i++){  //write the none leaf node
				out.writeShort(tree.get(i).getlChild());
				out.writeShort(tree.get(i).getrChild());
				out.writeShort(tree.get(i).getParent());
			}

		} catch(IOException e){
			throw e;
		} catch(Exception e){
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw e;
			}
		}
		return;
	}
}
