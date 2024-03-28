package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.doc.mute.Gamemode;
import de.xXSlideSlimeXx.main.windows.SelectableJPane;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class GamemodeSelBoxPanel extends SelectableJPane implements Resetable {

    public GamemodeSelBoxPanel() {
        super();
        for(final Gamemode rules : Gamemode.values()) {
            this.comboBox.addItem(rules.getName());
        }
        final Gamemode gamemode = Gamemode.fromName(Main.CONFIG.getOrDefault(ConfigKey.DEFAULT_GAMEMODE_SELECTED));
        if(gamemode != null) {
            this.comboBox.setSelectedItem(gamemode.getName());
        } else {
            this.comboBox.setSelectedItem(ConfigKey.DEFAULT_GAMEMODE_SELECTED.getDefaultValue());
        }


    }

    @Override
    public void reset() {
        final Gamemode gamemode = Gamemode.fromName(Main.CONFIG.getOrDefault(ConfigKey.DEFAULT_GAMEMODE_SELECTED));
        if(gamemode != null) {
            this.comboBox.setSelectedItem(gamemode.getName());
        } else {
            this.comboBox.setSelectedItem(ConfigKey.DEFAULT_GAMEMODE_SELECTED.getDefaultValue());
        }
    }
}
