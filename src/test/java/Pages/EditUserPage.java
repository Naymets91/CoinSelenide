package Pages;

import Config.Values;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class EditUserPage extends Page {
    @Step("Изминения пароля пользователя")
    public void editPassword() {
        $(By.xpath("//input[@id='password']")).clear(); // очистить поле
        $(By.xpath("//input[@id='password']")).sendKeys(Values.ukrnet_password);    // ввести новий пароль в поле пароль
        $(By.xpath("//input[@id='password_confirmation']")).clear();    // очистить поле
        $(By.xpath("//input[@id='password_confirmation']")).sendKeys(Values.ukrnet_password);   // ввести новий пароль в поле пароль
        $(By.xpath("//button[@type='submit']")).click();    // нажать кнопку изминить
    }
}
