package de.xXSlideSlimeXx.main.config;

import de.xXSlideSlimeXx.main.doc.mute.Gamemode;
import de.xXSlideSlimeXx.main.upload.ImgurUpload;

public enum ConfigKey {
    DEFAULT_REPORTER_NAME("default_reporter_name", ""),
    DEFAULT_GAMEMODE_SELECTED("default_gamemode_selected", Gamemode.MINIGAMES.getName()),
    DELETE_FILE_AFTER_REPORT("delete_file_after_report", "false"),
    DEFAULT_UPLOAD_SERVICE("default_upload_service", ImgurUpload.NAME),
    LOGIN_XF_SESSION("login_xf_session", ""),
    LOGIN_XF_USER("login_xf_user", ""),
    IMGUR_CLIENT_ID("imgur_client_id", null),
    IMGBB_API_KEY("imgbb_api_key", null),
    TIME_LIMIT("time_limit", "15"),
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
