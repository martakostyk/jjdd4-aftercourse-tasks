package martak.jjdd4.aftercourse;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpOptions;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class GetInfoAboutWebpage {

    private final Logger LOG = LoggerFactory.getLogger(GetInfoAboutWebpage.class);

    private Scanner scanner = new Scanner(System.in);

    private URL url = null;


    private void getUrl() {

        while (url == null) {
            try {
                url = new URL(scanner.next());
            } catch (MalformedURLException e) {
                System.out.println("Given address is incorrect. Please enter correct address: ");
                LOG.info("Incorrect address has been given. Error message: " + e.getMessage());
            }
        }
    }

    private boolean doesWebsiteExists() {

        try {
            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
            return connection.getResponseCode() == HttpURLConnection.HTTP_OK;

        } catch (Exception e) {
            System.out.println("Could not find given address. Website might not exist.");
            LOG.info("Error while trying to connect with given address, error message: " + e.getMessage());
        }

        return false;
    }

    private String getContentLength() {

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpHead request = new HttpHead(String.valueOf(url));
            HttpResponse response = client.execute(request);

            if (response.containsHeader("content-length")) {
                return response.getFirstHeader("content-length").getValue();
            }

        } catch (Exception e) {
            LOG.info("Error while trying to get content length, error message: " + e.getMessage());
        }

        return "not found";
    }

    private String getHttpMethods() {

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpOptions request = new HttpOptions(String.valueOf(url));
            HttpResponse response = client.execute(request);

            if(response.containsHeader("allow")) {
                return response.getFirstHeader("allow").getValue();
            } else if (response.containsHeader("access-control-allow-methods")) {
                return response.getFirstHeader("access-control-allow-methods").getValue();
            }

        } catch (Exception e) {
            LOG.info("Error while trying to get allowed http methods, error message: " + e.getMessage());
        }

        return "not found";

    }

    public static void main(String[] args) {

        GetInfoAboutWebpage getInfoAboutWebpage = new GetInfoAboutWebpage();

        System.out.println("Hello! Please enter the webpage address: ");

        getInfoAboutWebpage.getUrl();

        if (getInfoAboutWebpage.doesWebsiteExists()) {
            System.out.println("Content length: " + getInfoAboutWebpage.getContentLength());
            System.out.println("Allowed http methods: " + getInfoAboutWebpage.getHttpMethods());
        } else {
            System.out.println("Could not find given address. Website might not exist.");
        }
    }
}
