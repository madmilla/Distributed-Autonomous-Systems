//Calculator.java
package server;

import javax.xml.ws.Endpoint;
import calcws.Calculator;

public class CalculatorServer {
    
    public static void main(String[] args){
        // create and publish an endpoint
        Calculator calculator = new Calculator();
        Endpoint.publish("http://localhost:9191/calculator", calculator); 
        System.out.println("http://localhost:9191/calculator published");
    }
}