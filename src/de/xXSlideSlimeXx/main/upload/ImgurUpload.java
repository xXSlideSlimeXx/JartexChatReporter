package de.xXSlideSlimeXx.main.upload;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.xXSlideSlimeXx.main.config.Config;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.util.UploadUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
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
    public String uploadPicture(final HttpClient client1, final File file, final ReportDocument document) {
        try {
            HttpClient client = HttpClients.createDefault();
            final HttpPost post = new HttpPost("https://api.imgur.com/3/image");
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setBoundary("----WebKitFormBoundary" + UploadUtil.generateRandom());
            builder.addBinaryBody("image", file);
            builder.addTextBody("title", document.getRuleBreaker() + " | " + document.getBrokenRule().getName());
            builder.addTextBody("type", "file");
            post.addHeader("Authorization", "Client-ID " + clientId);
            post.setEntity(builder.build());
            String[] linkData = client.execute(post, httpResponse -> {
                final JsonObject data = JsonParser.parseString(EntityUtils.toString(httpResponse.getEntity())).getAsJsonObject().get("data").getAsJsonObject();
                return new String[] {"https://imgur.com/" + data.get("id").getAsString(), data.get("deletehash").getAsString()};
            });
            changeDesc(client, linkData[0], linkData[1], document);
            return linkData[0];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void changeDesc(HttpClient client, String link, String imgDeleteHash, ReportDocument doc) {
        try {
            final HttpPost post = new HttpPost("https://api.imgur.com/3/image/" + imgDeleteHash);
            final MultipartEntityBuilder builder = MultipartEntityBuilder.create()
                    .setBoundary("----WebKitFormBoundary" + UploadUtil.generateRandom());
            builder.addTextBody("description", "Link: " + link + "\n\n" + doc.getExtra());
            builder.addTextBody("type", "file");
            post.addHeader("Authorization", "Client-ID " + clientId);
            post.setEntity(builder.build());
            client.execute(post);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
