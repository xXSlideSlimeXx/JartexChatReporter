package de.xXSlideSlimeXx.main.upload;

import de.xXSlideSlimeXx.main.config.Config;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import org.apache.http.client.HttpClient;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 19.05.2024
 */
public abstract class PictureUploader {
    private final String name;
    private final URL logo_url;
    protected Config config;

    public PictureUploader(Config config, String name, String logo_url) {
        this.name = name;
        this.config = config;
        try {
            this.logo_url = new URL(logo_url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public abstract boolean checkConnection(final HttpClient client);

    protected abstract String uploadPicture(final HttpClient client, final File file, final ReportDocument document);

    public String getName() {
        return name;
    }

    public static File getScreenshotFolder() {
        return new File(System.getenv("APPDATA") + "/.minecraft/screenshots");
    }

    public static List<File> getScreenshot() {
        final File file = getScreenshotFolder();
        if(file.listFiles() == null) return List.of();
        final List<File> files = new ArrayList<>();
        for(final File f : file.listFiles()) {
            if(f.isDirectory()) continue;
            files.add(f);
        }
        Collections.reverse(files);
        return files;
    }

    public String uploadScreenshot(final HttpClient client, ReportDocument document, int latest) {
        final List<File> files = getScreenshot();
        if(files.isEmpty()) return null;
        return uploadPicture(client, files.get(latest), document);
    }

    public URL getLogoURL() {
        return logo_url;
    }



    @Override
    public String toString() {
        return "PictureUploader{" +
                "name='" + name + '\'' +
                ", logo_url='" + logo_url + '\'' +
                ", config=" + config +
                '}';
    }
}
