package de.xXSlideSlimeXx.main.windows.login.login;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.windows.login.LoginPanel;
import de.xXSlideSlimeXx.main.windows.login.login.cookies.LoginCookiesPanel;
import de.xXSlideSlimeXx.main.windows.login.login.credentials.LoginCredentialsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class LoginTypeSelPanel extends JPanel{
    private boolean loginCookies = false;

    private final JButton cookiesButton;
    private final JButton loginButton;

    private final LoginPanel loginPanel;
    private final LoginCredentialsPanel credentialsPanel;
    private final LoginCookiesPanel cookiesPanel;

    public LoginTypeSelPanel(LoginPanel loginPanel, LoginCredentialsPanel credentialsPanel, LoginCookiesPanel cookiesPanel) {
        if(!Main.CONFIG.getOrDefault(ConfigKey.LOGIN_XF_USER).isEmpty() && !Main.CONFIG.getOrDefault(ConfigKey.LOGIN_XF_SESSION).isEmpty()) {
            loginCookies = true;
        }

        this.loginPanel = loginPanel;
        this.credentialsPanel = credentialsPanel;
        this.cookiesPanel = cookiesPanel;
        this.cookiesButton = getLoginCookieButton();
        this.loginButton = getLoginCredentialsButton();

        this.add(this.loginButton);
        this.add(this.cookiesButton);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(!loginCookies) {
                    credentialsPanel.getEmailPanel().getTextField().requestFocus();
                } else {
                    cookiesPanel.getSessionPanel().getTextField().requestFocus();
                }
            }
        }, 1L);
    }

    private JButton getLoginCookieButton() {
        final JButton button = new JButton();
        button.setText("Cookies");
        button.setVisible(true);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setEnabled(!loginCookies);
        button.setPreferredSize(new Dimension(200, 40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button) {
                    loginCookies = true;
                    cookiesButton.setEnabled(false);
                    loginButton.setEnabled(true);

                    credentialsPanel.setVisible(false);
                    cookiesPanel.setVisible(true);
                    loginPanel.add(cookiesPanel, BorderLayout.CENTER);

                    cookiesPanel.getSessionPanel().getTextField().requestFocus();
                }
            }
        });
        return button;
    }

    private JButton getLoginCredentialsButton() {
        final JButton button = new JButton();
        button.setText("Credentials");
        button.setVisible(true);
        button.setHorizontalAlignment(JLabel.CENTER);
        button.setVerticalAlignment(JLabel.CENTER);
        button.setEnabled(loginCookies);
        button.setPreferredSize(new Dimension(200, 40));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == button) {
                    loginCookies = false;
                    cookiesButton.setEnabled(true);
                    loginButton.setEnabled(false);

                    credentialsPanel.setVisible(true);
                    cookiesPanel.setVisible(false);
                    loginPanel.add(credentialsPanel, BorderLayout.CENTER);

                    credentialsPanel.getEmailPanel().getTextField().requestFocus();

                }
            }
        });
        return button;
    }

    public boolean isLoginCookies() {
        return loginCookies;
    }
}
