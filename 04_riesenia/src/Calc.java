// simple calculator (does not prioritize * over +)
public class Calc 
{
    double num, accu;    // current displayed number and accummulator
    char operator;       // last operator that was pressed
	
    // constructor
	public Calc()
	{
		reset();
	}
	
	// initializes calculator
	private void reset()
	{
		accu = 0;
		num = 0;
		operator = '+';
	}
	
	// performs computation
	private void compute()
	{
		switch (operator) 
		{
		case '+': num += accu; break;
		case '-': num = accu - num; break;
		case '*': num *= accu; break;
		case '/': num = accu / num; break;
		}
		accu = 0;
		operator = '+';
	}
	
	// returns current state of the display
	public String getDisplay()
	{
		return Double.toString(num);
	}
	
	// user interface with simple calculator
	public void pushKey(char key)
	{
	    if (Character.isDigit(key)) num = num * 10 + (key - '0');
	    else if (key == 'C') reset();
	    else if (key == '=') compute();
	    else if ((key == '+') || (key == '-') || (key == '*') || (key == '/'))
	    {
	    	compute();
	    	accu = num;
	    	num = 0;
	    	operator = key;
	   	}
	    else System.out.println("Calc warning: Unrecognized command " + key);
	}
	
	// testing program
	public static void main(String[] args) 
	{
       Calc c = new Calc();
       c.pushKey('1');
       c.pushKey('2');
       c.pushKey('+');
       c.pushKey('5');
       c.pushKey('+');
       c.pushKey('6');
       c.pushKey('7');
       c.pushKey('=');
       System.out.println(c.getDisplay()); 
	}
}
