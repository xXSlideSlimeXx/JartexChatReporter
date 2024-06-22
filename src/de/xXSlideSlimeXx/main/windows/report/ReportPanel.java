package de.xXSlideSlimeXx.main.windows.report;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.doc.mute.Gamemode;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.upload.ImgurUpload;
import de.xXSlideSlimeXx.main.jartex.JartexSender;
import de.xXSlideSlimeXx.main.windows.report.report.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author xXSlideSlimeXx
 * @since 21.11.2023
 */
public final class ReportPanel extends JPanel {

    private final int timeLimit;

    private final Timer timer;

    private final ReporterNamePanel reporterNamePanel;
    private final RuleBreakerNamePanel ruleBreakerNamePanel;
    private final BrokenRuleSelBoxPanel brokenRuleSelPanel;
    private final GamemodeSelBoxPanel gamemodeSelPanel;
    private final ExtraInfoJPanel extraInfoJPanel;
    private final ScreenshotSelBoxPanel screenshotSelBoxPanel;
    private final PictureUploadPanel pictureUploadPanel;

    public ReportPanel() {
        timer = new Timer();

        this.timeLimit = Integer.parseInt(Main.CONFIG.getOrDefault(ConfigKey.TIME_LIMIT));

        setLayout(new BorderLayout(0, 30));
        this.screenshotSelBoxPanel = new ScreenshotSelBoxPanel();
        this.brokenRuleSelPanel = new BrokenRuleSelBoxPanel();
        this.gamemodeSelPanel = new GamemodeSelBoxPanel();
        this.reporterNamePanel = new ReporterNamePanel();
        this.ruleBreakerNamePanel = new RuleBreakerNamePanel();
        this.extraInfoJPanel = new ExtraInfoJPanel();
        this.pictureUploadPanel = new PictureUploadPanel();

        this.reporterNamePanel.init();
        this.ruleBreakerNamePanel.init();
        this.extraInfoJPanel.init();

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        panel.add(this.reporterNamePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.ruleBreakerNamePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.brokenRuleSelPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.gamemodeSelPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.extraInfoJPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.screenshotSelBoxPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(this.pictureUploadPanel);
        add(panel, BorderLayout.CENTER);
        add(getSubmitButton(), BorderLayout.SOUTH);
        //add second/third/fourth last screenshot
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(!reporterNamePanel.getTextField().getText().isEmpty()) {
                    ruleBreakerNamePanel.getTextField().requestFocus();
                }
            }
        }, 107);
    }

    private JPanel getSubmitButton() {
        final JPanel panel = new JPanel();
        final JButton button = new JButton();
        button.setText("Submit");
        button.setToolTipText("Fill out the report and then click to submit");
        button.setFont(new Font("Arial", Font.PLAIN, 20));
        button.setPreferredSize(new Dimension(200, 40));
        button.setAlignmentX(JTextField.CENTER);
        button.setAlignmentY(JTextField.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(button.getText().equals("Sending...")) return;
                if(reporterNamePanel.getTextField().getText().trim().isEmpty()) {
                    scheduleResetButton(button, "Please type in your username");
                    return;
                }
                if(ruleBreakerNamePanel.getTextField().getText().trim().isEmpty()) {
                   scheduleResetButton(button, "Please type in a valid rule breaker name");
                   return;
                }
                final File[] files = ImgurUpload.getScreenshotFolder().listFiles();
                if(files == null || screenshotSelBoxPanel.getComboBox().getSelectedIndex() >= files.length) {
                    scheduleResetButton(button, "The " + screenshotSelBoxPanel.getComboBox().getSelectedItem() +
                            " does not exist!");
                }
                button.setText("Sending...");
                Thread thread = new Thread(() -> {
                    if(JartexSender.sendReport(Main.client, pictureUploadPanel.getSelectedPictureUpload(), Main.firstCookie, new ReportDocument(
                            reporterNamePanel.getTextField().getText().trim(),
                            ruleBreakerNamePanel.getTextField().getText().trim(),
                            extraInfoJPanel.getTextField().getText().trim(),
                            BrokenRuleSelBoxPanel.indexMap.get(brokenRuleSelPanel.getComboBox().getSelectedIndex()),
                            Gamemode.fromName((String) gamemodeSelPanel.getComboBox().getSelectedItem())
                    ), screenshotSelBoxPanel.getComboBox().getSelectedIndex())) {
                        button.setText("Sending successful!");
                        button.setPreferredSize(new Dimension(420, 40));
                        button.setForeground(Color.GREEN);
                        schedule(button);
                        final File img = ImgurUpload.getScreenshot().get(screenshotSelBoxPanel.getComboBox().getSelectedIndex());
                        if(img != null && img.exists() && Boolean.parseBoolean(Main.CONFIG.getOrDefault(ConfigKey.DELETE_FILE_AFTER_REPORT))) {
                            try{
                                img.delete();
                            }catch (final SecurityException ex) {
                                ex.printStackTrace();
                            }
                        }
                        reset();
                    } else {
                        scheduleResetButton(button, "Sending failed! Maybe login went wrong");
                    }
                });
                thread.start();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if(thread.isAlive()) {
                            scheduleResetButton(button, "Sending failed! Time limit exceeded");
                            thread.interrupt();
                        }
                    }
                }, 1000L * timeLimit);
            }
        });

        panel.add(button);
        return panel;
    }

    private void reset() {
        this.screenshotSelBoxPanel.reset();
        this.brokenRuleSelPanel.reset();
        this.gamemodeSelPanel.reset();
        this.reporterNamePanel.reset();
        this.ruleBreakerNamePanel.reset();
        this.extraInfoJPanel.reset();

        if(!reporterNamePanel.getTextField().getText().isEmpty()) {
            ruleBreakerNamePanel.getTextField().requestFocus();
        }
    }

    private void scheduleResetButton(final JButton button, String errorMessage) {
        button.setText(errorMessage);
        button.setForeground(Color.RED);
        button.setPreferredSize(new Dimension(420, 40));
        schedule(button);
    }

    private void schedule(final JButton button) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                button.setText("Submit");
                button.setForeground(Color.BLACK);
                button.setPreferredSize(new Dimension(200, 40));
            }
        }, 3000);
    }
}
