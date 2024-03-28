package de.xXSlideSlimeXx.main.jartex;

import com.nixxcode.jvmbrotli.dec.BrotliInputStream;
import de.xXSlideSlimeXx.main.Main;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.message.BasicHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xXSlideSlimeXx
 * @since 19.11.2023
 */
public final class JartexHelper {

    public static final Header[] DEFAULT_HEADERS = new Header[] {
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

    public static final Header[] LOGIN_HEADERS = new Header[] {
            new BasicHeader(":authority", "jartexnetwork.com"),
            new BasicHeader(":method", "POST"),
            new BasicHeader(":path", "/login/login"),
            new BasicHeader(":scheme", "https"),
            new BasicHeader("Cache-Control", "max-age=0"),
            new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9," +
                    "image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7"),
            new BasicHeader("Accept-Encoding", "gzip, deflate, br"),
            new BasicHeader("Accept-Language", "de-DE,de;q=0.9,en-US;q=0.8,en;q=0.7"),
            new BasicHeader("Content-Type", "application/x-www-form-urlencoded"),
            new BasicHeader("Origin", "https://jartexnetwork.com"),
            new BasicHeader("Referer", "https://jartexnetwork.com/"),
            new BasicHeader("Sec-Ch-Ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Opera GX\";v=\"102\""),
            new BasicHeader("Sec-Ch-Ua-Mobile", "?0"),
            new BasicHeader("Sec-Ch-Ua-Platform", "\"Windows\""),
            new BasicHeader("Sec-Fetch-Dest", "document"),
            new BasicHeader("Sec-Fetch-Mode", "navigate"),
            new BasicHeader("Sec-Fetch-Site", "same-origin"),
            new BasicHeader("Sec-Fetch-User:", "?1"),
            new BasicHeader("Upgrade-Insecure-Requests:", "1"),
            new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36 OPR/102.0.0.0")
    };

    public static List<Header> toList(final Header[] headers) {
        return new ArrayList<>(List.of(headers));
    }

    public static String getFirstCookie(HttpClient client) throws IOException {
        final HttpGet get = new HttpGet("https://jartexnetwork.com");
        get.setHeaders(DEFAULT_HEADERS);
        return client.execute(get, new ResponseHandler<String>() {
            @Override
            public String handleResponse(HttpResponse httpResponse) throws IOException {
                for(final Header header : httpResponse.getAllHeaders()) {
                    if(header.getName().equals("set-cookie")) {
                        return header.getValue().substring(0, header.getValue().indexOf(";"));
                    }
                }
                return null;
            }
        });
    }

    public static String parseBrotli(final HttpResponse httpResponse) {
        try {
            final BrotliInputStream brotliInputStream = new BrotliInputStream(httpResponse.getEntity().getContent());
            final BufferedReader reader = new BufferedReader(new InputStreamReader(brotliInputStream));
            return reader.lines().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Cookie getCookie(final String name) {
        for(final Cookie cookie : Main.STORE.getCookies()) {
            if(cookie.getName().equals(name)) {
                return cookie;
            }
        }
        return null;
    }
}
