package de.xXSlideSlimeXx.main.windows.login;

import de.xXSlideSlimeXx.main.windows.WindowLoad;

import javax.swing.*;
import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class LoginFrame extends JFrame {


    
    public LoginFrame() {
        this.setSize(430, 420);
        this.setLayout(new BorderLayout());
        add(getTitleLabel(), BorderLayout.NORTH);

        final JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 10));
        add(panel, BorderLayout.SOUTH);
        add(new LoginPanel(this), BorderLayout.CENTER);

        /*JTextField text = new JTextField();
        text.setSize(new Dimension(200, 40));
        this.add(text);*/
        WindowLoad.setupDefault(this);
    }

    private JLabel getTitleLabel() {
        final JLabel label = new JLabel();
        label.setText("Login");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setVisible(true);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        return label;
    }
}
