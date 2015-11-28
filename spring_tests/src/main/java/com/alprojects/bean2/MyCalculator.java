package com.alprojects.bean2;

public class MyCalculator implements ICalculator {
	IOperation op;
	IWriter wr;

	public void setOperation(IOperation op) {
		// TODO Auto-generated method stub
		this.op = op; 
	}
	
	public void setWriter( IWriter wr ) 
	{
		this.wr = wr;
	}	

	public void performCalcs( int op1, int op2 ) 
	{
		StringBuilder sb = new StringBuilder();
		sb.append("Result of ");
		sb.append(op1);
		sb.append(op.getName());
		sb.append(op2);
		sb.append(" is ");
		sb.append( op.performOperation(op1, op2) );
		
		wr.print(sb.toString());
	}
}

