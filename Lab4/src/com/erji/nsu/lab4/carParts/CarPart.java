package com.erji.nsu.lab4.carParts;

import java.util.UUID;

public abstract class CarPart {
    private UUID partID;

    public CarPart() {
        generatePartID();
    }

    public void generatePartID() {
        partID = UUID.randomUUID();
    }

    public UUID getPartID() {
        return partID;
    }
}
