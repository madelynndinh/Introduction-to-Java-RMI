package org.example;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class CalculatorImplementation extends UnicastRemoteObject implements Calculator {
    private final Stack<Integer> stack;

    protected CalculatorImplementation() throws RemoteException {
        super();
        this.stack = new Stack<>();
    }

    @Override
    public synchronized void pushValue(int val) throws RemoteException {
        stack.push(val);
    }

    // The method
    @Override
    public synchronized void pushOperation(String operator) throws RemoteException {
        List<Integer> values = new ArrayList<>();
        while (!stack.isEmpty()) {
            values.add(stack.pop());
        }
        if (values.isEmpty()) {
            return; // assume not happens
        }
        int result;
        switch (operator) {
            case "min":
                result = Collections.min(values);
                break;
            case "max":
                result = Collections.max(values);
                break;
            case "gcd":
                result = values.get(0);
                for (int i = 1; i < values.size(); i++) {
                    result = gcd(result, values.get(i));
                }
                break;
            case "lcm":
                result = values.get(0);
                for (int i = 1; i < values.size(); i++) {
                    result = lcm(result, values.get(i));
                }
                break;
            default:
                throw new RemoteException("Invalid operator: " + operator);
        }
        stack.push(result);
    }

    @Override
    public synchronized int pop() throws RemoteException {
        return stack.pop();
    }

    @Override
    public synchronized boolean isEmpty() throws RemoteException {
        return stack.isEmpty();
    }

    @Override
    public synchronized int delayPop(int millis) throws RemoteException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RemoteException("Interrupted during delay", e);
        }
        return pop();
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private int lcm(int a, int b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a / gcd(a, b)) * b;
    }
}