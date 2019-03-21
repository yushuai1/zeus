package com.设计模式.解释器;

public class MulNode extends SymbolNode
{
    public MulNode(Node left,Node right)
    {
        super(left,right);
    }

    @Override
    public int interpret()
    {
        System.out.println("*********************");
        return left.interpret() * right.interpret();
    }
}