package com.泛型.方法;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializeConfig;

import javax.swing.plaf.PanelUI;

public class ArrayAlg {

    static{
        ParserConfig.getGlobalInstance().setAsmEnable(false);
        SerializeConfig.getGlobalInstance().setAsmEnable(false);
    }

    public static <M> M getUser(M sd){
        return sd;
    }

    public static <T> T getMiddle(T[] a) {
        return a[a.length / 2];
    }

    /**
     * 传入对象返回字符串
     *
     * @param a
     * @return
     */
    public static String obTest(Object a) {
        String m = JSON.toJSONString(a);
        return  m;
    }

    public static <D> D stringToObject(String m, Class<D> objectType) {

        D d = JSON.parseObject(m, objectType);
        return d;
    }

    public static void main(String[] asd) {
//        String[] names = {"aa", "bb", "cc"};
//        String middle = ArrayAlg.getMiddle(names);
//        System.out.println(middle);

        Cuser cuser = new Cuser(4, "fangfa");
        String s = JSON.toJSONString(cuser);
        System.out.println(s);

        Cuser cuser1 = stringToObject(s,Cuser.class);

        System.out.println(cuser1);
    }
}