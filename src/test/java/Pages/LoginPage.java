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
        $(By.className("btn-yel")).click();
        $(By.name("email")).sendKeys(Values.user_email);
        $(By.name("password")).sendKeys(Values.user_password);
        $(By.className("btn-modal")).click();
    }



    public void loginAdmin() {
        $(By.className("btn-yel")).click();
        $(By.name("email")).sendKeys(Values.admin_email);
        $(By.name("password")).sendKeys(Values.admin_password);
        $(By.className("btn-modal")).click();
        GoogleAuthenticator gAuth = new GoogleAuthenticator();
        int code = gAuth.getTotpPassword(Values.fa2_secret_key);
        System.out.println("Code = " + code + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        $(By.id("one_time_password")).sendKeys(Integer.toString(code));

        $(By.xpath("//form[@method='POST']//button")).click();
        if ($$(By.name("email")).size() != 0 ) {
            $(By.name("email")).sendKeys(Values.admin_email);
            $(By.name("password")).sendKeys(Values.admin_password);
            $(By.className("btn-modal")).click();
            int code2 = gAuth.getTotpPassword(Values.fa2_secret_key);
            System.out.println("Code = " + code2 + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            $(By.id("one_time_password")).sendKeys(Integer.toString(code2));
            $(By.xpath("//form[@method='POST']//button")).click();
        }
        if ($$(By.name("email")).size() != 0 ) {
            System.out.println( "2 раза введено неверно код провер двухфакторки");
            throw new Error();
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
