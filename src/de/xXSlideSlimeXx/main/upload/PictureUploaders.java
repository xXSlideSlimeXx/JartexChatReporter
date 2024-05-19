package de.xXSlideSlimeXx.main.upload;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.Config;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xXSlideSlimeXx
 * @since 19.05.2024
 */
public final class PictureUploaders {
    private static Set<PictureUploader> uploaders;
    private static List<PictureUploader> validUploaders;

    public static ImgurUpload IMGUR;
    public static ImgbbUpload IMGBB;
    public static FreeImageHostUpload FREE_IMAGE_HOST;

    public static void initalize() {
        uploaders = new HashSet<>();
        Config config = Main.CONFIG;

        IMGUR = reg(new ImgurUpload(config));
        IMGBB = reg(new ImgbbUpload(config));
        FREE_IMAGE_HOST = reg(new FreeImageHostUpload(config));



        validUploaders = new ArrayList<>();
        for(PictureUploader uploader : uploaders) {
            if(uploader.checkConnection(Main.client)) {
                validUploaders.add(uploader);
            }
        }
    }

    private static <T extends PictureUploader> T reg(T uploader) {
        uploaders.add(uploader);
        return uploader;
    }

    public static List<PictureUploader> getValidUploaders() {
        return validUploaders;
    }

    public static Set<PictureUploader> getUploaders() {
        return uploaders;
    }
}
