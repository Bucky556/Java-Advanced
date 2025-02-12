package HttpsClient_Gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class URLAndURLConnection {
    public static void main(String[] args) throws Exception {
        HttpClient httpClient = HttpClient.newHttpClient();
        //getRequest(httpClient);
        //postRequest(httpClient);
        //timeoutRequest(httpClient);
        authDelete();

    }

    private static void authDelete() throws IOException, InterruptedException {
        HttpClient httpClient1 = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    public PasswordAuthentication requestPasswordAuthenticationInstance(String host, InetAddress add, int port, String protocol, String prompt, String scheme, URL url, RequestorType reqType) {
                        return new PasswordAuthentication("user","password".toCharArray());
                    }
                }).build();
        HttpRequest httpRequest = HttpRequest
                .newBuilder(URI.create("http://localhost:8080/post/delete/79"))
                .DELETE()
                .build();
        HttpResponse<String> httpResponse = httpClient1.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse);
        System.out.println(httpResponse.body());
    }

    private static void timeoutRequest(HttpClient httpClient) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/timeout/request/"))
                .GET()
                .timeout(Duration.ofSeconds(2))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse);
        System.out.println(httpResponse.body());
    }

    private static void postRequest(HttpClient httpClient) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/post/create/"))
                .headers("Content-Type","application/json") // bu metho-data bulib pastdagi codimiz qaysi typeda ekanligini kursatyapti(bizniki json type da)
                .POST(HttpRequest.BodyPublishers.ofString("""
                        {
                        	"postTitle" : "Title: b903a528-f7f5-481d-aa1b-ade3b98e0265",
                        	"postBody" : "Lorem ipsum."
                        }
                        """))
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse);
        System.out.println(httpResponse.body());
    }

    private static void getRequest(HttpClient httpClient) throws IOException, InterruptedException {
        HttpRequest httpRequest = HttpRequest.newBuilder(URI.create("http://localhost:8080/posts/"))
                .GET()
                .build();
        HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse.body());
        System.out.println(httpResponse.uri());
        System.out.println(httpResponse.statusCode());
        Type type = new TypeToken<List<Post>>() {
        }.getType();
        List<Post> posts = new Gson().fromJson(httpResponse.body(), type);
        posts.forEach(System.out::println);
    }
}
