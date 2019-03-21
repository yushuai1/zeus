package com.分布式.nfs;

import java.io.File;

public class Test {

    public static void main(String[] asd){
        String path = "http://10.2.1.35/1/default_readme.txt";
        File file = new File(path);
        long length = file.length();
        System.out.println(length);


        int a;
        a= 10;

        System.out.println(a);

        String mima = "C3:E4,C5:C10,D9:E10,E11:E15" +
                ",C14:D15,G3:I4,I5:I10,G9:H10,G11:G15," +
                " H14:I15,K3:K15,L14:L15,M3:M15,L3:L";

    }
}
