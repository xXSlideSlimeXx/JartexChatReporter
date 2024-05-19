package de.xXSlideSlimeXx.main.upload;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.xXSlideSlimeXx.main.config.Config;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.util.UploadUtil;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author xXSlideSlimeXx
 * @since 19.05.2024
 */
public final class FreeImageHostUpload extends PictureUploader {

    private static final String API_KEY = "6d207e02198a847aa98d0a2a901485a5";
    public FreeImageHostUpload(Config config) {
        super(config, "Free Image Host", "https://iili.io/JPmHSzx.png");
    }

    @Override
    public boolean checkConnection(HttpClient client) {
        return true;
    }

    @Override
    protected String uploadPicture(HttpClient client, File file, ReportDocument document) {
        String bound = UploadUtil.generateRandom();
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create().setBoundary(bound).setCharset(StandardCharsets.UTF_8);
        builder.addBinaryBody("source", file);

        HttpPost post = new HttpPost("https://freeimage.host/api/1/upload?key=" + API_KEY + "&format=json");
        post.setHeader(new BasicHeader("Content-Type", "multipart/form-data; boundary=" + bound));
        post.setEntity(builder.build());

        try {
            return client.execute(post, httpResponse -> {
                final JsonObject obj = JsonParser.parseString(EntityUtils.toString(httpResponse.getEntity())).getAsJsonObject();
                return obj.get("image").getAsJsonObject().get("url").getAsString();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
