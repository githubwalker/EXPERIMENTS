package com.alprojects.bean2;

public interface ICalculator {
	void setOperation( IOperation op );
	void setWriter( IWriter wr );
	
	public void performCalcs( int op1, int op2 );
}

