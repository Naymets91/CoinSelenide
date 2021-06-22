package Config;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class Values {
    String userName = "1" ;
    String pass = "";
    String homePage = "";



    public void openHomePage() {
        open(homePage);
    }

    public void Login() {
        $(By.className("btn-yel")).click();
        $(By.name("email")).sendKeys(userName);
        $(By.name("password")).sendKeys(pass);
        $(By.className("btn-modal")).click();
    }

}
