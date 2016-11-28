package control;

import java.util.Vector;

import entity.*;

public class Simplify {
	private Polynomial polynomial_now;
	private String str;
	private Fuzhi fuzhi;	
	
	public Simplify(Polynomial polynomial,String s){
		polynomial_now=polynomial;
		str=s;
		fuzhi=null;	
	}
	
	private boolean judge(String str,Xiang[] dxshi){  
		Vector<String> var=new Vector<String>();
		int len=dxshi.length;
		for (int i=0;i<len;i++){
			int l=dxshi[i].var.size();
			for (int j=0;j<l;j++){
				if (var.indexOf(dxshi[i].var.get(j))==-1)
					var.add(dxshi[i].var.get(j));
			}
		}
		fuzhi=new Fuzhi();
	  	fuzhi.var=new Vector<String>();
		fuzhi.zhi=new Vector<Integer>();
		String sub=str.substring(10);
		String ss[]=sub.split(" ");   
		for (int i=0;i<ss.length;i++){
			int denghao=ss[i].indexOf("=");
			if (ss[i].length()==0)
				continue;
			if (denghao>=ss[i].length()-1 || denghao<=0){    					
				return false;
			}    					
			else{
				if (var.indexOf(ss[i].substring(0,denghao))==-1 ){
					return false;
				}
				else{
					fuzhi.var.addElement(ss[i].substring(0,denghao));
					fuzhi.zhi.addElement(Integer.parseInt(ss[i].substring(denghao+1)));
				}
			}
		} 
		return true;
	}
	
	public int handle(){
		Vector<Xiang> duoXiang=polynomial_now.getDuoXiang();
		int len=duoXiang.size();
		Xiang[] dxshi=new Xiang[len];
		for (int i=0;i<len;i++){
			dxshi[i]=duoXiang.get(i);
		}
		if (!judge(str,dxshi))
			return -1;
		else{
			simplify(dxshi);
			return 1;
		}	
	}
	
	private void simplify(Xiang[] dxshi){
		int len=dxshi.length;
		int flagfirst=0;
		int number=0;
		for (int i=0;i<len;i++){    	
			int xishu=dxshi[i].xishu;
			int size=dxshi[i].var.size();
			Boolean[] have=new Boolean[size];
			for (int j=0;j<size;j++){
				int index=fuzhi.var.indexOf(dxshi[i].var.elementAt(j));
				if (index==-1){
					have[j]=false;
				}
				else{
					have[j]=true;
					int shu=1;
					for (int q=0;q<dxshi[i].mi.elementAt(j);q++){
						shu*=fuzhi.zhi.elementAt(index);
					}
					xishu*=shu;
				}
			}	
			int flag=0;
			for (int w=0;w<size;w++){
				if (have[w]==false){
					flag+=1;
				}
			}
			if (xishu!=0 && flag!=0){
				if (flagfirst==0)
					flagfirst=1;    			
				else if(xishu<0)
					System.out.print("");
			else
				System.out.print("+");
			if (xishu!=1 && xishu!=-1){
				System.out.print(xishu);
				for (int w=0;w<size;w++){
					if (have[w]==false){	        				
						System.out.print("*"+dxshi[i].var.elementAt(w));
				if(dxshi[i].mi.elementAt(w)!=1)
					System.out.print("^"+dxshi[i].mi.elementAt(w));
					}
				}
			}
			else{
				int first=0;
				for (int w=0;w<size;w++){
					if (have[w]==false){
						if (xishu==-1)
							System.out.print("-");
				if (first==0){
					System.out.print(dxshi[i].var.elementAt(w));
					first=1;
					if(dxshi[i].mi.elementAt(w)!=1)
						System.out.print("^"+dxshi[i].mi.elementAt(w));
				}
				else{
					System.out.print("*"+dxshi[i].var.elementAt(w));
					if(dxshi[i].mi.elementAt(w)!=1)
						System.out.print("^"+dxshi[i].mi.elementAt(w));
								}	        				
			    			}
			    		}
					}
				}    
				else if (flag==0 && xishu!=0)
				{
					number+=xishu;
				}				
			}    	
			if (flagfirst==1 && number>0){
				System.out.print("+");
				System.out.print(number);
			}
			else if (flagfirst==1 && number<0){
				System.out.print("");
				System.out.print(number);
			}
			else if(flagfirst==0 ){
				System.out.print(number);
			}		
			System.out.println();
	}
}

class Fuzhi{
	public Vector<String> var;
	public Vector<Integer> zhi;
}