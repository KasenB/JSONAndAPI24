import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;

public class D_ReadATLA {

    public static void main(String[] args) throws ParseException {
        D_ReadATLA atla = new D_ReadATLA();
    }

    public D_ReadATLA() throws ParseException {
        pull();
    }

    public void pull() throws ParseException {
        String output = "";
        String jsonString = "";
        try {

            URL url = new URL("https://last-airbender-api.fly.dev/api/v1/characters"); /** Your API's URL goes here */
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));


            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
//                System.out.println(output);
                jsonString += output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // turn your string into a JSON object using a parser
        JSONParser parser = new JSONParser();
        JSONArray jsonArray = (JSONArray) parser.parse(jsonString);


//        System.out.println("JSON ARRAY: " + jsonArray);

        // print the name of every character

//        JSONArray nameArray = (JSONArray) jsonObject.get("name");
//        int n = nameArray.size();
//        System.out.println("NAMES: ");
//        for (int i = 0; i < n; i++) {
//            String film = (String) nameArray.get(i);
//            System.out.println(film);
//        }

        /* TODO : print the allies of the first character in the JSON */
        // here is a line to get you started:

        // print each ally on its own line

//        System.out.println("ALLIES:");
//        for (int i = 0; i < allies.size(); i++){
//            String ally = (String) allies.get(i);
//            System.out.println(ally);
//        }

        /* TODO : print the "name" of every character in the jsonArray */
        JSONObject character = (JSONObject) jsonArray.get(3); // 0 index is the first character

        for (int q = 0; q < jsonArray.size(); q ++){
            character = (JSONObject) jsonArray.get(q);
            String name = (String) character.get("name");
            System.out.println(name);
        }
//        JSONObject jsonObject = (JSONObject) jsonArray.get(0); // 0 index is the first character
//        String name = (String) jsonObject.get("name");
//        System.out.println(name);

    }
}


