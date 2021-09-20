package Pages;

import Config.Values;
import io.qameta.allure.Step;
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

    @Step("Перейти в раздел Заявки на удаление аккаунта")
    public void requestDelUser () {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[2]")).should(visible).click();
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]")).should(visible).click();
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]//li[2]//span")).should(visible).click();
    }

    public void auctions() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[1]//span")).click(); //  клик и переход на страницу аукционов
    }

    public void loti () {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();      //  клик по меню лоты
    }



@Step ("Переход в пункт меню Клиенты")
    public void editPassUser() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[2]")).should(visible).click();      // клик на меню пользователи
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]")).should(visible).click(); // клик на меню клиенты
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]//li[1]//span")).should(visible).click(); // клик на меню клиенты
    }
    @Step ("Поиск нужного пользователя")
    public void searchUser(){
    $(By.xpath("//*[@id='dataTable1_filter']/label/input")).sendKeys(Values.ukrnet_email);  // в поле поиск ввести емайл пользователя
    $(byXpath("//*[text()='testcoins179@ukr.net']/..//a[1]")).should(visible).click();  // нажать на кнопку изминить
    }
    @Step("Удаления пользователя")
    public void delUser( String name) {
        $(byXpath("//*[text()='" + name + "']/..//input")).should(visible).click();
        $(byXpath("//a[@id='customer_deleter']")).should(visible).click();

    }

    @Step("Удаление пользователя администратором")
    public void DelUserAdmin(String name) {
       $(By.xpath("//div[@id='dataTable1_filter']//input")).sendKeys(Values.userRegDelMail);  // ввод в поле поска електронки удаляемого пользователя
        $(byXpath("//*[text()='" + name + "']/..//input/../a")).should(visible).click();    // нажать кнопку удалить
        $(byXpath("//div[@class='modal-footer']/button")).should(visible).click();    // подтверждение удаления
    }

    @Step("Переход на вкладку пользователи")
    public void User() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[2]")).should(visible).click();
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]")).should(visible).click();
        $(By.xpath("//*[@id='main-menu-navigation']/li[2]//li[3]//li[1]//span")).should(visible).click();
    }
////////////////////////////////////////////////////////////настройка
    ///////////////////////////////////////////////// Категории
@Step("Переход на вкладку категории")
    public void category() {
    $(By.xpath("//ul[@id='main-menu-navigation']/li[7]")).should(visible).click();      // клик на меню настройкм
    $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[2]")).should(visible).click(); // клик на меню категории
    }

    @Step("Переход на вкладку период")
    public void period() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[7]")).should(visible).click();      // клик на меню настройкм
        $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[4]")).should(visible).click(); // клик на меню фильтры
        $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[4]//li[1]")).should(visible).click(); // клик на меню период
    }
    @Step("Переход на вкладку номинал")
    public void denomination() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[7]")).should(visible).click();      // клик на меню настройкм
        $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[4]")).should(visible).click(); // клик на меню фильтры
        $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[4]//li[2]")).should(visible).click(); // клик на меню номинал
    }

    public void materials() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[7]")).should(visible).click();      // клик на меню настройкм
        $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[4]")).should(visible).click(); // клик на меню фильтры
        $(By.xpath("//*[@id='main-menu-navigation']/li[7]//li[4]//li[3]")).should(visible).click(); // клик на меню материал
    }

    public void news() {
        $(By.xpath("//ul[@id='main-menu-navigation']/li[5]")).should(visible).click();      // клик на меню информация
        $(By.xpath("//*[@id='main-menu-navigation']/li[5]//li[1]")).should(visible).click(); // клик на меню новости
    }
}

