package com.泛型.对象;

public class Test {

   public static void main(String[] arg){

       Tuser tuser = new Tuser(1,"yu");
       Uuser uuser = new Uuser(2,"shuai");
       Auser auser = new Auser(3,"hao");
       Pair<Tuser,Uuser,Auser> jj = new Pair<>(tuser,uuser,auser);

       System.out.println(jj);
   }

}
