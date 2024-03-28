package de.xXSlideSlimeXx.main.windows;

import javax.swing.*;
import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public class SelectableJPane extends JPanel {

    protected JLabel label;
    protected JComboBox<String> comboBox;
    public SelectableJPane() {
        label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 20));

        this.comboBox = new JComboBox<>();
        this.comboBox.setPreferredSize(new Dimension(200, 40));
        this.comboBox.setFont(new Font("Arial", Font.PLAIN, 20));
        this.comboBox.setMaximumRowCount(12);
        add(label);
        add(comboBox);
    }

    public JComboBox<String> getComboBox() {
        return comboBox;
    }
}
