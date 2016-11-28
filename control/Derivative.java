package control;

import java.util.Vector;

import entity.Polynomial;
import entity.Xiang;

public class Derivative {
	private Polynomial polynomial_now;	
	private String str;
	public Derivative(Polynomial polynomial,String s){
		polynomial_now=polynomial;
		str=s;
	}
	
	public int handle(){
		Vector<Xiang> duoXiang=polynomial_now.getDuoXiang();
		int len=duoXiang.size();
		Xiang[] dxshi=new Xiang[len];
		for (int i=0;i<len;i++){
			dxshi[i]=duoXiang.get(i);
		}
		int c=judge(str,dxshi);
		if (c==-1)
			return -1;
		else if (c==1){
			String sub=str.substring(5);
			sub=sub.replaceAll(" ","");
    		sub=sub.replaceAll("	","");
			derivative(dxshi,sub);
			return 1;
		}
		else{
			System.out.println(0);
			return 1;
		}
	}
	
	public String handle(int x){
		if (x==2){
			Vector<Xiang> duoXiang=polynomial_now.getDuoXiang();
			int len=duoXiang.size();
			Xiang[] dxshi=new Xiang[len];
			for (int i=0;i<len;i++){
				dxshi[i]=duoXiang.get(i);
			}
			int c=judge(str,dxshi);
			if (c==-1)
				return null;
			else if (c==1){
				String sub=str.substring(5);
				sub=sub.replaceAll(" ","");
	    		sub=sub.replaceAll("	","");
	    		return derivative(dxshi,sub);
			}
			else{
				System.out.println(0);
				return "";
			}
		}
		return null;
	}
	
	private int judge(String str,Xiang[] dxshi){
		Vector<String> var=new Vector<String>();
		int len=dxshi.length;
		for (int i=0;i<len;i++){
			int l=dxshi[i].var.size();
			for (int j=0;j<l;j++){
				if (var.indexOf(dxshi[i].var.get(j))==-1)
					var.add(dxshi[i].var.get(j));
			}
		}
		if(str.startsWith("!d/d ")){
			String sub=str.substring(5);
			if (var.indexOf(sub)==-1 ){
				sub=sub.replaceAll(" ","");
    			sub=sub.replaceAll("	","");
    			if (sub.length()==0)
    				return -1;
    			if (fuhao(sub.charAt(0))!=1)
    				return -1;
    			if (sub.length()>=2)
        			for (int q=1;q<sub.length();q++)
        				if (fuhao(sub.charAt(q))!=0 && fuhao(sub.charAt(q))!=1)
        					return -1;
				return 0;
			}
			else{
				return 1;    				
			}
		}
		else 
			return -1;
	}
	
	public String derivative(Xiang[] dxshi,String str){
		String result="";
		int len=dxshi.length;
		int flagfirst=0;
    	int number=0;
    	for (int i=0;i<len;i++){
    		int size=dxshi[i].var.size();
    		if (dxshi[i].var.indexOf(str)==-1){
    		}
    		else{
    			int xishu=dxshi[i].xishu;
    			int index=dxshi[i].var.indexOf(str);
    			if (xishu!=0){
	    			int mi=dxshi[i].mi.elementAt(index);
	    			if (size==1 && mi==1){
	    				number+=xishu;
	    			}
	    			else{
						if (flagfirst==0)
	        				flagfirst=1;        			
	        			else
	        				result+=("+"); 
						int first=0;
						if (xishu*mi!=1 && xishu*mi!=-1)
						{
							result+=(xishu*mi);
							first=1;
						}
						else if (xishu*mi==-1)
							result+=("-");
		    			for (int w=0;w<size;w++){
		    				if (w!=index){
		        				if(first!=0){
		        					result+=("*"+dxshi[i].var.elementAt(w));
		        					if (dxshi[i].mi.elementAt(w)!=1)
		        						result+=("^"+dxshi[i].mi.elementAt(w));
		        				}
		        				else{
		        					first=1;
		        					result+=(dxshi[i].var.elementAt(w));
		        					if (dxshi[i].mi.elementAt(w)!=1)
		        						result+=("^"+dxshi[i].mi.elementAt(w));
		        				}
			        		}		    				
		    				else{
		    					if (mi-1>=1){
		    						if(first!=0){
			        					result+=("*"+dxshi[i].var.elementAt(w));
			        				}
			        				else{
			        					first=1;
			        					result+=(dxshi[i].var.elementAt(w));
			        				}
			    					if (mi-1!=1)
			    						result+=("^"+(mi-1));
		    					}
		    				}
		        		}
	        		}
    			}
    		}
    	}   
    	if (flagfirst==1 && number!=0){
    		result+=("+");
			result+=(number);
		}
		else if(flagfirst==0 ){
			result+=(number);
		}	
    	return result;
	}
	
	private int fuhao(char a){ 
		String s="+-*";
		if (a<='9' && a>='0') return 0;
		else if ((a<='z' && a>='a') || a=='_') return 1;
		else if (s.indexOf(a)!=-1) return 2;
		else if (a=='^') return 3;
		return -1;
	}
}
