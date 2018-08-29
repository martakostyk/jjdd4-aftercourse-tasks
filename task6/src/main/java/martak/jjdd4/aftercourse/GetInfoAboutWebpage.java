package martak.jjdd4.aftercourse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GetInfoAboutWebpage {

    private final Logger LOG = LoggerFactory.getLogger(GetInfoAboutWebpage.class);

    private Scanner scanner = new Scanner(System.in);

    private URL url = null;

    private void getUrl() {
        System.out.println("Hello! Please enter the webpage address: ");

        while (url == null) {
            try {
                url = new URL(scanner.next());
            } catch (MalformedURLException e) {
                System.out.println("Given address is incorrect. Please enter correct address: ");
                LOG.info("Given address is incorrect");
            }
        }
    }

    private void getInfoAboutWebsite() {

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            if (doesWebsiteExist(connection)) {

                connection.setRequestMethod("HEAD");
                System.out.println("content length: " + connection.getContentLengthLong());
                connection.setRequestMethod("OPTIONS");
                System.out.println("allowed methods: " + connection.getHeaderField("Allow"));

            } else {
                System.out.println("Given webpage does not exist");
                LOG.info("Given webpage does not exist");

            }

        } catch (IOException e) {
            LOG.error("There has been error while trying to connect to given url address");
            e.getStackTrace();
        }
    }

    private boolean doesWebsiteExist(HttpURLConnection connection) throws IOException {
        return connection.getResponseCode() == HttpURLConnection.HTTP_OK;
    }

    public static void main(String[] args) {

        GetInfoAboutWebpage getInfoAboutWebpage = new GetInfoAboutWebpage();
        getInfoAboutWebpage.getUrl();
        getInfoAboutWebpage.getInfoAboutWebsite();
    }
}
