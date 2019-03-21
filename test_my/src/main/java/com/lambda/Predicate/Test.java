package com.lambda.Predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Test {
    public static void main(String args[]){
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        filter(languages, (str)->str.toString().startsWith("J"));

        filter(languages, (str)->str.toString().endsWith("a"));

        filter(languages, (str)->true);

        filter(languages, (str)->false);

        filter(languages, (str)->str.toString().length() > 4);
    }

    public static void filter(List<String> names, Predicate condition) {
        names.forEach(name->{
            if(condition.test(name)) {
                System.out.println(name + "");
            }
        });
        System.out.println( " \n\r");
    }
}
