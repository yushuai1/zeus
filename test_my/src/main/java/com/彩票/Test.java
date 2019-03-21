package com.彩票;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test {


    public static void main(String[] as){

        List<Integer> stringList = new ArrayList(3);

        for (int m=0;m<10;m++){
            stringList.add(0,m);
        }

        for (Integer i:stringList) {
            System.out.println(i);
        }

    }
}
