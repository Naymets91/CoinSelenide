package Pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
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


    @Step("Переход в админ панель")
    public void gotoAdminPanel() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();     // Нажать на кнопку меню ЛИЧНЫЙ КАБИНЕТ
        $(By.xpath("//ul[@class='-visible']/li[1]/a")).click();     // клик по разделу выпадающего меню АДМИНКА
    }

    public void gotoProfile() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']/li[5]/a")).click();
    }

    @Step("Переход в аукцион")
    public void gotoAuction() {
        $(By.xpath("//div[@class='auction-ithem__desc']//a[@class='btn-yel']")).click();
    }
    @Step("Переход на страницу избраного")
    public void goFavoritesPage() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();     // Нажать на кнопку меню ЛИЧНЫЙ КАБИНЕТ
        $(By.xpath("//ul[@class='-visible']/li[2]/a")).click();     // клик по разделу выпадающего меню АДМИНКА
    }

    public void gotoNews() {
        $(By.xpath("//ul[@class='header-nav__nav -horizontal']/li[4]")).click();
    }
}
