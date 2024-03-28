package de.xXSlideSlimeXx.main.windows;

import javax.swing.*;
import java.awt.*;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public class TextFieldJPanel extends JPanel {

    protected JLabel label;
    protected JTextField field;

    public TextFieldJPanel() {
        setLayout(new GridLayout(2,1));
        this.setPreferredSize(new Dimension(300, 75));
        setBackground(Color.GRAY);

        label = new JLabel();
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        label.setVisible(true);
        field = new JTextField();
        field.setFont(new Font(null, Font.PLAIN, 20));

        field.setAlignmentX(JTextField.LEFT_ALIGNMENT);
        field.setAlignmentY(JTextField.TOP_ALIGNMENT);
        field.setPreferredSize(new Dimension(290, 30));
        field.setVisible(true);
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return field;
    }

    public void init() {
        add(label);
        add(field);
    }
}
