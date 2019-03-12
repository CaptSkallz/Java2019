package com.fmpi.prog4.java_04.Two;
import com.fmpi.prog4.java_04.One.Alpha;

public class DeltaTwo {
    public static void main(String[] args) {

        Alpha alpha = new Alpha();

        //alpha.privateMethod(); 
	 //alpha.packageMethod(); 
	 //alpha.protectedMethod(); 
        alpha.publicMethod();      

	int r = 	//   alpha.iamprivate +
		//   alpha.iampackage +
		//   alpha.iamprotected +
		alpha.iampublic;
    }
}

