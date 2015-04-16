//Calculator.java
package calcws;

import javax.jws.WebService;

@WebService
public class Calculator {
    
    public int add(int a, int b) {
    	System.out.println("request add(" + a + "," + b + ")");
    	int som = a+b;
    	System.out.println("return " + som);
        return som;
    }
}