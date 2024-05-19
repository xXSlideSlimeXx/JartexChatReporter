package de.xXSlideSlimeXx.main.windows;

import de.xXSlideSlimeXx.main.windows.login.LoginFrame;
import de.xXSlideSlimeXx.main.windows.report.ReportFrame;

import javax.swing.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class WindowLoad {

    public static String LOGO_URL = "https://avatars.githubusercontent.com/u/39830022?s=280&v=4";
    public static void load() {
        new LoginFrame();
    }

    public static void setupDefault(final JFrame frame) {
        ImageIcon Iicon = null;
        try {
            Iicon = new ImageIcon(new URL(LOGO_URL));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if(Iicon != null) {
            frame.setIconImage(Iicon.getImage());
        }
        frame.setTitle("Jartex Reporter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
