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

public class E_PracticeHW {

    public static void main(String[] args) throws ParseException {
        E_PracticeHW pokemon = new E_PracticeHW();
    }

    public E_PracticeHW() throws ParseException {
        pull();
    }

    public void pull() throws ParseException {
        String output = "";
        String jsonString="";
        try {

            URL url = new URL("https://pokeapi.co/api/v2/pokemon/pikachu/"); /** Your API's URL goes here */
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
                System.out.println(output);
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
        JSONObject pikachu = (JSONObject) parser.parse(jsonString);
        System.out.println("Pikachu's JSON: " + pikachu);

        /* TODO : print out the name of each ability that Pikachu has */

        JSONArray abilities = (JSONArray) pikachu.get("abilities");
        System.out.println("ABILITIES:");
        for (int q = 0; q < abilities.size(); q++){
            JSONObject ability = (JSONObject) abilities.get(q);
            JSONObject power = (JSONObject) ability.get("ability");
            String name = (String) power.get("name");
            System.out.println(name);
        }

        // feel free to paste this URL into your browser to explore the JSON: https://pokeapi.co/api/v2/pokemon/pikachu/

        JSONArray heldItems = (JSONArray) pikachu.get("held_items");
        System.out.println("\n HELD ITEMS:");
        for (int z = 0; z < heldItems.size(); z++){
            JSONObject item = (JSONObject) heldItems.get(z);
            JSONObject itemV2 = (JSONObject) item.get("item");
            String name = (String) itemV2.get("name");
            System.out.println(name);
//            for (int y = 0; y < heldItems.size(); y++){
//                JSONObject versionDetails = (JSONObject) item.get("version_details");
//                JSONObject versionThings = (JSONObject) versionDetails.get("name");
//                System.out.println(versionThings);
//            }
        }
    }
}