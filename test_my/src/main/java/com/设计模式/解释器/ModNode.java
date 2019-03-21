package com.设计模式.解释器;

public class ModNode extends SymbolNode{
    public ModNode(Node left,Node right){
        super(left,right);
    }

    @Override
    public int interpret(){
        System.out.println("%%%%%%%%%%%%%%%%");
        return super.left.interpret() % super.right.interpret();
    }
}