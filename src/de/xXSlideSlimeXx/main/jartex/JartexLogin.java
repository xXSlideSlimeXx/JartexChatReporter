package de.xXSlideSlimeXx.main.jartex;

import de.xXSlideSlimeXx.main.Main;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xXSlideSlimeXx
 * @since 18.11.2023
 */
public final class JartexLogin {

    public static void login(HttpClient client, String firstCookie, String email, String password) throws IOException {
        final String xfToken = getxfToken(client, firstCookie);
        final HttpPost post = new HttpPost("https://jartexnetwork.com/login/login");
        final List<Header> headers = JartexHelper.toList(JartexHelper.LOGIN_HEADERS);
        headers.add(new BasicHeader("Cookie", firstCookie));
        post.setHeaders(headers.toArray(new Header[0]));
        final String url = getURL(new BasicNameValuePair("_xfToken", xfToken), new BasicNameValuePair("login", email),
                new BasicNameValuePair("password", password), new BasicNameValuePair("remember", "1"),
                new BasicNameValuePair("_xfRedirect", "https://jartexnetwork.com/"));
        post.setEntity(new StringEntity(url));
        client.execute(post, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) {
                System.out.println(httpResponse.getStatusLine());
                return JartexHelper.parseBrotli(httpResponse);
            }
        });
        if(JartexHelper.getCookie("xf_session") == null) {
            throw new IOException("User credentials are wrong");
        }
    }

    public static void loginFromCookie(String sessionValue, String userValue) {
        Main.STORE.addCookie(new BasicClientCookie("xf_session", sessionValue));
        Main.STORE.addCookie(new BasicClientCookie("xf_user", userValue));
    }

    private static String getxfToken(HttpClient client, final String firstCookie) throws IOException {
        final HttpGet get = new HttpGet("https://jartexnetwork.com/login/login");
        final List<Header> basicHeaders = new ArrayList<>(List.of(JartexHelper.LOGIN_HEADERS));
        basicHeaders.add(new BasicHeader("Cookie", firstCookie));
        get.setHeaders(basicHeaders.toArray(new Header[0]));
        return client.execute(get, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws IOException {
                final String html = JartexHelper.parseBrotli(httpResponse);
                final int index = html.indexOf("<input type=\"hidden\" name=\"_xfToken\" value=\"") + "<input type=\"hidden\" name=\"_xfToken\" value=\"".length();
                return html.substring(index, html.indexOf("\"", index));
            }
        });
    }

    private static String getURL(final BasicNameValuePair... pairs) {
        final StringBuilder builder = new StringBuilder();
        for(final BasicNameValuePair pair : pairs) {
            builder.append("&").append(pair.getName()).append("=").append(URLEncoder.encode(pair.getValue(), StandardCharsets.UTF_8));
        }
        return builder.substring(1);
    }
}
