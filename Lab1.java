import java.io.*;
import java.lang.Integer;
import java.lang.String;
import java.util.Vector;


public class Lab1 {
  public static void main(String[] args) throws IOException {
    InputStreamReader isr = new InputStreamReader(System.in);
    BufferedReader reader = new BufferedReader(isr);
    System.out.print(">");
    String s;
    Chuli a = new Chuli();
    while( (s = reader.readLine()) != null ) {
      if (!a.judge(s)) {
        System.out.println("Input Error!");
      }
      else {
        if (s.charAt(0)!='!') {
          a = new Chuli();
          s = s.replaceAll(" ","");
          s = s.replaceAll("	","");
        }
        a.expression(s);
        a.simplify(s);
      }
      System.out.print("\n>");
    }
  }
}


class Xiang{
  public int xishu=1;
  public Vector<String> var;
  public Vector<Integer> mi;
  public Xiang(){
	xishu=1;
	var=new Vector<String>();
	mi=new Vector<Integer>();
  }
}		
class Fuzhi{
  public Vector<String> var;
  public Vector<Integer> zhi;
  public Fuzhi(){
	var=new Vector<String>();
	zhi=new Vector<Integer>();
  }
}
