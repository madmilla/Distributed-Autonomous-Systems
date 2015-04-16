package client;
import calcws.*;

class CalculatorClient {
	
	public static void main(String args[])
	{
    	CalculatorService service = new CalculatorService();
        Calculator calculatorProxy = service.getCalculatorPort();

        int a = 10;
	    int b = 20;          
        int result = calculatorProxy.add(a,b);
        System.out.println(""+ a + " + " + b + " = " + result);

        a = 31;
	    b = 41;
        result = calculatorProxy.add(a,b);
        System.out.println(""+ a + " + " + b + " = " + result);
	}
}