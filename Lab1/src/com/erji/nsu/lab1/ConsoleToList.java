package com.erji.nsu.lab1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleToList {
    public List<String> transform() {
        Scanner scan = new Scanner(System.in);

        String buf;

        StringBuilder builder = new StringBuilder();

        System.out.println("Enter your text");

        String firstLine = scan.nextLine();

        builder.append(firstLine);

        while(!(buf = scan.nextLine()).equals("")) {
            builder.append(" ").append(buf);
        }

        return Arrays.asList(builder.toString().split("\\s+"));

    }
}
