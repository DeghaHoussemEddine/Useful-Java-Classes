

import java.io.Serializable;

public class Liste implements Serializable{
private Object e;
private Liste rest;

public Liste(){
	this.e = null; 
	this.rest = null;
}
public boolean ListeVide(){ return this.e == null ;}
public int length(){
	if(this.ListeVide()) return 0; 
	return 1+this.rest.length();
}
public Object getE(int pos){
	if( (pos<1) || pos>this.length() || this.ListeVide() ) return null ;
	if(pos==1){ return this.e;}
	return this.rest.getE(pos -1) ;
}
public Liste inasert(Object x , int pos){
	if(x==null) return this;
	if( (pos<1) || pos>this.length()+1) return this;
	// if(this.ListeVide()&& pos == 1) this.e = x;is.
	 if( pos ==1){
		 Liste l =new Liste();
		 l.e = this.e;
		 l.rest = this.rest ;
		 this.e = x;
		 this.rest = l ;
	 }else this.rest.inasert(x, pos-1);
	
	return this;
}
public Liste delate(int pos ){
	if( (pos<1) || pos>this.length()) return this;
	 if( pos == 1){
		 this.e = this.rest.e;
		 this.rest = this.rest.rest;
	 }else this.rest.delate(pos-1);
	return this;
};
public void aff(){
	if(this.ListeVide()) return;
	System.out.print(this.e+"	");
	this.rest.aff();

}
public static void h(Liste l){
	if(l.ListeVide())return ;
	boolean k = true;
	for(int i = 1 ;i <= l.length(); i++){
		if((Integer)l.getE(i)!=0){
		System.out.print(" * ");
		l.inasert((Integer)l.getE(i)-1, i);
		l.delate(i+1);
		k = false ;
		}else{
			System.out.print("   ");
		}
	}
	System.out.println();
	if(k==false){h(l);}
}
public static void insert_list(Liste l1, Liste l2,int pos){
	if(pos>0 && pos<=l1.length()+1)
	for(int i =1; i<= l2.length(); i++){
		l1.inasert(l2.getE(i), pos+i-1);
	}
	return ;
}

}
