package de.xXSlideSlimeXx.main.windows.login.login.cookies;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;

/**
 * @author xXSlideSlimeXx
 * @since 24.11.2023
 */
public final class XFUserPanel extends TextFieldJPanel {

    public XFUserPanel() {
        super();
        label.setText("XF User:");

        field.setToolTipText("Copy the xf_user cookie from the jartex website and paste in");

        String user = Main.CONFIG.getOrDefault(ConfigKey.LOGIN_XF_USER);
        if(user != null) {
            field.setText(user);
        }
    }
}
