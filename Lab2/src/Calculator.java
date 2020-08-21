import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.*;

public class Calculator {
   private static ArrayList<String> consoleText = new ArrayList<String>();
   private static int index = 0;
   private ArrayList<ArrayList<String>> functionList = new ArrayList<ArrayList<String>>(); //для define
   private Stack<Integer> stack = new Stack<>();
   private static Scanner input = new Scanner(System.in);

   public ArrayList<String> getConsoleText(){
        return consoleText;
    }
   public Scanner getInput(){
       return input;
   }
   public int getIndex() { return index;}

    public void start() {
        String buf;
        int functionNumber = 0;
        InstructionParser instructionParser = new InstructionParser();
        System.out.println("Input text (type <end> to close): ");
         do {
            buf = input.nextLine();     //считали строку
            consoleText.addAll(consoleText.size(), Arrays.asList(buf.split("\\s")));
        }while (!buf.equals(""));

        System.out.println("Input complete");
        index = 0;
        while (index < consoleText.size() ){ //анализируем каждую инстр
            buf = consoleText.get(index);
            int indShift = 0;
            if ((indShift = instructionParser.parse(buf, stack)) >= 1) {//если это инструкция, выполняем
                index += indShift;                                      //и смещаем на длину инструкции
            } else                                   // иначе
                switch (buf) {
                    case "#":
                        index += Arrays.asList(input.nextLine().split("\\s"))
                                .size();
                        break;
                    case "end":
                        System.out.println("Closing...");
                        return;
                    case "define":////////////////////////////////
                        int funcStart = consoleText.indexOf("define") + 1;
                        int funcEnd = consoleText.indexOf(";");
                        ArrayList<String> subArrayList = new ArrayList<String>
                                (consoleText.subList(funcStart, funcEnd));
                        functionList.add(functionNumber, subArrayList);
                        System.out.print("Added function: <" + subArrayList.get(0) + "> = (");
                        for (int i = 1; i < subArrayList.size(); i++)
                            System.out.print(subArrayList.get(i) + " ");
                        System.out.println(")");
                        functionNumber++;
                        index = funcEnd + 1;
                        break;

                    default://///////////////////////////
                        //если встретили функцю, исполняем ее инструкции
                        boolean found = false;
                        for (int i = 0; i < functionList.size(); i++) {
                            if (functionList.get(i).get(0).equals(buf)) {//функц найдена
                                found = true;
                                consoleText.remove(index);
                                consoleText.addAll(index,functionList.get(i));
                                index++;
                            }
                        }//for
                        if (found == false) {
                            System.out.println("Unparseable input: '" + buf + "', skipped");
                            index++;
                            break;
                        }
                        break;
                    //default
                }//swich
        }//while ()
    }//void start
}//class
