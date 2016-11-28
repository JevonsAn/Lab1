package control;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivativeTest3{

	@Test
	public void testDerivative() {
		Expression e=new Expression("x+x*3+x^5");
		e.handle();		
		Derivative d=new Derivative(e.getPolynomial(), "!d/d x");
		String result=d.handle(2);
		assertEquals("5*x^4+4",result);
	}

}
