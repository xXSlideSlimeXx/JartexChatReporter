package de.xXSlideSlimeXx.main.upload;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import de.xXSlideSlimeXx.main.config.Config;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.util.UploadUtil;
import de.xXSlideSlimeXx.main.windows.WindowLoad;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * @author xXSlideSlimeXx
 * @since 19.05.2024
 */
public final class ImgbbUpload extends PictureUploader {

    public static final String NAME = "ImgBB";
    private final String apiKey;
    public ImgbbUpload(Config config) {
        super(config, NAME, "https://pipedream.com/s.v0/app_1M0hkk/logo/orig");
        this.apiKey = config.getOrDefault(ConfigKey.IMGBB_API_KEY);
    }

    @Override
    public boolean checkConnection(HttpClient client) {
        if(apiKey == null) return false;
        HttpPost post = new HttpPost("https://api.imgbb.com/1/upload?key=" + apiKey +  "&image=" + WindowLoad.LOGO_URL + "&expiration=60");
        try {
            return client.execute(post, httpResponse -> {
                final String s = EntityUtils.toString(httpResponse.getEntity());
                return s.contains("\"success\":true,");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected String uploadPicture(HttpClient client, File file, ReportDocument document) {
        String bound = UploadUtil.generateRandom();
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create().setBoundary(bound).setCharset(StandardCharsets.UTF_8);
        builder.addBinaryBody("image", file);

        HttpPost post = new HttpPost("https://api.imgbb.com/1/upload?key=" + apiKey +  "&name=" + URLEncoder.encode(document.getRuleBreaker() + " | " + document.getBrokenRule().getName(), StandardCharsets.UTF_8));
        post.setHeader(new BasicHeader("Content-Type", "multipart/form-data; boundary=" + bound));
        post.setEntity(builder.build());
        try {
            return client.execute(post, httpResponse -> {
                final JsonObject obj = JsonParser.parseString(EntityUtils.toString(httpResponse.getEntity())).getAsJsonObject();
                return obj.get("data").getAsJsonObject().get("url_viewer").getAsString();
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
