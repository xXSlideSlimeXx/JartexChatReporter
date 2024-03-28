package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.windows.SelectableJPane;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 25.11.2023
 */
public final class ScreenshotSelBoxPanel extends SelectableJPane implements Resetable {
    private static final int AMOUNT = 25;
    public ScreenshotSelBoxPanel() {
        super();
        this.comboBox.addItem("Last Screenshot");
        for(int i = 2; i< AMOUNT; i++) {
            int lastDigit = i % 10;
            String ordinal = "th";
            if(lastDigit == 1) {
                ordinal = "st";
            } else if(lastDigit == 2) {
                ordinal = "nd";
            } else if(lastDigit == 3) {
                ordinal = "rd";
            }
            this.comboBox.addItem(i + ordinal + " last Screenshot");
        }
        this.comboBox.setSelectedItem("Last Screenshot");
        this.comboBox.setPreferredSize(new Dimension(20*((int)Math.log10(AMOUNT)) + 200,40));
    }

    @Override
    public void reset() {
        this.comboBox.setSelectedIndex(0);
    }
}
