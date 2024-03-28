package de.xXSlideSlimeXx.main.windows.login.login.credentials;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.jartex.JartexLogin;
import de.xXSlideSlimeXx.main.windows.login.LoginFrame;
import de.xXSlideSlimeXx.main.windows.report.ReportFrame;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * @author xXSlideSlimeXx
 * @since 22.11.2023
 */
public final class LoginCredentialsPanel extends JPanel {
    private final EmailPanel emailPanel;
    private final PasswordPanel passwordPanel;

    public LoginCredentialsPanel(final LoginFrame frame) {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(310, 160));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        this.passwordPanel = new PasswordPanel();
        this.emailPanel = new EmailPanel(this.passwordPanel);
        this.emailPanel.init();
        this.passwordPanel.init();

        panel.add(this.emailPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.passwordPanel);
        add(panel);

        this.passwordPanel.getTextField().addActionListener(e -> onLogin(frame));
    }

    public void onLogin(final LoginFrame login) {
        login.setVisible(false);
        StringBuilder builder = new StringBuilder();
        boolean exception = false;
        for (final char c : this.passwordPanel.getTextField().getPassword()) {
            builder.append(c);
        }
        try {
            JartexLogin.login(Main.client, Main.firstCookie, this.emailPanel.getTextField().getText(), builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            exception = true;
        }
        if(exception) {
            this.emailPanel.getTextField().setText("");
            login.setVisible(true);
        } else {
            login.dispose();
            new ReportFrame();
        }
        builder = null;
        this.passwordPanel.getTextField().setText("");
    }

    public EmailPanel getEmailPanel() {
        return emailPanel;
    }

    public PasswordPanel getPasswordPanel() {
        return passwordPanel;
    }
}
