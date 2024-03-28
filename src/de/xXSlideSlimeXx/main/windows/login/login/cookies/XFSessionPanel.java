package de.xXSlideSlimeXx.main.windows.login.login.cookies;

import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;

/**
 * @author xXSlideSlimeXx
 * @since 24.11.2023
 */
public final class XFSessionPanel extends TextFieldJPanel {
    public XFSessionPanel(final XFUserPanel userPanel) {
        super();
        label.setText("XF Session:");

        field.setToolTipText("Copy the xf_session cookie from the jartex website and paste in");
        field.addActionListener(e -> userPanel.getTextField().requestFocus());
    }
}
