package de.xXSlideSlimeXx.main.config;

import de.xXSlideSlimeXx.main.doc.mute.Gamemode;

public enum ConfigKey {
    DEFAULT_REPORTER_NAME("default_reporter_name", ""),
    DEFAULT_GAMEMODE_SELECTED("default_gamemode_selected", Gamemode.MINIGAMES.getName()),
    DELETE_FILE_AFTER_REPORT("delete_file_after_report", "false"),
    IMGUR_CLIENT_ID("imgur_client_id", null)
    ;

    private final String key;
    private final String defaultValue;

    ConfigKey(String key, String defaultValue) {
        this.key = key;
        this.defaultValue = defaultValue;
    }

    public String getKey() {
        return key;
    }

    public String getDefaultValue() {
        return defaultValue;
    }
}
