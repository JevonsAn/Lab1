package boundary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import control.*;
import entity.*;

public class Main {
	private static Polynomial polynomial_now;
	private static InputStreamReader isr = new InputStreamReader(System.in);
    private static BufferedReader reader = new BufferedReader(isr);
    
	public static void main(String[] args) throws IOException{        
        String input;
        int cls=0;
        Derivative dervative=null;
        Expression expression=null;
        Simplify simplify=null;
        while (true){
        	input=acceptInput();
        	cls=judge(input);
        	if (cls==1){
        		expression=new Expression(input);
        		int rs=expression.handle();
        		if (rs==1){
        			polynomial_now=expression.getPolynomial(); 
        		}
        		showResult(rs);
        	}
        	else if (cls==2){
        		simplify=new Simplify(polynomial_now,input);
        		int rs=simplify.handle();
        		showResult(rs);
        	}
        	else if (cls==3){
        		dervative=new Derivative(polynomial_now,input);
        		int rs=dervative.handle();
        		showResult(rs);
        	}
        	else{
        		showResult(-1);
        	}        		
        }        	
    }
	
	public static String acceptInput() throws IOException{		
        System.out.print(">");
        String input="";
        if ((input = reader.readLine()) != null ){
        	return input;
        }
        else 
        	return null;		
	}
	
	public static void showResult(int cls){
		if (cls==1)
			System.out.print("\n>");
		else if (cls==-1){
			System.out.println("Input Error!");
			System.out.print("\n>");
		}
	}
	
	public static int judge(String input){
		if (!input.startsWith("!"))
			return 1;
		else if (input.startsWith("!simplify "))
			return 2;
		else if (input.startsWith("!d/d "))
			return 3;
		else
			return 0;
	}
	

}
