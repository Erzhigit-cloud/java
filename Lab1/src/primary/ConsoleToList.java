package primary;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleToList {
    public List<String> transform(){
        Scanner scan = new Scanner(System.in);
        String buf;

        System.out.println("Enter your text");
        String longLine = scan.nextLine();

        while(!(buf = scan.nextLine()).equals("")) {
            longLine += " " + buf;
        }
        return Arrays.asList(longLine.split("\\s+"));

    }
}
