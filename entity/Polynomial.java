package entity;

import java.util.Vector;

public class Polynomial {
	private Vector<Xiang> duoXiang;
	
	public Polynomial(){
		duoXiang=new Vector<Xiang>();
	}
	
	public Polynomial(Xiang[] dxshi){
		duoXiang=new Vector<Xiang>();
		int len=dxshi.length;
		for (int i=0;i<len;i++){
			if (dxshi[i].xishu!=0)
				duoXiang.add(dxshi[i]);
		}
	}
	
	public Vector<Xiang> getDuoXiang() {
		return duoXiang;
	}

	public void setDuoXiang(Vector<Xiang> duoXiang) {
		this.duoXiang = duoXiang;
	}
	
}

