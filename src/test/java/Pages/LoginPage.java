package Pages;

import Config.Values;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends Page {

    String tempStr;
    Integer tempInt;

    String password;


    public void loginUser() {
        $(By.className("btn-yel")).click();         // клик по кнопке вход
        $(By.name("email")).sendKeys(Values.user_email);    // ввод в поле емаил емаил юзера
        $(By.name("password")).sendKeys(Values.user_password);      // ввод в поле пароль пароль юзера
        $(By.className("btn-modal")).click();   // клик по кнопке
    }



    public void loginAdmin() {
        $(By.className("btn-yel")).click();  // клик по кнопке вход
        $(By.name("email")).sendKeys(Values.admin_email);       // ввод в поле емаил емаил администратора
        $(By.name("password")).sendKeys(Values.admin_password); // ввод в поле пароль пароль администратора
        $(By.className("btn-modal")).click();        // клик по кнопке
        GoogleAuthenticator gAuth = new GoogleAuthenticator();      // подключение  2 аутентификации
        int code = gAuth.getTotpPassword(Values.fa2_secret_key);    // считывания кода 2 аутентификации
        System.out.println("Code = " + code + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        $(By.id("one_time_password")).sendKeys(Integer.toString(code));  // ввод в нужное поле кода 2 аутентификации

        $(By.xpath("//form[@method='POST']//button")).click();      // клик по кнопке отправить
        if ($$(By.name("email")).size() != 0 ) {    // если код на двухфакторку еподошол пробуем авторизоватся еще раз
            $(By.name("email")).sendKeys(Values.admin_email);
            $(By.name("password")).sendKeys(Values.admin_password);
            $(By.className("btn-modal")).click();
            int code2 = gAuth.getTotpPassword(Values.fa2_secret_key);
            System.out.println("Code = " + code2 + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            $(By.id("one_time_password")).sendKeys(Integer.toString(code2));
            $(By.xpath("//form[@method='POST']//button")).click();
        }
        if ($$(By.name("email")).size() != 0 ) {        // если авторизация не удалась тест падает с выводом текста ошибки
            System.out.println( "2 раза введено неверно код провер двухфакторки");
            throw new Error();      // после етой команды тест падает
            }
    }


    public void logAutAdmin() {
        open("https://coins.dd-dev.club/");
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']//li[7]/a")).click();
    }

    public void logAutUser() {
        open("https://coins.dd-dev.club/");
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']//li[6]/a")).click();
    }


    public void createRequestRecoveryPassword() {
        $(By.className("btn-yel")).click();    // клик по кнопке вход
        $(By.xpath("//div[@class='form__ithem form_forgot']/a[2]")).click();    // клик по ссылке  Забыли пароль?
        $(By.name("email")).sendKeys(Values.userReset_email);   // ввод емайла для востановления пароля
        $(By.className("btn-modal")).click();    // нажатия кнопки Отправить
    }


    public void recsetPassword() {
        switchTo().window(1);       // переключения на 2 вкладку
        tempStr = "Df";
        tempInt = getRandomNumber(8000000,8999999);
        password = tempStr + tempInt;
        System.out.println(password);
        $(By.name("email")).sendKeys(Values.ukrnet_email);      // ввод емаийла
        $(By.name("password")).sendKeys(password);              // ввод сгенерированого рандомного пароля
        $(By.name("password_confirmation")).sendKeys(password); // подтверждения пароля
        $(By.xpath("//button[@class='btn-yel btn-modal']")).click();           // нажатия кнопки Отправить
    }
    public  void loginRessetPassword () {
        $(By.className("btn-yel")).click();
        $(By.name("email")).sendKeys(Values.ukrnet_email);
        $(By.name("password")).sendKeys(password);
        $(By.className("btn-modal")).click();
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']//li[5]/a")).click();
        if ($$(By.name("phone")).size() == 0) {
            System.out.println("Невозможно авторизоваться используя новый пароль");
            throw new Error();
        }
    }

}
