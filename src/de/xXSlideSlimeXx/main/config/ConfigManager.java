package de.xXSlideSlimeXx.main.config;

import de.xXSlideSlimeXx.main.doc.mute.Gamemode;
import de.xXSlideSlimeXx.main.upload.ImgurUpload;
import de.xXSlideSlimeXx.main.upload.PictureUploaders;
import org.apache.http.client.HttpClient;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 28.03.2024
 */
public final class ConfigManager {

    public static boolean checkConfiguration(Config cf) {
        List<String> errors = new ArrayList<>();
        Gamemode gm = Gamemode.fromName(cf.getOrDefault(ConfigKey.DEFAULT_GAMEMODE_SELECTED));
        if(gm == null) {
            errors.add("- Invalid Gamemode");
        }
        if(PictureUploaders.getValidUploaders().isEmpty()) {
            errors.add("- No upload service is set up");
        }
        try{
            Integer.parseInt(cf.getOrDefault(ConfigKey.TIME_LIMIT));
        }catch (final NumberFormatException ex) {
            errors.add("- Invalid time limit");
        }
        if(!errors.isEmpty()) {
            JOptionPane.showMessageDialog(null, "These errors occurred during startup:\n" + String.join("\n", errors),
                    "Configuration error", JOptionPane.ERROR_MESSAGE);
            return true;
        }
        return false;
    }
}
