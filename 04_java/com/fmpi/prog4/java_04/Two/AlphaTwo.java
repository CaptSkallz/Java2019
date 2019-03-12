package com.fmpi.prog4.java_04.Two;
import com.fmpi.prog4.java_04.One.*;

public class AlphaTwo extends Alpha {
    public static void main(String[] args) {
        Alpha a = new Alpha();
        //a.privateMethod();   
        //a.packageMethod();  
        //a.protectedMethod();
        a.publicMethod();

        int r = //  a.iamprivate +
                  //  a.iampackage +
                   // a.iamprotected +
                 a.iampublic;

        AlphaTwo a2 = new AlphaTwo();
        a2.protectedMethod();  
        System.out.println(a2.iamprotected); 
   }
}

