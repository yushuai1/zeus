package com.esClient.util;

public class Test {

    static int i = 10;


    public static void main(String[] args) {

        String tt = null;

        if (tt.equals(null)){
            System.out.println("asdasdas");
        }

        i =11;
        assert(i == 11) :"i不等于10";
        System.out.println(i);

    }
}
