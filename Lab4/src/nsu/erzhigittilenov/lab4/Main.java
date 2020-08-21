package nsu.antonsokovnin.lab4;

import nsu.antonsokovnin.lab4.factory.Factory;
import nsu.antonsokovnin.lab4.view.StartFrame;

public class Main {
    public static void main(String[] args) {
        FactoryConfig parser = new FactoryConfig();
        Factory factory = new Factory(parser);
        new StartFrame(parser, factory);
    }
}
