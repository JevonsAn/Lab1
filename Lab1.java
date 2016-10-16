import java.io.*;
import java.lang.String;
import java.util.Vector;
import java.lang.Integer;
//我的注释
public class Lab1 {
    public static void main(String[] args) throws IOException{
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        System.out.print(">");
        String s;
        Chuli a=new Chuli();
        while( (s = reader.readLine()) != null )  
        {      	
        	if (!a.judge(s)){
        		System.out.println("Input Error!");
        	}
        	else {        
        		if (s.charAt(0)!='!')
        		{	a=new Chuli();
        			s=s.replaceAll(" ","");
        			s=s.replaceAll("	","");}
        		a.expression(s);
        		a.simplify(s);        		
        	}
        	System.out.print("\n>");
        }
    }
}
class Chuli{
	Xiang dxshi[];
	Fuzhi fuzhi;
	Vector<String> var;
	int len;
    public Chuli(){
    	var=new Vector<String>(4);
    	fuzhi=new Fuzhi();
    }
    public boolean judge(String str){
    	if (str.charAt(0)=='!'){
    		if (str.startsWith("!simplify ")){
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
    						
    					}
    					else{
    						fuzhi.var.addElement(ss[i].substring(0,denghao));
    						fuzhi.zhi.addElement(Integer.parseInt(ss[i].substring(denghao+1)));
    					}
    				}
    			}    			 
    		}
    		else if(str.startsWith("!d/d ")){
    			String sub=str.substring(5);
    			if (var.indexOf(sub)==-1 ){
    				sub=sub.replaceAll(" ","");
        			sub=sub.replaceAll("	","");
        			if (sub.length()==0)
        				return false;
        			if (fuhao(sub.charAt(0))!=1)
        				return false;
        			if (sub.length()>=2)
	        			for (int q=1;q<sub.length();q++)
	        				if (fuhao(sub.charAt(q))!=0 && fuhao(sub.charAt(q))!=1)
	        					return false;
					System.out.println(0);
				}
    			else{
    				derivative(sub);    				
    			}
    		}
    	}
    	else {
    		str=str.replaceAll("	", "");
        	str=str.replace(" ","");
        	Vector<Integer> indexfang=new Vector<Integer>();
	    	int flag=3;
	    	for (int i=0;i<str.length();i++){
	    		if (flag==1){
	    			if (fuhao(str.charAt(i))==-1)	return false;
	    			else if (fuhao(str.charAt(i))==0) 	flag=1;
	    			else if(fuhao(str.charAt(i))==2)	flag=3;
	    			else if(fuhao(str.charAt(i))==1)	flag=2;
	    			else if(fuhao(str.charAt(i))==3)	flag=4;
	    		}
	    		else if(flag==2){
	    			if (fuhao(str.charAt(i))==-1)	return false;
	    			else if(fuhao(str.charAt(i))==2)	flag=3;
	    			else if (fuhao(str.charAt(i))==0) 	flag=1;
	    			else if(fuhao(str.charAt(i))==1)	flag=2;
	    			else if(fuhao(str.charAt(i))==3)	flag=4;
	    			else return false;
	    		}
	    		else if (flag==3){
	    			if (fuhao(str.charAt(i))==-1)	return false;
	    			else 
	    			if (fuhao(str.charAt(i))==0) 	flag=1;
	    			else if(fuhao(str.charAt(i))==1)	flag=2;
	    			else return false;
	    		}
	    		else if (flag==4){
	    			if (fuhao(str.charAt(i))==-1)	return false;
	    			else {
		    			indexfang.addElement(i-1);
		    			if (fuhao(str.charAt(i))==0) 	flag=1;
		    			else return false;
	    			}
	    		}	    		
	    	}
	    	if (flag==3 || flag==4){
	    		return false;
	    	}
    		for (int j=0;j<indexfang.size();j++){
    			int w=indexfang.elementAt(j)+1;
    			while(fuhao(str.charAt(w))==0){
    				w++;
    				if (w>=str.length()){
    					break;
    				}
    			}
    			if (w>=str.length()){
					break;
				}
    			if (fuhao(str.charAt(w))!=2 && fuhao(str.charAt(w))!=3)
    				return false;
    		}
    	}   	
    	return true;
    }
    public int strtoint(String str){
    	int weishu=str.length();
		int x=0;
		for (int q=0;q<weishu;q++){
			x*=10;
			x+=str.charAt(q)-48;
		}
    	return  x; 
    }
    public void expression(String str){
    	if (str.charAt(0)=='!'){

    	}
    	else if (str.indexOf("0^0")!=-1)
    		System.out.println("Input Error!");
    	else {    		
    		String[] strArray=str.split("\\+|-");
    		len=strArray.length;
    		dxshi=new Xiang[len];
    		for(int i=0;i<len;i++){
    			dxshi[i]=new Xiang();
    		} 
    		int indexfu[]=new int[len];
    		int indexnum=1;
    		for(int i=0;i<str.length();i++){
    			if (str.charAt(i)=='+'){
    				indexfu[indexnum]=0;
    				indexnum++;
    			}
    			else if(str.charAt(i)=='-'){
    				indexfu[indexnum]=1;
    				dxshi[indexnum].xishu*=-1;
    				indexnum++;
    			}
    		}
    		String array[][] = new String[len][];    		
    		for(int i=0;i<len;i++){
    			if (fuhao(strArray[i].charAt(0))==0){
	    			int indexfu2=0;
	        		while(fuhao(strArray[i].charAt(indexfu2))==0){
	        			indexfu2++;
	    				if (indexfu2>=strArray[i].length()){
	    					break;
	    				}
	    			} 		
	        		if (indexfu2>=strArray[i].length()){
	        			indexfu2=-1;
	    			}
	        		if (indexfu2!=-1 && fuhao(strArray[i].charAt(indexfu2))==1)
	        		{
	    	    		//Ìí³ËºÅ
	    	    		String newstr=new String();
	    	    		newstr=strArray[i].substring(0, indexfu2);
	    	    		newstr+="*";
	    	    		newstr+=strArray[i].substring(indexfu2, strArray[i].length());	    		
	    	    		strArray[i]=newstr;
	        		}
    			}
    			array[i]=strArray[i].split("\\*");
    		}
    		   		
    		for (int i=0;i<len;i++){
    			//System.out.println("len:"+array[i].length);
    			for (int j=0;j<array[i].length;j++){
    				//System.out.println("len:"+array[i][j].length());
    				if (array[i][j].indexOf('^')==-1 && array[i][j].length()!=0){
    					//System.out.println("no^");
	    				if (fuhao(array[i][j].charAt(0))==1){
	    					int index2=var.indexOf(array[i][j]);
	    					if (index2==-1) {
	    						var.addElement(array[i][j]);
	    					}
	    					int index=dxshi[i].var.indexOf(array[i][j]);
	    					if (index==-1) {
	    						dxshi[i].var.addElement(array[i][j]);
	    						dxshi[i].mi.addElement(1);
	    					}
	    					else{
	    						int yuan=dxshi[i].mi.elementAt(index);
	    						dxshi[i].mi.setElementAt(yuan+1,index);
	    					}
	    						
	    				}
	    				else if(fuhao(array[i][j].charAt(0))==0 && array[i][j].length()!=0){
	    					int weishu=array[i][j].length();
	    					int x=0;
	    					for (int q=0;q<weishu;q++){
	    						x*=10;
	    						x+=array[i][j].charAt(q)-48;
	    					}
	    					dxshi[i].xishu*=x;
	    				}
    				}
    				else{
    					String zan[]=array[i][j].split("\\^");
    					//System.out.println("yes^"+"len:"+zan.length);
    					if (fuhao(zan[0].charAt(0))==0){
    						int ci=1;
	    					ci=mi(Integer.parseInt(zan[zan.length-2]),Integer.parseInt(zan[zan.length-1]));
	    					for (int pp=zan.length-3;pp>=0;pp--){
	    						ci=mi(Integer.parseInt(zan[pp]),ci);
	    					}
	    					dxshi[i].xishu*=ci;
    					}
    					else if(fuhao(zan[0].charAt(0))==1){
    						int index2=var.indexOf(zan[0]);
	    					if (index2==-1) {
	    						var.addElement(zan[0]);
	    					}
	    					int index=dxshi[i].var.indexOf(zan[0]);
	    					//ÃÝÔËËã
	    					int ci=1;
	    					if (zan.length==2)
	    						ci=Integer.parseInt(zan[1]);
	    					else if(zan.length>=3){
	    						ci=mi(Integer.parseInt(zan[zan.length-2]),Integer.parseInt(zan[zan.length-1]));
		    					for (int pp=zan.length-3;pp>=1;pp--){
		    						ci=mi(Integer.parseInt(zan[pp]),ci);
		    					}
	    					}
	    					if (index==-1) {
	    						dxshi[i].var.addElement(zan[0]);
	    						dxshi[i].mi.addElement(ci);  
	    					}
	    					else{
	    						int yuan=dxshi[i].mi.elementAt(index);
	    						dxshi[i].mi.setElementAt(yuan+ci,index);
	    					}
    					}
    				}
    			}    			
    		}
    	merge();
    	}    	
    }
    public int mi(int a,int b){
    	int result=1;
    	for (int i=0;i<b;i++){
    		result*=a;
    	}
    	return result;
    }
    public int fuhao(char a){ 
    	String s="+-*";
    	if (a<='9' && a>='0') return 0;
    	else if ((a<='z' && a>='a') || a=='_') return 1;
    	else if (s.indexOf(a)!=-1) return 2;
    	else if (a=='^') return 3;
		return -1;
    }
    public void simplify(String str){
    if (str.indexOf("0^0")!=-1){}
    else if (!str.startsWith("!d/d ")){
    	//System.out.print(str+"simp");
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
    public void derivative(String str){
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
	        				System.out.print("+"); 
						int first=0;
						if (xishu*mi!=1 && xishu*mi!=-1)
						{
							System.out.print(xishu*mi);
							first=1;
						}
						else if (xishu*mi==-1)
							System.out.print("-");
		    			for (int w=0;w<size;w++){
		    				if (w!=index){
		        				if(first!=0){
		        					System.out.print("*"+dxshi[i].var.elementAt(w));
		        					if (dxshi[i].mi.elementAt(w)!=1)
		        						System.out.print("^"+dxshi[i].mi.elementAt(w));
		        				}
		        				else{
		        					first=1;
		        					System.out.print(dxshi[i].var.elementAt(w));
		        					if (dxshi[i].mi.elementAt(w)!=1)
		        						System.out.print("^"+dxshi[i].mi.elementAt(w));
		        				}
			        		}		    				
		    				else{
		    					if (mi-1>=1){
		    						if(first!=0){
			        					System.out.print("*"+dxshi[i].var.elementAt(w));
			        				}
			        				else{
			        					first=1;
			        					System.out.print(dxshi[i].var.elementAt(w));
			        				}
			    					if (mi-1!=1)
			    						System.out.print("^"+(mi-1));
		    					}
		    				}
		        		}
	        		}
    			}
    		}
    	}   
    	if (flagfirst==1 && number!=0){
    		System.out.print("+");
			System.out.print(number);
		}
		else if(flagfirst==0 ){
			System.out.print(number);
		}	
    System.out.println();
    }  
    public void merge(){
    	if (dxshi.length>1){
    		//System.out.println(dxshi.length);
	    	for(int i=0;i<dxshi.length-1;i++){
	    		for(int j=i+1;j<dxshi.length;j++){
	    			//System.out.println(i+"  "+j);
	    			if(equal(i,j)){
	    				//System.out.println("yes");
	    				dxshi[j].xishu+=dxshi[i].xishu;
	    				dxshi[i].xishu=0;
	    			}
	    		}
	    	}
    	}
    }
    public boolean equal(int a,int b){
    	int sizea=dxshi[a].var.size();
    	int sizeb=dxshi[b].var.size();
    	if (sizea!=sizeb)
    		return false;
    	else{
    		int juzhen[][]=new int[2][sizea];
    		for (int i=0;i<2;i++){
    			for (int j=0;j<sizea;j++){
    				juzhen[i][j]=0;
    			}
    		}
    		for (int i=0;i<sizea;i++){
    			for (int j=0;j<sizeb;j++){
    				//System.out.println(i+"  "+j);
    				if(juzhen[0][i]==0 && juzhen[1][j]==0){
    					if (dxshi[a].var.elementAt(i).equals(dxshi[b].var.elementAt(j))){
    						if(dxshi[a].mi.elementAt(i).equals(dxshi[b].mi.elementAt(j))){
		    					juzhen[0][i]=1;
		    					juzhen[1][j]=1; 
		    				}
    					}
    				}
    			}
    		}
    		for (int j=0;j<sizea;j++){
    			if (juzhen[0][j]==0 || juzhen[1][j]==0)
    				return false;
			}
    	}
		return true;   	
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
