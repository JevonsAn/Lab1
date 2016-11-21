import static org.junit.Assert.*;

import org.junit.Test;

public class ChuliTest3 {

	@Test
	public void testDerivative() {
		Chuli chuli=new Chuli();
		chuli.expression("x+x*3+x^5");
		String result=chuli.derivative("x");
		assertEquals("5*x^4+4",result);
	}

}
