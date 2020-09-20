package com.erji.nsu.lab4.view;

import com.erji.nsu.lab4.factory.Factory;

import javax.swing.*;
import java.util.Hashtable;

public class PreferencesPanel extends JPanel {
    private final JSlider accessoriesSupplierSpeed;
    private final JSlider bodiesSupplierSpeed;
    private final JSlider enginesSupplierSpeed;
    private final JSlider workersSpeed;
    private final JSlider dealersSpeed;

    private final JLabel accessoriesLabel;
    private final JLabel bodiesLabel;
    private final JLabel enginesLabel;
    private final JLabel dealersLabel;
    private final JLabel workersLabel;

    private final GroupLayout layout;

    public PreferencesPanel(Factory f) {

        accessoriesSupplierSpeed = new JSlider(JSlider.HORIZONTAL, 500, 120000, f.getAccessoriesSuppliers().get(0).getPeriod());
        accessoriesSupplierSpeed.setPaintLabels(true);
        accessoriesSupplierSpeed.setMinorTickSpacing(500);
        accessoriesSupplierSpeed.setSnapToTicks(true);

        bodiesSupplierSpeed = new JSlider(JSlider.HORIZONTAL, 500, 120000, f.getBodiesSupplier().getPeriod());
        bodiesSupplierSpeed.setPaintLabels(true);
        bodiesSupplierSpeed.setMinorTickSpacing(500);
        bodiesSupplierSpeed.setSnapToTicks(true);

        enginesSupplierSpeed = new JSlider(JSlider.HORIZONTAL, 500, 120000, f.getEnginesSupplier().getPeriod());
        enginesSupplierSpeed.setPaintLabels(true);
        enginesSupplierSpeed.setMinorTickSpacing(500);
        enginesSupplierSpeed.setSnapToTicks(true);

        dealersSpeed = new JSlider(JSlider.HORIZONTAL, 500, 120000, f.getDealers().get(0).getPeriod());
        dealersSpeed.setPaintLabels(true);
        dealersSpeed.setMinorTickSpacing(500);
        dealersSpeed.setSnapToTicks(true);

        workersSpeed = new JSlider(JSlider.HORIZONTAL, 500, 120000, f.getCarBuilder().getWorkTime());
        workersSpeed.setPaintLabels(true);
        workersSpeed.setMinorTickSpacing(500);
        workersSpeed.setSnapToTicks(true);

        Hashtable<Integer, JLabel> hs = new Hashtable<>();
        hs.put(accessoriesSupplierSpeed.getMinimum(), new JLabel(accessoriesSupplierSpeed.getMinimum() + "ms"));
        hs.put(accessoriesSupplierSpeed.getMaximum(), new JLabel(accessoriesSupplierSpeed.getMaximum() / 60 / 1000 + "m"));

        accessoriesSupplierSpeed.setLabelTable(hs);
        bodiesSupplierSpeed.setLabelTable(hs);
        enginesSupplierSpeed.setLabelTable(hs);
        dealersSpeed.setLabelTable(hs);
        workersSpeed.setLabelTable(hs);

        accessoriesLabel = new JLabel("Speed of accessories suppliers: " + accessoriesSupplierSpeed.getValue() + "ms");
        bodiesLabel = new JLabel("Speed of bodies supplier: " + bodiesSupplierSpeed.getValue() + "ms");
        enginesLabel = new JLabel("Speed of engines supplier: " + enginesSupplierSpeed.getValue() + "ms");
        dealersLabel = new JLabel("Speed of dealers: " + dealersSpeed.getValue() + "ms");
        workersLabel = new JLabel("Speed of workers: " + workersSpeed.getValue() + "ms");

        layout = new GroupLayout(this);
        configureLayout();

        accessoriesSupplierSpeed.addChangeListener(
                e -> {
                    for (var supplier : f.getAccessoriesSuppliers()) {
                        supplier.setPeriod(accessoriesSupplierSpeed.getValue());
                    }
                    accessoriesLabel.setText("Speed of accessories suppliers: " + accessoriesSupplierSpeed.getValue() + "ms");
                }
        );

        bodiesSupplierSpeed.addChangeListener(
                e -> {
                    f.getBodiesSupplier().setPeriod((bodiesSupplierSpeed.getValue()));
                    bodiesLabel.setText("Speed of bodies supplier: " + bodiesSupplierSpeed.getValue() + "ms");
                }
        );

        enginesSupplierSpeed.addChangeListener(
                e -> {
                    f.getEnginesSupplier().setPeriod(enginesSupplierSpeed.getValue());
                    enginesLabel.setText("Speed of engines supplier: " + enginesSupplierSpeed.getValue() + "ms");
                }
        );

        dealersSpeed.addChangeListener(
                e -> {
                    for (var dealer : f.getDealers()) {
                        dealer.setPeriod(dealersSpeed.getValue());
                    }
                    dealersLabel.setText("Speed of dealers: " + dealersSpeed.getValue() + "ms");
                }
        );

        workersSpeed.addChangeListener(
                e -> {
                    f.getCarBuilder().setWorkTime(workersSpeed.getValue());
                    workersLabel.setText("Speed of workers: " + workersSpeed.getValue() + "ms");
                }
        );

        setLayout(layout);
    }

    private void configureLayout() {
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createParallelGroup()
                                .addComponent(accessoriesLabel)
                                .addComponent(accessoriesSupplierSpeed))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(bodiesLabel)
                                .addComponent(bodiesSupplierSpeed))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(enginesLabel)
                                .addComponent(enginesSupplierSpeed))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(workersLabel)
                                .addComponent(workersSpeed))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(dealersLabel)
                                .addComponent(dealersSpeed))

        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addComponent(accessoriesLabel)
                        .addComponent(accessoriesSupplierSpeed)
                        .addComponent(bodiesLabel)
                        .addComponent(bodiesSupplierSpeed)
                        .addComponent(enginesLabel)
                        .addComponent(enginesSupplierSpeed)
                        .addComponent(workersLabel)
                        .addComponent(workersSpeed)
                        .addComponent(dealersLabel)
                        .addComponent(dealersSpeed)
        );
    }
}
