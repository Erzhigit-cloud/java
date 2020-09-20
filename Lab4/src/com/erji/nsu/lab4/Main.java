package com.erji.nsu.lab4;

import com.erji.nsu.lab4.factory.Factory;
import com.erji.nsu.lab4.view.StartFrame;

public class Main {
    public static void main(String[] args) {

        FactoryConfig parser = new FactoryConfig();

        Factory factory = new Factory(parser);

        new StartFrame(parser, factory);
    }
}
