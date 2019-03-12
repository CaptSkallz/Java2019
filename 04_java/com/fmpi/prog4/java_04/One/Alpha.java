package com.fmpi.prog4.java_04.One;

public class Alpha {
    private   	int iamprivate = 1;
               	int iampackage = 2;  
    protected	int iamprotected = 3;
    public		int iampublic = 4;

   private void privateMethod() {
   }
   void packageMethod() {
   }
   protected void protectedMethod() {
   }
   public void publicMethod() {
   }
   public static void main(String[] args) {
       Alpha a = new Alpha();

       a.privateMethod(); 
       a.packageMethod();   
       a.protectedMethod(); 
       a.publicMethod();    
       
       int r = 
	a.iamprivate+ 
	a.iampackage+
	a.iamprotected+ 
	a.iampublic;
   }
}
 