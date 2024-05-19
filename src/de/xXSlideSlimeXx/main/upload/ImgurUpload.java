package de.xXSlideSlimeXx.main.upload;

import de.xXSlideSlimeXx.main.config.Config;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.jartex.JartexSender;
import de.xXSlideSlimeXx.main.util.UploadUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;

public class ImgurUpload extends PictureUploader {

    public static final String NAME = "Imgur";

    private final String clientId;
    public ImgurUpload(Config config) {
        super(config, NAME, "https://play-lh.googleusercontent.com/gjX-aBU0iIAmfJqUWg8rWifLPDWNwRe0aIj46216Bq0cpKnEm6nC3pG7-3uh56wd5AE");
        this.clientId = config.getOrDefault(ConfigKey.IMGUR_CLIENT_ID);
    }

    @Override
    public boolean checkConnection(final HttpClient client) {
        if(clientId == null) return false;
        final HttpPost post = new HttpPost("https://api.imgur.com/3/image/jMZqN4X");
        post.addHeader("Authorization", "Client-ID " + clientId);
        try {
            return client.execute(post, httpResponse -> {
                final String s = EntityUtils.toString(httpResponse.getEntity());
                return !s.contains("Invalid client_id");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String uploadPicture(final HttpClient client, final File file, final ReportDocument document) {
        try {
            final HttpPost post = new HttpPost("https://api.imgur.com/3/image");
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setBoundary("----WebKitFormBoundary" + UploadUtil.generateRandom());
            builder.addBinaryBody("image", file);
            builder.addTextBody("title", document.getRuleBreaker() + " | " + document.getBrokenRule().getName());
            builder.addTextBody("description", document.getExtra());
            builder.addTextBody("type", "file");
            post.addHeader("Authorization", "Client-ID " + clientId);
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
}
