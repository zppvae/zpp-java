package org.java.structure.stack;

import java.io.Serializable;

public class EmptyStackException extends RuntimeException implements Serializable {

	private static final long serialVersionUID = -8766038608920134747L;

	public EmptyStackException() {
		super();
	}

	public EmptyStackException(String msg) {
		super(msg);
	}
}