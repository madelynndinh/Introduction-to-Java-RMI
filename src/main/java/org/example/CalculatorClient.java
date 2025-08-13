package org.example;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class CalculatorClient {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Calculator calc = (Calculator) registry.lookup("Calculator");
// Test operations
            calc.pushValue(10);
            calc.pushValue(20);
            calc.pushValue(30);
            System.out.println("Pushed 10,20,30");
            calc.pushOperation("max");
            int res = calc.pop();
            System.out.println("After max: " + res); // should 30
            if (calc.isEmpty()) System.out.println("Stack empty");
            calc.pushValue(4);
            calc.pushValue(6);
            calc.pushOperation("gcd");
            res = calc.pop();
            System.out.println("GCD of 4,6: " + res); // 2
// More tests...
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}