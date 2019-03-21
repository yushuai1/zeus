package com.杂乱无章;

public class GetChar {

    public static void main(String[] args){

        String hua = "你是傻逼吗？";
        char[] chars = hua.toCharArray();
        for (int i=0;i<chars.length;i++){
            System.out.print((int)chars[i]+" ");
        }

        System.out.println((char)20320);
        for(int i = 0;i<1;i++){
            char a = (char) i;
            System.out.println(i+"-------"+a);
        }
    }
}
