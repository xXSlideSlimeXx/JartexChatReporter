package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class ExtraInfoJPanel extends TextFieldJPanel implements Resetable {

    public ExtraInfoJPanel() {
        super();
        label.setText("Extra Information:");

        field.setToolTipText("Type in extra information or leave it blank");
    }

    @Override
    public void reset() {
        field.setText("");
    }
}
