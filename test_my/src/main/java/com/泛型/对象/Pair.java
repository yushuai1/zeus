package com.泛型.对象;

//这里的T即Type的首字母，代表类型的意思，
// 常用的还有E（element）、K（key）、V（value）等。当然不用这些字母指代类型参数也完全可以。
public class Pair<T, U,A> {
    private T first;
    private U second;
    private A nihao;


    public Pair(T first, U second, A nihao) {
        this.first = first;
        this.second = second;
        this.nihao = nihao;
    }

    public A getNihao() {
        return nihao;
    }

    public void setNihao(A nihao) {
        this.nihao = nihao;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public void setFirst(T newValue) {
        first = newValue;
    }

    public void setSecond(U newValue) {
        second = newValue;
    }
}
