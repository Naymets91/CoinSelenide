package Config;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

public class JsonRead {

public void read () {
    Object obj = null; // Object obj = new JSONParser().parse(new FileReader("JSONExample.json"));
    try {
        obj = new JSONParser().parse(new FileReader("src/test/resources/valueslocal.json"));
        JSONObject jo = (JSONObject) obj;
// Достаём
        Values.homePage = (String) jo.get("homePage");
        Values.ukrnetPage = (String) jo.get("ukrnetPage");

        Values.ukrnet_email = (String) jo.get("ukrnet_email");
        Values.ukrnet_password = (String) jo.get("ukrnet_password");

        Values.userReset_email = (String) jo.get("userReset_email");
        Values.userRegDelMail = (String) jo.get("userRegDelMail");
        Values.userRegDelPassword = (String) jo.get("userRegDelPassword");

        Values.user1_email = (String) jo.get("user1_email");
        Values.user1_password = (String) jo.get("user1_password");

        Values.user2_email = (String) jo.get("user2_email");
        Values.user2_password = (String) jo.get("user2_password");

        Values.user3_Limit_email = (String) jo.get("user3_Limit_email");
        Values.user3_Limit_password = (String) jo.get("user3_Limit_password");

        Values.admin_email = (String) jo.get("admin_email");
        Values.admin_password = (String) jo.get("admin_password");

        Values.fa2_secret_key = (String) jo.get("fa2_secret_key");


    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
// Кастим obj в JSONObject

// Достаем массив номеров
//    JSONArray phoneNumbersArr = (JSONArray) jo.get("phoneNumbers");
//    Iterator phonesItr = phoneNumbersArr.iterator();
//    System.out.println("phoneNumbers:");
// Выводим в цикле данные массива
//    while (phonesItr.hasNext()) {
//        JSONObject test = (JSONObject) phonesItr.next();
//        System.out.println("- type: " + test.get("type") + ", phone: " + test.get("number"));
//    }
}

//    public static void main(String[] args) {
//        JSONParser parser = new JSONParser();
//
//        try (Reader reader = new FileReader("src/test/resources/values.json")) {
//      obj = new JSONParser().parse(new FileReader("src/test/resources/values.json"));
//            JSONObject jsonObject = (JSONObject) parser.parse(reader);
//            System.out.println(jsonObject);
//
//            Values.homePage = (String) jsonObject.get("homePage");
//            System.out.println(Values.homePage);
//
//            Values.ukrnetPage = (String) jsonObject.get("ukrnetPage");
//            System.out.println(Values.ukrnetPage);
//
//            Values.ukrnet_email = (String) jsonObject.get("ukrnet_email");
//            System.out.println(Values.ukrnet_email);
//
//            Values.ukrnet_password = (String) jsonObject.get("ukrnet_password");
//            System.out.println(Values.ukrnet_password);
//
//            Values.userReset_email = (String) jsonObject.get("userReset_email");
//            System.out.println(Values.userReset_email);
//
//            Values.userRegDelMail = (String) jsonObject.get("userRegDelMail");
//            System.out.println(Values.userRegDelMail);
//
//            Values.userRegDelPassword = (String) jsonObject.get("userRegDelPassword");
//            System.out.println(Values.userRegDelPassword);
//
//            Values.user1_email = (String) jsonObject.get("user1_email");
//            System.out.println(Values.user1_email);
//
//            Values.user1_password = (String) jsonObject.get("user1_password");
//            System.out.println(Values.user1_password);
//
//            Values.user2_email = (String) jsonObject.get("user2_email");
//            System.out.println(Values.user2_email);
//
//            Values.user2_password = (String) jsonObject.get("user2_password");
//            System.out.println(Values.user2_password);
//
//            Values.user3_Limit_email = (String) jsonObject.get("user3_Limit_email");
//            System.out.println(Values.user3_Limit_email);
//
//            Values.user3_Limit_password = (String) jsonObject.get("user3_Limit_password");
//            System.out.println(Values.user3_Limit_password);
//
//            Values.admin_email = (String) jsonObject.get("user3_Limit_email");
//            System.out.println(Values.user3_Limit_email);
//
//            Values.admin_password = (String) jsonObject.get("admin_password");
//            System.out.println(Values.admin_password);
//
//            Values.fa2_secret_key = (String) jsonObject.get("fa2_secret_key");
//            System.out.println(Values.fa2_secret_key);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//    }
}
