package de.xXSlideSlimeXx.main.windows.login.login.credentials;

import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;

/**
 * @author xXSlideSlimeXx
 * @since 22.11.2023
 */
public final class EmailPanel extends TextFieldJPanel {

    public EmailPanel(final PasswordPanel panel) {
        super();
        label.setText("E-mail:");

        field.setToolTipText("Type in your e-mail address");
        field.addActionListener(e -> panel.getTextField().requestFocus());
    }
}
