package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class ReporterNamePanel extends TextFieldJPanel implements Resetable {

    public ReporterNamePanel() {
        super();
        label.setText("Reporters name:");

        field.setToolTipText("Type in your username");
        field.setText(Main.CONFIG.getOrDefault(ConfigKey.DEFAULT_REPORTER_NAME));
    }

    @Override
    public void reset() {
        this.field.setText(Main.CONFIG.getOrDefault(ConfigKey.DEFAULT_REPORTER_NAME));
    }
}
