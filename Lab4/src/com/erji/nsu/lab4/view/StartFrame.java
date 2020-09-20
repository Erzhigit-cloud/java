package com.erji.nsu.lab4.view;

import com.erji.nsu.lab4.FactoryConfig;
import com.erji.nsu.lab4.factory.Factory;

import javax.swing.*;
import java.awt.*;

public class StartFrame extends JFrame {

    public StartFrame(FactoryConfig config, Factory f) {
        super("Factory Preferences");

        setLayout(new BorderLayout());

        add(new PreferencesPanel(f), BorderLayout.NORTH);

        JButton start = new JButton("OK");

        start.addActionListener(
                e -> {
                    new Thread(new MainFrame(config, f)).start();
                    f.start();
                    this.dispose();
                }
        );

        add(start, BorderLayout.SOUTH);

        setVisible(true);
        setResizable(false);
        setSize(new Dimension(740, 370));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
