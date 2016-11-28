package control;

import static org.junit.Assert.*;

import org.junit.Test;

public class DerivativeTest4{

	@Test
	public void testDerivative() {
		Expression e=new Expression("x+2yz*q+3");
		e.handle();		
		Derivative d=new Derivative(e.getPolynomial(), "!d/d x");
		String result=d.handle(2);
		assertEquals("1",result);
		e=new Expression("x+2yz*q+3");
		e.handle();		
		d=new Derivative(e.getPolynomial(), "!d/d q");
		result=d.handle(2);
		assertEquals("2*yz",result);
		e=new Expression("x+2yz*q+3");
		e.handle();		
		d=new Derivative(e.getPolynomial(), "!d/d yz");
		result=d.handle(2);
		assertEquals("2*q",result);
	}

}
