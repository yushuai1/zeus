package com.transport.test;

public class Test {

    public static void main(String[] args) {

        for (int m=0;m<1000;m++){

            if ((m&15)!=(m%16)){
                System.out.println("----------------------------------");
            }
        }

    }
}
