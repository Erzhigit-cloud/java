import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class LoopParser {
    static private int nestedLoopLevel = 0;//уровеь вложенности цикла
    private ArrayList<String> loopInstructions = new ArrayList<String>();
    private InstructionParser instructionParser = new InstructionParser();

    public void setLoopInstructions(ArrayList<String> inst){
        this.loopInstructions = inst;
    }

    public int getNestedLoopLevel(){
        return this.nestedLoopLevel;
    }

    public int[] getBounds(ArrayList<String> loopText, int loopLevel, int index){
        int depth = 0;
        int[] bounds = {0,0};
        int size = loopText.size();
        for(index = index; index < size ; index++) {
            if (loopText.get(index).equals("[") && depth == loopLevel) {
                depth++;
                if (bounds[0] == 0)
                    bounds[0] = index + 1;
            } else if(loopText.get(index).equals("[")) depth++;

            if (loopText.get(index).equals("]") && depth == loopLevel + 1){
                if (bounds[1] == 0)
                    bounds[1] = index + 1;
                depth--;
                break;
            }  else if(loopText.get(index).equals("]")) depth--;

        }
        return bounds;
    }

    public void parse(Stack<Integer> stack){
        nestedLoopLevel++;
        Calculator calculator = new Calculator();
        Scanner input = calculator.getInput();
        String buf;
        System.out.println();
        int temp = stack.peek();
        for (int iter = 0; stack.peek() != 0; iter++) {
            stack.pop();
            for (int i = 0; i < loopInstructions.size(); ) {
                i += instructionParser.parse(loopInstructions.get(i), stack);
            }
        }
        stack.pop();
        nestedLoopLevel--;
    }

}
