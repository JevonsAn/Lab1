package control;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivativeTest {

	@Test
	public void testDerivative() {
		Expression e=new Expression("x+2x^3+3");
		e.handle();		
		Derivative d=new Derivative(e.getPolynomial(), "!d/d x");
		String result=d.handle(2);
		assertEquals("6*x^2+1",result);
	}

}
