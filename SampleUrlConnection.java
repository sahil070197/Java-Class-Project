import java.io.*;
import java.net.*;
import org.json.simple.*;

public class SampleUrlConnection {
    private static final String API_KEY = "8a71258d91df68b20064a75233199183";
    private static final String URL = "https://api.themoviedb.org";
    private static final String LANGUAGE = "hi-IN";
    private static final String GET_REQUEST = "GET";
    private static final String POST_REQUEST = "POST";

    static void getCurrentMovies() throws Exception {
        int page = 1;
        String requestURLString = URL + "/3/movie/now_playing?api_key=" + API_KEY + "&language=" + LANGUAGE + "&page="+ page;
        URL requestURL = new URL(requestURLString);
        HttpURLConnection connection = (HttpURLConnection) requestURL.openConnection();
        connection.setRequestMethod(GET_REQUEST);
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            InputStreamReader connectionInputStream = new InputStreamReader(connection.getInputStream());
            BufferedReader reader = new BufferedReader(connectionInputStream);
            String inputLine = reader.readLine();
            
            JSONParser parser = new JSONParser();
        }
    }

    public static void main(String args[]) {
        try {
            getCurrentMovies();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
