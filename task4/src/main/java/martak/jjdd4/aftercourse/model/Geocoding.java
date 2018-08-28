package martak.jjdd4.aftercourse.model;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

public class Geocoding {

    private final String API_KEY;

    public Geocoding(String api_key) {
        API_KEY = api_key;
    }

    private void displayOnMap(String address) {
        final Client client = ClientBuilder.newClient();
        final WebTarget webTarget = client.target("https://maps.googleapis.com/maps/api/geocode/json?address="
                + address
                + "&key="
                + API_KEY);
        final Response response = webTarget.request().get();

    }

}
