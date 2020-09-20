package com.erji.nsu.lab1;

public class CommandLineParser {

    private int phraseLength = 1;
    private int phraseCount = 1;
    private String fileName;
    private boolean isConsole;

    public CommandLineParser(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-n":
                    phraseLength = Integer.parseInt(args[++i]);
                    break;
                case "-m":
                    phraseCount = Integer.parseInt(args[++i]);
                    break;
                case "-":
                    assert (!isConsole && fileName == null);
                    isConsole = true;
                    break;
                default:
                    assert (!isConsole && fileName == null);
                    fileName = args[i];
                    break;
            }
        }
    }

    public int getPhraseLength() {
        return phraseLength;
    }

    public int getPhraseCount() {
        return phraseCount;
    }

    public boolean isConsole() {
        return isConsole;
    }

    public String getFileName() {
        return fileName;
    }
}
