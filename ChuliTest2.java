import static org.junit.Assert.*;

import org.junit.Test;

public class ChuliTest2 {

	@Test
	public void testDerivative() {
		Chuli chuli=new Chuli();
		chuli.expression("y-x*y");
		String result=chuli.derivative("x");
		assertEquals("-y",result);
		chuli=new Chuli();
		chuli.expression("y-x*y");
		result=chuli.derivative("y");
		assertEquals("-x+1",result);
	}

}
