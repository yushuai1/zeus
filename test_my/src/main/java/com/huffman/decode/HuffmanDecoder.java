package com.huffman.decode;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class HuffmanDecoder {
	
	public static String readFile = "F:/android/HuffmanCoding/src/1.huffman";
	public static void main(String [] args){
		String filename;
		if(args.length==0){
			System.out.println("Please input the file name to be decompressed");
			Scanner scanner = new Scanner(System.in);
			filename = scanner.next();
			scanner.close();
		}
		else{
			filename=args[0];
		}
		if(!filename.endsWith(".huffman")){
			System.out.println("Bad file type, please input the .huffman file!");
			return;
		}
		
		String toFilename = filename.replaceAll(".huffman", ".dehuffman");
		System.out.println("Decompress start!");
		long startTime = System.currentTimeMillis();
		for(int i=0;i<100;i++){
			System.out.print("=");
		}
		System.out.println("");
		try {
			deCompress(filename, toFilename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Decompressed failed, please check the file path!");
			return;
		}
		System.out.println("\r\nDecompressed finished!");
		long endTime = System.currentTimeMillis();
		long time = endTime-startTime;
		System.out.println("Decode time used: "+ 1.0*time/1000);
		return;
		
	}
/***
 * Description : read the file head and rebuild the huffman tree and call the Decode() to decode the file
 * @param filename the file name to be decompressed
 * @param toFilename the file name decompressed
 * @throws IOException 
 */
	public static void deCompress(String filename,String toFilename) throws IOException{
		ArrayList<HuffmanNode> tree = new ArrayList<HuffmanNode>();
		int charNum = -1;
		int nodeNum = -1;
		int count;
		File file = new File(filename);
		DataInputStream in = null;
		try{
			in = new DataInputStream(new FileInputStream(file));
			count = in.readInt();
			charNum = in.readShort();
			nodeNum = 2*charNum -1;
			//rebuild the huffman tree
			for(int i=0;i<charNum;i++){
				HuffmanNode node = new HuffmanNode((char)in.readByte());
				int parent = in.readShort();
				node.setParent(parent);
				tree.add(node);
			}
			
			for(int i=charNum;i<nodeNum;i++){
				HuffmanNode node = new HuffmanNode(' ');
				int l = in.readShort();
				int r = in.readShort();
				int p = in.readShort();
				node.setlChild(l);
				node.setrChild(r);
				node.setParent(p);
				tree.add(node);
			}
			System.out.print("=");
			Decode(tree, in, count, toFilename);
			
		} catch(IOException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
		} finally{
			try{
				in.close();
				
			} catch(Exception e){
				e.printStackTrace();
			}
		}
		return;
	}
/***
 * Description : to decode the data
 * @param tree the huffman tree
 * @param filename to be decompressed file name
 * @param in the input stream
 * @param n the character count
 * @param toFilename decompressed file name
 * @throws IOException 
 */
	
	public static void Decode(ArrayList<HuffmanNode> tree, DataInputStream in,int n, String toFilename) throws IOException{
		FileWriter writer =null;
		try {
			writer = new FileWriter(toFilename);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			throw e1;
		} 
		int num = 0;
		int count = 0;
		int dep = 0;
		StringBuilder buff = new StringBuilder(256);
		int temp;
		HuffmanNode node;
		String charBuff = "";
		try{

			while(true){
				while(buff.length()<32){
					temp = in.readInt();
					String codeString = Integer.toBinaryString(temp);
					while(codeString.length()<32){
						codeString='0'+codeString;
					}
					buff.append(codeString);
				}
				node = tree.get(tree.size()-1);
				dep = 0;
				while(!(node.getlChild()==-1&&node.getrChild()==-1)){
					if(dep>=buff.length()){
						System.out.println( "Buff overflow");
					}
					if(buff.charAt(dep)=='0'){
						node = tree.get(node.getlChild());
					}
					else if(buff.charAt(dep)=='1'){
						node = tree.get(node.getrChild());
					}
					else{
						System.out.println("Coding error");
					}
					dep++;
				}
				
				char c = node.getCH();
				num++;
				if(num>=n/99){
					System.out.print("=");
					num=0;
				}
				count++;
				if(count>=n){
					break;
				}
				charBuff+=c;
				if(charBuff.length()>256){
					writer.write(charBuff);
					charBuff="";
				}
				buff.delete(0, dep);
				
			}
			
		} catch(EOFException e){
			//just do nothing
		}
		catch(Exception e){
			e.printStackTrace();
		} finally{
			//there may be data released in the buff and charbuff, so we need to process them
			while(buff.length()>0){
				node = tree.get(tree.size()-1);
				dep = 0;
				while(!(node.getlChild()==-1&&node.getrChild()==-1)){
					if(dep>=buff.length()){
						break;
					}
					if(buff.charAt(dep)=='0'){
						node = tree.get(node.getlChild());
					}
					else if(buff.charAt(dep)=='1'){
						node = tree.get(node.getrChild());
					}
					else{
						System.out.println("Coding error");
						//return;
					}
					dep++;
				}
				char c = node.getCH();
				num++;
				if(num>=n/99){
					System.out.print("=");
					num=0;
				}
				count++;
				if(count>=n){
					break;
				}
				charBuff+=c;
				if(charBuff.length()>256){
					try {
						writer.write(charBuff);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					charBuff="";
				}
				buff.delete(0, dep);
			}
			
			try {
				writer.write(charBuff);
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
		try{
			writer.close();
		} catch(IOException e){
			throw e;
		}
		return;
	}
	
	
}


