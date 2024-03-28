package de.xXSlideSlimeXx.main;

import com.nixxcode.jvmbrotli.common.BrotliLoader;
import de.xXSlideSlimeXx.main.config.Config;
import de.xXSlideSlimeXx.main.config.ConfigKey;
import de.xXSlideSlimeXx.main.config.ConfigManager;
import de.xXSlideSlimeXx.main.doc.mute.BrokenRule;
import de.xXSlideSlimeXx.main.doc.mute.Gamemode;
import de.xXSlideSlimeXx.main.doc.mute.ReportDocument;
import de.xXSlideSlimeXx.main.imgur.ImgurHelper;
import de.xXSlideSlimeXx.main.jartex.JartexHelper;
import de.xXSlideSlimeXx.main.jartex.JartexLogin;
import de.xXSlideSlimeXx.main.jartex.JartexSender;
import de.xXSlideSlimeXx.main.windows.WindowLoad;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static final CookieStore STORE = new BasicCookieStore();
    public static final Config CONFIG = new Config("./config.properties");
    public static HttpClient client;
    public static String firstCookie;

    public static void main(String[] args) throws IOException {
        client = HttpClients.custom().setDefaultCookieStore(STORE).build();
        if(ConfigManager.checkConfiguration(client, CONFIG)) return;
        BrotliLoader.isBrotliAvailable();
        firstCookie = JartexHelper.getFirstCookie(client);
        WindowLoad.load();
    }
}