package de.xXSlideSlimeXx.main.config;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.doc.mute.Gamemode;
import de.xXSlideSlimeXx.main.imgur.ImgurHelper;
import org.apache.http.client.HttpClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 28.03.2024
 */
public final class ConfigManager {

    public static boolean checkConfiguration(HttpClient client, Config cf) {
        List<String> errors = new ArrayList<>();
        Gamemode gm = Gamemode.fromName(cf.getOrDefault(ConfigKey.DEFAULT_GAMEMODE_SELECTED));
        String clientId = cf.getOrDefault(ConfigKey.IMGUR_CLIENT_ID);
        if(gm == null) {
            errors.add("- Invalid Gamemode");
        }
        if(clientId == null || clientId.trim().isEmpty()) {
            errors.add("- Invalid imgur client ID");
        } else if(ImgurHelper.checkClientId(client, clientId)) {
            errors.add("- Client ID does not work for uploading");
        }
        if(!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, "These errors occurred during startup:\n" + String.join("\n", errors),
                    "Configuration error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
