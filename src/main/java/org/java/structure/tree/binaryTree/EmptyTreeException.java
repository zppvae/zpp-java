package org.java.structure.tree.binaryTree;

import java.io.Serializable;

public class EmptyTreeException extends RuntimeException implements Serializable{


    private static final long serialVersionUID = -8766038608920134747L;


    public EmptyTreeException(){
        super();
    }

    public EmptyTreeException(String msg){
        super(msg);
    }
}