package Pages;

import Config.Values;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;


public class AdminPanelPage extends Page {

public void limitCahEdit () {
    $(By.xpath("//ul[@id='main-menu-navigation']/li[2]")).should(visible).click();
    $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]")).should(visible).click();
    $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]//li[3]//span")).should(visible).click();
}

    public void auctions() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
    }

    public void loti () {
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();      //  клик по меню лоты
    }

    public void auctionAdd() {
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[1]//span")).click(); //  клик и переход на страницу аукционов
    }


    public void editPassUser() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[2]")).should(visible).click();      // клик на меню пользователи
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]")).should(visible).click(); // клик на меню клиенты
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]//li[1]//span")).should(visible).click(); // клик на меню клиенты
    }
    public void searchUser(){
    $(By.xpath("//*[@id='dataTable1_filter']/label/input")).sendKeys(Values.ukrnet_email);  // в поле поиск ввести емайл пользователя
    $(byXpath("//*[text()='testcoins179@ukr.net']/..//a[1]")).should(visible).click();  // нажать на кнопку изминить
    }
}

