package com.erji.nsu.lab1;

public class Main {

    public static void main(String[] args) {
        try {
            PhrasesCounter phrasesCounter = new PhrasesCounter();
            phrasesCounter.start(args);

            // System.out.println(phrasesCounter.getPhraseCount());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
