package de.xXSlideSlimeXx.main.windows.login;

import de.xXSlideSlimeXx.main.windows.login.login.LoginTypeSelPanel;
import de.xXSlideSlimeXx.main.windows.login.login.cookies.LoginCookiesPanel;
import de.xXSlideSlimeXx.main.windows.login.login.credentials.LoginCredentialsPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class LoginPanel extends JPanel {

    private final LoginTypeSelPanel typeSelPanel;
    private final LoginCredentialsPanel credentialsPanel;
    private final LoginCookiesPanel cookiesPanel;
    public LoginPanel(final LoginFrame frame) {
        this.setLayout(new BorderLayout(0, 30));
        this.credentialsPanel = new LoginCredentialsPanel(frame);
        this.cookiesPanel = new LoginCookiesPanel(frame);
        this.typeSelPanel = new LoginTypeSelPanel(this, credentialsPanel, cookiesPanel);

        add(this.typeSelPanel, BorderLayout.NORTH);
        if(this.typeSelPanel.isLoginCookies())
            add(this.cookiesPanel, BorderLayout.CENTER);
        if(!this.typeSelPanel.isLoginCookies())
            add(this.credentialsPanel, BorderLayout.CENTER);

        final JPanel panel = new JPanel();
        panel.add(getLoginButton(frame));
        panel.setPreferredSize(new Dimension(210, 50));
        add(panel, BorderLayout.SOUTH);

        this.cookiesPanel.setVisible(this.typeSelPanel.isLoginCookies());
        this.credentialsPanel.setVisible(!this.typeSelPanel.isLoginCookies());
    }

    private JButton getLoginButton(final LoginFrame frame) {
        final JButton button = new JButton();
        button.setText("Login");
        button.setToolTipText("Fill the E-mail and password fields and then click login");
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JTextField.CENTER);
        button.setAlignmentY(JTextField.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!typeSelPanel.isLoginCookies()) {
                    credentialsPanel.onLogin(frame);
                } else {
                    cookiesPanel.onLogin(frame);
                }
            }
        });
        return button;
    }
}
