package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class UkrnetPage extends Page {

    @Step("Авторизация на Ukrnet ")
    public void loginUkrnet(String ukrnet_email, String ukrnet_password) {
        $(By.name("login")).sendKeys(ukrnet_email);      // ввод в поле емайл валидный емайл
        $(By.name("password")).sendKeys(ukrnet_password);    // ввод в поле пароль валидный пароль
        $(By.xpath("//button [@class='Ol0-ktls jY4tHruE _2Qy_WiMj']")).click(); // нажатия кнопки Продолжить
    }

    public void selectionLastLetterPassword() {
        sleep(5000);
        $(By.xpath("//table[@class='noselect']//tbody/tr[1]")).click();     // выбор 1 письма
        $(By.xpath("//span[@style='display:block;']//a")).click();         // клик по ссылке Нажмите сюда для сброса пароля
    }

    @Step("Открытие письма и клик по ссылке для продолжения регистрации")
    public void selectionLastLetterRegister() {
        sleep(15000);
        $(By.xpath("//table[@class='noselect']//tbody/tr[1]")).click();     // выбор 1 письма
        $(By.xpath("//span[@style='display:block;']//p[4]/a")).click();         // клик по ссылке Нажмите сюда для регистрации
    }
    public void checkLater() {

    }
}
