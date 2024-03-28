package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.doc.mute.BrokenRule;
import de.xXSlideSlimeXx.main.windows.SelectableJPane;
import de.xXSlideSlimeXx.main.windows.report.ComboBoxToolTipRenderer;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

import java.awt.*;;
import java.util.*;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class BrokenRuleSelBoxPanel extends SelectableJPane implements Resetable {

    public static final Map<Integer, BrokenRule> indexMap = new HashMap<>();
    public BrokenRuleSelBoxPanel() {
        super();
        final List<String> desc = new ArrayList<>();
        ComboBoxToolTipRenderer renderer = new ComboBoxToolTipRenderer();
        comboBox.setRenderer(renderer);
        int i = 0;
        for(final BrokenRule rules : BrokenRule.values()) {
            this.comboBox.addItem("<html><p><font color=" + rules.getPunishType().getColorAsString() +">■ </font>" + rules.getName() + "</p></html>");
            final StringBuilder builder = new StringBuilder();
            for(final String s : rules.getExamples()) {
                final StringTokenizer tokenizer = new StringTokenizer(s, "\n");
                final String token = tokenizer.nextToken();
                if(!tokenizer.hasMoreTokens()) {
                    builder.append("⌈ ").append(token).append("<br>");
                } else {
                    builder.append("⌈ ").append(token).append("<br>");
                    while (tokenizer.hasMoreTokens()) {
                        builder.append("| ").append(tokenizer.nextToken()).append("<br>");
                    }
                }
            }
            desc.add("<html><p>" + rules.getDescription() + (rules.getExamples().length != 0 ? "<br>Example:<br>" + builder : "") + "</p></html>");
            indexMap.put(i, rules);
            i++;
        }
        renderer.setTooltips(desc);
        this.comboBox.setPreferredSize(new Dimension(425,40));
    }

    @Override
    public void reset() {
        this.comboBox.setSelectedIndex(0);
    }
}
