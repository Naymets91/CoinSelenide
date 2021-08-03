package Config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class JsonSimpleReadExample {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader("src\\test\\resources\\test.json")) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String homePage = (String) jsonObject.get("homePage");
            System.out.println(homePage);

            String ukrnetPage = (String) jsonObject.get("ukrnetPage");
            System.out.println(ukrnetPage);

            String ukrnet_email = (String) jsonObject.get("ukrnet_email");
            System.out.println(ukrnet_email);

            String ukrnet_password = (String) jsonObject.get("ukrnet_password");
            System.out.println(ukrnet_password);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}