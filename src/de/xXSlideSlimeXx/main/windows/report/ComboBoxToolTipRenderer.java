package de.xXSlideSlimeXx.main.windows.report;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 24.11.2023
 */
public final class ComboBoxToolTipRenderer extends DefaultListCellRenderer {
    private List<String> tooltips;

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {

        JComponent comp = (JComponent) super.getListCellRendererComponent(list,
                value, index, isSelected, cellHasFocus);

        if (-1 < index && null != value && null != tooltips) {
            list.setToolTipText(tooltips.get(index));
        }
        return comp;
    }

    public void setTooltips(List<String> tooltips) {
        this.tooltips = tooltips;
    }
}