package de.xXSlideSlimeXx.main.windows.report.report;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.upload.PictureUploader;
import de.xXSlideSlimeXx.main.upload.PictureUploaders;
import de.xXSlideSlimeXx.main.windows.report.Resetable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 19.05.2024
 */
public final class PictureUploadPanel extends JPanel implements Resetable {

    private final JComboBox<ImgNText> comboBox;
    public PictureUploadPanel() {
        super();
        this.comboBox = new JComboBox<>();

        this.comboBox.setPreferredSize(new Dimension(200, 40));
        this.comboBox.setModel(getModel());
        this.comboBox.setRenderer(new ImageTextRenderer());

        add(this.comboBox);
        selectPreferredItem();
    }

    private DefaultComboBoxModel<ImgNText> getModel() {
        DefaultComboBoxModel<ImgNText> model = new DefaultComboBoxModel<>();
        for (PictureUploader pictureUploader : PictureUploaders.getValidUploaders()) {
            model.addElement(new ImgNText(pictureUploader));
        }
        return model;
    }

    @Override
    public void reset() {
        selectPreferredItem();
    }

    private void selectPreferredItem() {
        int found = 0;
        List<PictureUploader> list = PictureUploaders.getValidUploaders();
        String name = Main.CONFIG.getOrDefault(ConfigKey.DEFAULT_UPLOAD_SERVICE);
        for(int i = 0; i<list.size(); i++) {
            if(list.get(i).getName().equalsIgnoreCase(name)) {
                found = i;
                break;
            }
        }
        this.comboBox.setSelectedIndex(found);
    }

    public PictureUploader getSelectedPictureUpload() {
        return PictureUploaders.getValidUploaders().get(this.comboBox.getSelectedIndex());
    }

    private static class ImgNText {
        private final String name;
        private final Icon img;

        public ImgNText(PictureUploader pictureUploader) {
            ImageIcon icon = new ImageIcon(pictureUploader.getLogoURL());

            Dimension dim = getScaledDimension(new Dimension(icon.getIconWidth(), icon.getIconHeight()), new Dimension(200, 40));
            Image img = icon.getImage().getScaledInstance((int) dim.getWidth(), (int) dim.getHeight(), Image.SCALE_SMOOTH);
            icon.setImage(img);

            this.name = pictureUploader.getName();
            this.img = icon;
        }

        public String getName() {
            return name;
        }

        public Icon getIcon() {
            return img;
        }

        //copied code
        public static Dimension getScaledDimension(Dimension imgSize, Dimension boundary) {

            int original_width = imgSize.width;
            int original_height = imgSize.height;
            int bound_width = boundary.width;
            int bound_height = boundary.height;
            int new_width = original_width;
            int new_height = original_height;

            // first check if we need to scale width
            if (original_width > bound_width) {
                //scale width to fit
                new_width = bound_width;
                //scale height to maintain aspect ratio
                new_height = (new_width * original_height) / original_width;
            }

            // then check if we need to scale even with the new height
            if (new_height > bound_height) {
                //scale height to fit instead
                new_height = bound_height;
                //scale width to maintain aspect ratio
                new_width = (new_height * original_width) / original_height;
            }

            return new Dimension(new_width, new_height);
        }
    }

    private static class ImageTextRenderer extends JLabel implements ListCellRenderer<ImgNText> {

        @Override
        public Component getListCellRendererComponent(JList<? extends ImgNText> list, ImgNText value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(" " + value.getName());
            setIcon(value.getIcon());

            return this;
        }
    }
}
