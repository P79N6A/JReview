package com.example.java.HttpClient;


import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <!-- https://mvnrepository.com/artifact/commons-httpclient/commons-httpclient -->
 * <dependency>
 * <groupId>commons-httpclient</groupId>
 * <artifactId>commons-httpclient</artifactId>
 * <version>3.1</version>
 * </dependency>
 */
//实现了先登录在利用cookie进行获取网站信息
public class HttpClientTest {
    //    private static final String USERNAME = "czbcxy";
//    private static final String PASSWORD = "123qweasdzxc";
    //    private static final String LOGIN_URL = "http://passport.mop.com/?targetUrl=http://hi.mop.com/?&g=1447141423230&loginCheck=UNLOGINED";
//    private static final String REQUEST_URL = "http://hi.mop.com/?";
//    private static final String REFERER = "http://passport.mop.com/";

    private static final String LOGIN_URL = "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)&_=1551418719251";
    private static final String USERNAME = "15709298119";
    private static final String PASSWORD = "czb19940318";
    private static final String REQUEST_URL = "https://login.sina.com.cn/signup/signin.php?entry=blog&r=http%3A%2F%2Fcontrol.blog.sina.com.cn%2Fadmin%2Farticle%2Farticle_add.php%3Findex&from=referer:http://control.blog.sina.com.cn/admin/article/article_add.php?index,func:0006";
    //    来源
    private static final String REFERER = "http://i.blog.sina.com.cn/blogprofile/profilelatestnote.php";


    public static String HttpRequest() {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(LOGIN_URL);
        NameValuePair[] data = {new NameValuePair("loginName", USERNAME), new NameValuePair("loginPasswd", PASSWORD)};
        postMethod.setRequestBody(data);
        try {
            httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
            int statusCode = httpClient.executeMethod(postMethod);
            Cookie[] cookies = httpClient.getState().getCookies();
            StringBuffer tmpcookies = new StringBuffer();
            for (Cookie c : cookies) {
                tmpcookies.append(c.toString() + ";");
                System.out.println("cookies = " + c.toString());
            }
            if (statusCode == 200 || statusCode == 302) {
                GetMethod getMethod = new GetMethod(REQUEST_URL);
                getMethod.setRequestHeader("cookie", tmpcookies.toString());
                postMethod.setRequestHeader("Referer", REFERER);
                postMethod.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
                httpClient.executeMethod(getMethod);
                return getMethod.getResponseBodyAsString();
            } else {
                return "login error!";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "login error!";
    }

    //    常规获取cookie
    public static String GCookie() {
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("username", "15709298119");
        map1.put("password", "czb19940318");
        String url = "https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)&_=1551418719251";
        PrintWriter out = null;
        InputStream in = null;
        try {
            HttpURLConnection conn = getConnUrl(url,"POST");
            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            out.print(map1);
            out.flush();
            String cookie = conn.getHeaderField("Set-Cookie").split(";")[0];
            return cookie;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
        return "error";
    }


    public static String SCookie(String cookie) {
        String url = "https://login.sina.com.cn/signup/signin.php?entry=sso";
        InputStream in = null;
        try {
            HttpURLConnection conn = getConnUrl(url,"GET");
            conn.setRequestProperty("Cookie", cookie);
            conn.connect();
            in = conn.getInputStream();
            String result = new BufferedReader(new InputStreamReader(in, "utf8"))
                    .lines().parallel().collect(Collectors.joining("\n"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "error 2";
    }

    public static HttpURLConnection getConnUrl(String url,String method) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) (new URL(url).openConnection());
        //设置超时时间
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(15000);
        conn.setRequestMethod(method);
        conn.setRequestProperty("accept", "*/*");
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
        return conn;
    }

    public static void main(String[] args) {
//        System.out.println(HttpRequest());
        String cookie = GCookie();
        System.err.println(cookie);
        System.out.println(SCookie(cookie));
    }


}