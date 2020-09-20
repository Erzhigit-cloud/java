package com.erji.nsu.lab4.view;

import com.erji.nsu.lab4.FactoryConfig;
import com.erji.nsu.lab4.factory.Factory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrame extends JFrame implements Runnable {
    private final StatusPanel statusPanel;
    private final WorkersPanel workersPanel;
    private final PreferencesPanel preferencesPanel;

    private final Factory factory;
    private final FactoryConfig factoryConfig;

    public MainFrame(FactoryConfig factoryConfig, Factory factory) {
        super("Factory");

        JTabbedPane tabbedPanel = new JTabbedPane();

        this.factory = factory;
        this.factoryConfig = factoryConfig;

        statusPanel = new StatusPanel(factoryConfig);
        workersPanel = new WorkersPanel(factoryConfig.getWorkersCount());
        preferencesPanel = new PreferencesPanel(factory);

        tabbedPanel.addTab("Storage", statusPanel);
        tabbedPanel.setMnemonicAt(0, KeyEvent.VK_1);
        tabbedPanel.addTab("Workers", new JScrollPane(workersPanel));
        tabbedPanel.setMnemonicAt(1, KeyEvent.VK_2);
        tabbedPanel.addTab("Preferences", preferencesPanel);
        tabbedPanel.setMnemonicAt(2, KeyEvent.VK_3);

        add(tabbedPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                factory.stop();
                System.exit(0);
            }
        });

        setVisible(true);
        setMinimumSize(new Dimension(740, 370));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            statusPanel.getAccessoriesStorageBar().setValue(factory.getAccessoriesStorage().currentCountElements());
            statusPanel.getEnginesStorageBar().setValue(factory.getEnginesStorage().currentCountElements());
            statusPanel.getBodiesStorageBar().setValue(factory.getBodiesStorage().currentCountElements());
            statusPanel.getCarsStorageBar().setValue(factory.getCarsStorage().currentCountElements());

            statusPanel.getAccessoriesStorageCount().setText(factory.getAccessoriesStorage().currentCountElements() + "/" + factory.getAccessoriesStorage().getCapacity());
            statusPanel.getEnginesStorageCount().setText(factory.getEnginesStorage().currentCountElements() + "/" + factory.getEnginesStorage().getCapacity());
            statusPanel.getBodiesStorageCount().setText(factory.getBodiesStorage().currentCountElements() + "/" + factory.getBodiesStorage().getCapacity());
            statusPanel.getCarsStorageCount().setText(factory.getCarsStorage().currentCountElements() + "/" + factory.getCarsStorage().getCapacity());

            int count = 0;
            for (var status : factory.getCarBuilder().getStatuses()) {
                switch (status) {
                    case WAITING:
                        workersPanel.getStatuses().get(count).setText("Waiting for details");
                        break;
                    case SLEEPING:
                        workersPanel.getStatuses().get(count).setText("Sleeping");
                        break;
                    case WORKING:
                        workersPanel.getStatuses().get(count).setText("Assembling car");
                        break;
                }
                ++count;
            }
        }
    }
}
