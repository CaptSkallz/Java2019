package doc.resources;

public class Zvrhlosti {
		public static void main(String[] args) {
		    int a = 0;
		    int b = (a++) + (a);
		    System.out.println(b);
		    int i = 0;
		    i = i++;
		    System.out.println(i);
		}
		/*
		#include <stdio.h>
		void main(int argc, char *argv) {
		    int a = 0;
		    int b = (a++) + (a);
		    printf("%d\n",b);
		    int i = 0;
		    i = i++;
		    printf("%d\n",i);
		}
	
		 */
}
