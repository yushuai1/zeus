package com.设计模式.模板;

import java.util.List;

import static com.设计模式.模板.UtilTool.setAge;

public abstract class AbsTest {

      public List<String> choutu(String m){
          int n = setAge(1);
          return null;
      }

      public String getResult(String m){

          while (true){
              if (m==null){
                  return "cc";
              }
          }
      }

      public int getReult(){
          String s =getResult(null);
          return add(1)+del(2);
      }

      public abstract int add(int m);

      public abstract int del(int m);
}
