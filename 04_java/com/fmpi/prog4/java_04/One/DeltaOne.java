package com.fmpi.prog4.java_04.One;

public class DeltaOne {

    public static void main (String[] args) {
        Alpha a = new Alpha();

        //a.privateMethod(); 
        a.packageMethod();    
        a.protectedMethod();  
        a.publicMethod();     

        int r = 
        	//  a.iamprivate +   
		a.iampackage +
		a.iamprotected +
		a.iampublic;    
    }
}
