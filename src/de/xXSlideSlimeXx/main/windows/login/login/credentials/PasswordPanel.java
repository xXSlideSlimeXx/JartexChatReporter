package de.xXSlideSlimeXx.main.windows.login.login.credentials;

import de.xXSlideSlimeXx.main.windows.TextFieldJPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 22.11.2023
 */
public final class PasswordPanel extends TextFieldJPanel {

    public PasswordPanel() {
        field = new JPasswordField();
        label.setText("Password:");
        field.setPreferredSize(new Dimension(290, 30));
        field.setVisible(true);
        field.setFont(new Font(null, Font.PLAIN, 20));

        field.setToolTipText("Type in your password");
    }

    @Override
    public JPasswordField getTextField() {
        return (JPasswordField) field;
    }
}
