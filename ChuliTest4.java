import static org.junit.Assert.*;

import org.junit.Test;

public class ChuliTest4 {

	@Test
	public void test() {
		Chuli chuli=new Chuli();
		chuli.expression("x+2yz*q+3");
		String result=chuli.derivative("x");
		assertEquals("1",result);
		chuli=new Chuli();
		chuli.expression("x+2yz*q+3");
		result=chuli.derivative("q");
		assertEquals("2*yz",result);
		chuli=new Chuli();
		chuli.expression("x+2yz*q+3");
		result=chuli.derivative("yz");
		assertEquals("2*q",result);
	}

}
