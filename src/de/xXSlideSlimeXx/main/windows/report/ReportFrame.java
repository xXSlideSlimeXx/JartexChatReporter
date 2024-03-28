package de.xXSlideSlimeXx.main.windows.report;

import de.xXSlideSlimeXx.main.windows.WindowLoad;

import javax.swing.*;
import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 24.11.2023
 */
public final class ReportFrame extends JFrame {

    public ReportFrame() {

        this.setSize(450, 420);
        this.setLayout(new BorderLayout(0, 30));

        add(getTitleLabel(), BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.add(new ReportPanel());
        add(panel, BorderLayout.CENTER);
        //setPreferredSize(new Dimension(600, 500));
        WindowLoad.setupDefault(this);
    }

    private JLabel getTitleLabel() {
        final JLabel label = new JLabel();
        label.setText("Chat Report");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.TOP);
        label.setFont(new Font("Arial", Font.PLAIN, 40));
        label.setVisible(true);
        label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        return label;
    }
}
