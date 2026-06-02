import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.LinkedList;

import java.util.Queue;

public class App {
    //getting the API Key
    private static final String API_KEY = System.getenv("TWELVE_DATA_API_KEY");
    private static final String GET_URL = "https://api.twelvedata.com/price?symbol=DIA&apikey="+API_KEY;
    //or i could hard code the api key into the URL variable as so (delete before submitting):
    //private static final String GET_URL = "https://api.twelvedata.com/price?symbol=DIA&apikey={pastemyurlhere}";
    private static final Queue<Double> resultQueue = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        while(true){
            sendGetRequest();
            try {
                Thread.sleep((15000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void sendGetRequest() throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("GET Response code: " + responseCode);
        //if the response code is successful
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // parsing, storing and printing result
            String output =response.toString();
            Double price = getPriceFromOutput(output);
            resultQueue.add(price);
            System.out.println(resultQueue);

            LocalDateTime dateTimeStamp = LocalDateTime.now();
            System.out.println("Added data point: price=  "+ price +
                    ", timestamp= " + dateTimeStamp);
            System.out.println("Current queue size: " + resultQueue.size());
            System.out.println("Waiting 15 seconds to obtain the next value");


        } else {
            System.out.println("GET request did not work.");
        }




    }
    private static Double getPriceFromOutput(String JSONOutput){
        String marker = "\"price\":\"";

        int start = JSONOutput.indexOf(marker);
        start += marker.length();

        int end = JSONOutput.indexOf("\"", start);

        String price = JSONOutput.substring(start, end);

        return Double.parseDouble(price);
    }
}
