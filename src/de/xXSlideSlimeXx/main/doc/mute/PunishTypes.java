package de.xXSlideSlimeXx.main.doc.mute;

import java.awt.*;

public enum PunishTypes {
    MINOR((byte) 0, new Color(223, 210, 56)),
    MODERATE((byte) 1, new Color(245, 115, 43)),
    SERVERE((byte) 2, new Color(243, 104, 104)),
    ;

    private final byte weight;
    private final Color color;

    PunishTypes(byte weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public byte getWeight() {
        return weight;
    }

    public String getColorAsString() {
        return String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public static int compare(PunishTypes other1, PunishTypes other2) {
        return Byte.compare(other1.getWeight(), other2.getWeight());
    }
}
