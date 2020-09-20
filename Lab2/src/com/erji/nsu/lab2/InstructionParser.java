package com.erji.nsu.lab2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class InstructionParser {

    public int parse(String buf, Stack<Integer> stack) {

        Calculator calculator = new Calculator();
        Scanner input = calculator.getInput();

        // проверка на число
        if (buf.matches("[-+]?\\d+")) {
            stack.push(Integer.parseInt(buf));
            return 1;
        } else {

            int a; //временные переменные
            int b;

            if (buf.equals("[")) {

                ArrayList<String> text = calculator.getConsoleText(); //Паредаем все инструкции

                LoopParser loopParser = new LoopParser(); //обработчик циклов
                int loopLevel = loopParser.getNestedLoopLevel(); //

                int[] bounds = loopParser
                        .getBounds(text, loopLevel, calculator.getIndex()); // границы цикла

                ArrayList<String> loopText = new ArrayList<String>
                        (text.subList(bounds[0], bounds[1])); //Инструкции цикла

                loopParser.setLoopInstructions(loopText); //Устанавливаем инструкции

                loopParser.parse(stack); //анализируем

                return bounds[1] - bounds[0]; //возвращаем смещение индекса
            }
            if (buf.equals("]")) {
                return 1;
            }
            switch (buf) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    return 1;
                case "-":
                    a = stack.pop();
                    stack.push(stack.pop() - a);
                    return 1;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    return 1;
                case "/":
                    a = stack.pop();
                    stack.push(stack.pop() / a);
                    return 1;
                case "sqrt":
                    stack.push((int) Math.sqrt(stack.pop()));
                    return 1;
                case ">":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a > b ? 1 : 0);
                    return 1;
                case "<":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a < b ? 1 : 0);
                    return 1;
                case "print":
                    System.out.println("Printed: " + stack.peek());
                    return 1;
                case "dup":
                    stack.push(stack.peek());
                    return 1;
                case "swap":
                    a = stack.pop();
                    b = stack.pop();
                    stack.push(a);
                    stack.push(b);
                    return 1;
                case "rot":
                    int size = stack.size();
                    int temp[] = new int[size];
                    for (int i = size - 2; i >= 0; i--) {
                        temp[i] = stack.pop();
                    }
                    temp[size - 1] = stack.pop();
                    for (int i = 0; i < size; i++) {
                        stack.push(temp[i]);
                    }
                    return 1;
                case "drop":
                    stack.pop();
                    return 1;

                default:
                    return 0;
            }//swich

        }//if else
    }//parse
}
