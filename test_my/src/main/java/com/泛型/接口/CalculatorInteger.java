package com.泛型.接口;

public class CalculatorInteger implements Calculator<Integer>{
    @Override
    public Integer and(Integer a, Integer b){
        return a + b;
    }

    public static void main(String[] args) {
        CalculatorInteger ci = new CalculatorInteger();
        Integer val = ci.and(10, 20);
        System.out.println(val);
    }
}