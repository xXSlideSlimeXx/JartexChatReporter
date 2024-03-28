package de.xXSlideSlimeXx.main.jartex;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.imgur.ImgurHelper;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicHeader;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author xXSlideSlimeXx
 * @since 18.11.2023
 */
public final class JartexSender {

    public static final Header[] HEADERS_CHAT_REPORT = new Header[] {
            new BasicHeader(":authority", "jartexnetwork.com"),
            new BasicHeader(":method", "POST"),
            new BasicHeader(":path", "/form/chat-reports.9/submit"),
            new BasicHeader(":scheme", "https"),
            new BasicHeader("Accept", "application/json, text/javascript"),
            new BasicHeader("Accept-Encoding", "gzip, deflate, br"),
            new BasicHeader("Accept-Language", "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7"),
            //new BasicHeader("Content-Length", "1260"),
            new BasicHeader("Origin", "https://jartexnetwork.com"),
            new BasicHeader("Referer", "https://jartexnetwork.com/form/9/select"),
            new BasicHeader("Sec-Ch-Ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Opera GX\";v=\"102\""),
            new BasicHeader("Sec-Ch-Ua-Mobile", "?0"),
            new BasicHeader("Sec-Ch-Ua-Platform", "\"Windows\""),
            new BasicHeader("Sec-Fetch-Dest", "empty"),
            new BasicHeader("Sec-Fetch-Mode", "cors"),
            new BasicHeader("Sec-Fetch-Site", "same-origin"),
            new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 OPR/102.0.0.0"),
            new BasicHeader("X-Requested-With", "XMLHttpRequest")
    };

    private static String getxfToken(HttpClient client, final String session, final String user, final String firstCookie) throws IOException {
        final HttpGet get = new HttpGet("https://jartexnetwork.com/form/9/select");
        final List<Header> basicHeaders = new ArrayList<>(List.of(HEADERS_CHAT_REPORT));
        basicHeaders.add(new BasicHeader("Cookie", firstCookie + "; " + session + "; " + user));
        get.setHeaders(basicHeaders.toArray(new Header[0]));
        return client.execute(get, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws IOException {
                System.err.println(Arrays.toString(httpResponse.getAllHeaders()));
                final String html = JartexHelper.parseBrotli(httpResponse);
                final int index = html.indexOf("<input type=\"hidden\" name=\"_xfToken\" value=\"") + "<input type=\"hidden\" name=\"_xfToken\" value=\"".length();
                return html.substring(index, html.indexOf("\"", index));
            }
        });
    }



    private static void doPostRequest(HttpClient client, final String firstCookie, final ReportDocument reportDocument) throws IOException {
        if(reportDocument.getProof().isEmpty()) {
            System.out.println("You cannot report without a proof!");
            return;
        }
        final String session = "xf_session=" + JartexHelper.getCookie("xf_session").getValue();
        final String user = "xf_user=" + JartexHelper.getCookie("xf_user").getValue();
        final String xfToken = getxfToken(client, session, user, firstCookie);

        final List<Header> basicHeaders = new ArrayList<>(List.of(HEADERS_CHAT_REPORT));
        final HttpPost post = new HttpPost("https://jartexnetwork.com/form/chat-reports.9/submit");
        final String bound = "----WebKitFormBoundary" + generateRandom(16);

        basicHeaders.add(new BasicHeader("Content-Type", "multipart/form-data; boundary=" + bound));
        final JsonObject obj = getJsonObject(xfToken, reportDocument);
        basicHeaders.add(new BasicHeader("Cookie", firstCookie + "; " + user + "; " + session));

        /*final StringBuilder builder = new StringBuilder("--" + bound +"\n" +
                "Content-Disposition: form-data; name=\"_xfToken\"\n" +
                "\n" +
                xfToken + "\n");
        for(final Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            builder.append("--" + bound + "\nContent-Disposition: form-data; name=\"" + entry.getKey() + "\"\n\n" + entry.getValue().getAsString() + "\n");
        }
        builder.append("--" + bound + "--");
        System.out.println(builder);
        post.setEntity(new StringEntity(builder.toString()));*/
        post.setEntity(getEntity(obj, bound));
        post.setHeaders(basicHeaders.toArray(new Header[0]));
        client.execute(post, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws IOException {
                if(httpResponse.getStatusLine().getStatusCode() == 403) {
                    throw new IOException("Cannot send chat report; Maybe cookies login data are wrong?");
                }
                return JartexHelper.parseBrotli(httpResponse);
            }
        });
    }

    private static HttpEntity getEntity(final JsonObject obj, final String bound) {
        final MultipartEntityBuilder builder = MultipartEntityBuilder.create().setBoundary(bound).setCharset(StandardCharsets.UTF_8);
        for(final Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            builder.addTextBody(entry.getKey(), entry.getValue().getAsString());
        }
        return builder.build();
    }

    private static JsonObject getJsonObject(String xfToken, final ReportDocument reportDocument) {
        final JsonObject obj = new JsonObject();
        obj.addProperty("question[8]", reportDocument.getName());
        obj.addProperty("question[122]", reportDocument.getRuleBreaker());
        obj.addProperty("question[123]", reportDocument.getBrokenRule().getReportId());
        obj.addProperty("question[124]", reportDocument.getGamemode().getReportId());
        obj.addProperty("question[125]", reportDocument.getProof());
        obj.addProperty("question[126]", reportDocument.getExtra());
        obj.addProperty("_xfRequestUri", "/form/9/select");
        obj.addProperty("_xfWithData", "1");
        obj.addProperty("_xfToken", xfToken);
        obj.addProperty("_xfResponseType", "json");
        return obj;
    }

    public static String generateRandom(final int to) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i< to; i++) {
            builder.append(abc.charAt(new Random().nextInt(abc.length())));
        }
        return builder.toString();
    }

    public static boolean sendReport(final HttpClient client, final String firstCookie, final ReportDocument reportDocument, int latest) {
        final String img = ImgurHelper.uploadScreenshot(client, reportDocument.getRuleBreaker()
                + " | " + reportDocument.getBrokenRule().getName(), reportDocument.getExtra(), latest);
        if(reportDocument.getProof().isEmpty()) {
            System.out.println(img);
            reportDocument.setProof(img);
        }
        try {
            doPostRequest(client, firstCookie, reportDocument);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
