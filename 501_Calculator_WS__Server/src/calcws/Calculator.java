//Calculator.java
package calcws;

import javax.jws.WebService;
import javax.jws.WebParam;

@WebService
public class Calculator {
    
    public int add(@WebParam(name="a") int a, 
    		       @WebParam(name="b") int b) 
    {
    	System.out.println("request add(" + a + "," + b + ")");
    	int som = a+b;
    	System.out.println("return " + som);
        return som;
    }
}