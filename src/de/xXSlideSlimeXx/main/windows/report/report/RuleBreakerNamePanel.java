package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class RuleBreakerNamePanel extends TextFieldJPanel implements Resetable {

    public RuleBreakerNamePanel() {
        super();
        label.setText("Rule breaker Name:");

        field.setToolTipText("Type in the name of the rule breaker");
    }

    @Override
    public void reset() {
        this.field.setText("");
    }
}
