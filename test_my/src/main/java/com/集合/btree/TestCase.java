package com.集合.btree;

import org.junit.Before;

import org.junit.Test;

public class TestCase {

    static B_Tree<String, String> b_tree = null;

    @Before
    public void newInstance() {

        b_tree = new B_Tree<String, String>(3);    //degree为3

    }

    /**
     * 测试插入方法Case1
     */

    @Test

    public void test1() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.frontIterator(b_tree.root);

        System.out.println(b_tree.number);

    }

    /**
     * 测试插入方法Case2
     */

    @Test

    public void test2() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.insert("J", "j");

        b_tree.insert("K", "k");

        b_tree.insert("M", "m");

        b_tree.insert("N", "n");

        b_tree.insert("O", "o");

        b_tree.frontIterator(b_tree.root);

        System.out.println(b_tree.number);

    }

    /**
     * 测试搜索方法Case1
     * <p>
     * 查找B tree中存在的结点
     */

    @Test

    public void test3() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.insert("J", "j");

        b_tree.insert("K", "k");

        b_tree.insert("M", "m");

        b_tree.insert("N", "n");

        b_tree.insert("O", "o");

        b_tree.frontIterator(b_tree.root);

        System.out.println(b_tree.number);

        System.out.println(b_tree.search(b_tree.root, "M"));

    }

    /**
     * 测试搜索方法Case2
     * <p>
     * 查找B tree中不存在的结点
     */

    @Test

    public void test4() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.frontIterator(b_tree.root);

        System.out.println(b_tree.number);

        System.out.println(b_tree.search(b_tree.root, "F"));

    }

    /**
     * 测试删除方法Case1
     */

    @Test

    public void test5() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.frontIterator(b_tree.root);

        b_tree.remove("A");

        System.out.println("--------------RemoveResult--------------");

        b_tree.frontIterator(b_tree.root);

    }

    /**
     * 测试删除方法Case2
     */

    @Test

    public void test6() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.insert("J", "j");


        b_tree.frontIterator(b_tree.root);

        b_tree.remove("D");

        System.out.println("--------------RemoveResult--------------");

        b_tree.frontIterator(b_tree.root);

    }

    /**
     * 测试删除方法Case3
     */

    @Test

    public void test7() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.insert("J", "j");

        b_tree.frontIterator(b_tree.root);

        b_tree.remove("C");

        System.out.println("--------------RemoveResult--------------");

        b_tree.frontIterator(b_tree.root);

    }

    /**
     * 测试删除方法Case4
     */

    @Test

    public void test8() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.insert("J", "j");

        b_tree.insert("K", "k");

        b_tree.insert("M", "m");

        b_tree.insert("N", "n");

        b_tree.insert("H", "h");

        b_tree.insert("B", "b");

        b_tree.frontIterator(b_tree.root);

        b_tree.remove("J");

        System.out.println("--------------RemoveResult--------------");

        b_tree.frontIterator(b_tree.root);

    }

    /**
     * 测试删除方法Case5
     */

    @Test

    public void test9() {

        b_tree.insert("A", "a");

        b_tree.insert("C", "c");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("G", "g");

        b_tree.insert("J", "j");

        b_tree.insert("K", "k");

        b_tree.insert("M", "m");

        b_tree.insert("N", "n");

        b_tree.frontIterator(b_tree.root);

        b_tree.remove("J");

        System.out.println("--------------RemoveResult--------------");

        b_tree.frontIterator(b_tree.root);

    }

    /**
     * 测试删除方法Case6
     */

    @Test

    public void test10() {

        b_tree.insert("C", "c");

        b_tree.insert("G", "g");

        b_tree.insert("P", "p");

        b_tree.insert("T", "t");

        b_tree.insert("X", "x");

        b_tree.insert("M", "m");


        b_tree.insert("A", "a");

        b_tree.insert("B", "b");

        b_tree.insert("D", "d");

        b_tree.insert("E", "e");

        b_tree.insert("J", "j");

        b_tree.insert("K", "k");


        b_tree.insert("L", "l");

        b_tree.insert("N", "n");

        b_tree.insert("O", "o");

        b_tree.insert("Q", "q");

        b_tree.insert("R", "r");

        b_tree.insert("S", "s");


        b_tree.insert("U", "u");

        b_tree.insert("V", "v");

        b_tree.insert("Y", "y");

        b_tree.insert("Z", "z");


        b_tree.frontIterator(b_tree.root);

        b_tree.remove("D");

        System.out.println("--------------RemoveResult--------------");

        b_tree.frontIterator(b_tree.root);

    }

}
