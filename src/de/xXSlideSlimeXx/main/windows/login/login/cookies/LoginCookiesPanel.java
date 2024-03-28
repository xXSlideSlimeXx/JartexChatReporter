package de.xXSlideSlimeXx.main.windows.login.login.cookies;

import de.xXSlideSlimeXx.main.jartex.JartexLogin;
import de.xXSlideSlimeXx.main.windows.login.LoginFrame;
import de.xXSlideSlimeXx.main.windows.report.ReportFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 22.11.2023
 */
public final class LoginCookiesPanel extends JPanel {
    private final XFSessionPanel sessionPanel;
    private final XFUserPanel userPanel;

    public LoginCookiesPanel(LoginFrame frame) {
        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(310, 160));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        this.userPanel = new XFUserPanel();
        this.sessionPanel = new XFSessionPanel(this.userPanel);
        this.sessionPanel.init();
        this.userPanel.init();

        panel.add(this.sessionPanel);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(this.userPanel);
        add(panel);

        this.userPanel.getTextField().addActionListener(e -> onLogin(frame));
    }

    public void onLogin(final LoginFrame frame) {
        frame.dispose();
        JartexLogin.loginFromCookie(this.sessionPanel.getTextField().getText(), this.userPanel.getTextField().getText());
        new ReportFrame();
    }

    public XFSessionPanel getSessionPanel() {
        return sessionPanel;
    }

    public XFUserPanel getUserPanel() {
        return userPanel;
    }
}
