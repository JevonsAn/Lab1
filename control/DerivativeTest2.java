package control;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivativeTest2{

//	public DerivativeTest2(Polynomial polynomial, String s) {
//		super(polynomial, s);
//		// TODO Auto-generated constructor stub
//	}

	@Test
	public void testDerivative() {
		Expression e=new Expression("y-x*y");
		e.handle();		
		Derivative d=new Derivative(e.getPolynomial(), "!d/d x");
		String result=d.handle(2);
		assertEquals("-y",result);
		e=new Expression("y-x*y");
		e.handle();		
		d=new Derivative(e.getPolynomial(), "!d/d y");
		result=d.handle(2);
		assertEquals("-x+1",result);
	}

}
