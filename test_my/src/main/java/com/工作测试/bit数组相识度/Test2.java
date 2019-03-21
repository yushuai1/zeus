package com.工作测试.bit数组相识度;

public class Test2 {
    private static byte[] gByte() {
        byte[] b = new byte[512];
        for (int i = 0; i < 512; i++) {
            b[i] = (byte) (Math.random() > 0.5 ? 1 : 0);
        }
        return b;
    }

    //Oxff  表示11111111
    public static void main(String[] args) {


       for (int m = 0;m<19;m++){
           for (int q=0;q<16;q++){
               String system1 = "count*** = count*** + (memoryOne[myBiOne[s+---] + 128] + memoryTwo[myBiOne[s+ ///] + 128]);";

               if (q==0){
                   system1 =  system1.replace("***",m+"").replace("+---","").
                           replace("///",(q+1)+"").
                           replace("memoryOne","memory"+(q+1)).replace("memoryTwo","memory"+(q+2));
               }else {
                   system1 = system1.replace("***",m+"").replace("---",q+"").
                           replace("///",(q+1)+"").
                           replace("memoryOne","memory"+(q+1)).replace("memoryTwo","memory"+(q+2));;
               }

               if ((q+1)%2==0){
                   continue;
               }
               System.out.println(system1);
           }
       }

    }
    static void swap(int x, int y) {
        x ^= y;
        y ^= x;
        x ^= y;
    }
}
