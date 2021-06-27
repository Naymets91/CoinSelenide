package Pages;

import Config.Values;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    public void loginUser() {
        $(By.className("btn-yel")).click();
        $(By.name("email")).sendKeys(Values.user_email);
        $(By.name("password")).sendKeys(Values.user_password);
        $(By.className("btn-modal")).click();
    }



    public void loginAdmin() {
        $(By.className("btn-yel")).click();
        $(By.name("email")).sendKeys(Values.admin_email);
        $(By.name("password")).sendKeys(Values.admin_password);
        $(By.className("btn-modal")).click();
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        int code = gAuth.getTotpPassword(Values.fa2_secret_key);
        System.out.println("Code = " + code + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        $(By.id("one_time_password")).sendKeys(Integer.toString(code));
        $(By.xpath("//form[@method='POST']//button")).click();
    }

    public void logAut(Integer size) {

        open("https://coins.dd-dev.club/");
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();

        $(By.xpath("//ul[@class='-visible']//li["+ size +"]/a")).click();
    }




}
