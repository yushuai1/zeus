package com.设计模式.解释器;

public abstract class SymbolNode implements Node
{
    protected Node left;
    protected Node right;

    public SymbolNode(Node left,Node right)
    {
        this.left=left;
        this.right=right;
    }
}