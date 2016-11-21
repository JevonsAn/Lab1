import static org.junit.Assert.*;

import org.junit.Test;
import java.lang.String;

public class TestCase1 {

	@Test
	public void testDerivative() {
		Chuli chuli=new Chuli();
		chuli.expression("x+2x^3+3");
		String result=chuli.derivative("x");
		assertEquals("6*x^2+1",result);
	}

}
