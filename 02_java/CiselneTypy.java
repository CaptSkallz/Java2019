public class CiselneTypy {
  public static void main(String[] args) {
     int i; byte b;
     b = -1;   // -1 = 255 
     i = (b < 0) ? b + 256 : b;
     System.out.println("i = " + i);
     i = 128;
     b = (byte) ((i > 127) ? i - 256 : i);
     System.out.println("b = " + b);
     i = 100;
     b = (byte) ((i > 127) ? i - 256 : i);
     System.out.println("b = " + b);
  }
}
