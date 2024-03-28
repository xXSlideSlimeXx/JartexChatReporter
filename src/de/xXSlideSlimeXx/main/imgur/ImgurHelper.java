package de.xXSlideSlimeXx.main.imgur;

import de.xXSlideSlimeXx.main.Main;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.jartex.JartexSender;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ImgurHelper {

    public static boolean checkClientId(final HttpClient client, String clientId) {
        final HttpPost post = new HttpPost("https://api.imgur.com/3/image/jMZqN4X");
        post.addHeader("Authorization", "Client-ID " + clientId);
        try {
            return client.execute(post, httpResponse -> {
                final String s = EntityUtils.toString(httpResponse.getEntity());
                return s.contains("Invalid client_id");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static String uploadImage(final HttpClient client, final File file, final String title, final String desc) {
        try {
            final HttpPost post = new HttpPost("https://api.imgur.com/3/image");
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setBoundary("----WebKitFormBoundary" + JartexSender.generateRandom(16));
            builder.addBinaryBody("image", file);
            builder.addTextBody("title", title);
            builder.addTextBody("description", desc);
            builder.addTextBody("type", "file");
            post.addHeader("Authorization", "Client-ID " + Main.CONFIG.getOrDefault(ConfigKey.IMGUR_CLIENT_ID));
            post.setEntity(builder.build());
            return client.execute(post, httpResponse -> {
                final String s = EntityUtils.toString(httpResponse.getEntity());
                final int index = s.indexOf("\"link\":\"") + "\"link\":\"".length();
                return s.substring(index, s.lastIndexOf(".", s.indexOf("\"", index))).replace("\\/", "/")
                        .replace("\u200B", "");
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public static String uploadScreenshot(final HttpClient client, String title, String desc, int latest) {
        final List<File> files = getScreenshot();
        if(files.isEmpty()) return null;
        return uploadImage(client, files.get(latest), title, desc);
    }
}
