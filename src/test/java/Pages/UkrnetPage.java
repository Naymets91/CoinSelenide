package Pages;

import Config.Values;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class UkrnetPage extends Page {

    public void loginUkrnet() {
        $(By.name("login")).sendKeys(Values.ukrnet_email);      // ввод в поле емайл валидный емайл
        $(By.name("password")).sendKeys(Values.ukrnet_password);    // ввод в поле пароль валидный пароль
        $(By.xpath("//button [@class='Ol0-ktls jY4tHruE _2Qy_WiMj']")).click(); // нажатия кнопки Продолжить
    }

    public void selectionLastLetter() {
        sleep(20000);
        $(By.xpath("//table[@class='noselect']//tbody/tr[1]")).click();     // выбор 1 письма
        $(By.xpath("//span[@style='display:block;']//a")).click();         // клик по ссылке Нажмите сюда для сброса пароля
    }

}
