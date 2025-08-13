package org.example;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
public class CalculatorServer {
    public static void main(String[] args) {
        try {
            CalculatorImplementation calc = new CalculatorImplementation();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Calculator", calc);
            System.out.println("Calculator server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}