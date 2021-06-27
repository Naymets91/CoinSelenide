package Pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class MainPage extends Page {

    String beafore;
    String after;
    String temp;
    String parsName;

    Integer size;
    Integer sizeRandom;

    public void SwitchLanguageRu() {
        $(By.className("flag")).click();
        $(By.xpath("//div[@class='nav-lang__list']/a[3]")).click();
    }

    public void gotoAdminPanel() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']/li[1]/a")).click();
    }
    public void gotoProfile() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']/li[5]/a")).click();
    }




}
