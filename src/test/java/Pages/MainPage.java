package Pages;

import Config.Values;
import com.codeborne.selenide.Condition;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

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

    @Step("Проверка авторизации")
    public void equals2fa() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();     // Нажать на кнопку меню ЛИЧНЫЙ КАБИНЕТ
        $(By.xpath("//ul[@class='-visible']/li[1]/a")).click();     // клик по разделу выпадающего меню АДМИНКА
        System.out.println("Произошла авторизация с верным кодом 2fa");
        Allure.attachment("Результат", "Произошла авторизация с верным кодом 2fa");
    }

    @Step("Переход в админ панель")
    public void gotoAdminPanel() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();     // Нажать на кнопку меню ЛИЧНЫЙ КАБИНЕТ
        $(By.xpath("//ul[@class='-visible']/li[1]/a")).click();     // клик по разделу выпадающего меню АДМИНКА
    }
    @Step("Переход на страницу мой профиль")
    public void gotoProfile() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']/li[5]/a")).click();
    }


    @Step("Переход в аукцион")
    public void gotoAuction() {
        $(By.xpath("//div[@class='auction-ithem__desc']//a[@class='btn-yel']")).click();
    }
    @Step("Переход в аукцион 49")
    public void gotoAuction49() {
        open("https://coins.dd-dev.club/auction/show/31");
        $(By.xpath("//div[@class='auction-one__content']")).waitUntil(visible, 30000); // ожидания появления елемента
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
