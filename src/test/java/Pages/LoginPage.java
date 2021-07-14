package Pages;

import Config.Values;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends Page {

    boolean tempBool;

    String tempStr;
    Integer tempInt;

    String password;

    public void loginUserUkrnet() {
        $(By.className("btn-yel")).click();         // клик по кнопке вход
        $(By.name("email")).sendKeys(Values.user1_email);    // ввод в поле емаил емаил юзера
        $(By.name("password")).sendKeys(Values.user1_password);      // ввод в поле пароль пароль юзера
        $(By.className("btn-modal")).click();   // клик по кнопке
    }

    public void loginUser(String user_email, String user_password) {
        $(By.xpath("//div[@class='nav-lang']/../a[2]")).click();         // клик по кнопке вход
        $(By.name("email")).sendKeys(user_email);    // ввод в поле емаил емаил юзера
        $(By.name("password")).sendKeys(user_password);      // ввод в поле пароль пароль юзера
        $(By.className("btn-modal")).click();   // клик по кнопке
    }


    public void loginAdmin() {
        $(By.xpath("//div[@class='nav-lang']/../a[2]")).click();  // клик по кнопке вход
        $(By.name("email")).sendKeys(Values.admin_email);       // ввод в поле емаил емаил администратора
        $(By.name("password")).sendKeys(Values.admin_password); // ввод в поле пароль пароль администратора
        $(By.className("btn-modal")).click();        // клик по кнопке
        GoogleAuthenticator gAuth = new GoogleAuthenticator();      // подключение  2 аутентификации
        int code = gAuth.getTotpPassword(Values.fa2_secret_key);    // считывания кода 2 аутентификации
        System.out.println("Code = " + code + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
        $(By.id("one_time_password")).sendKeys(Integer.toString(code));  // ввод в нужное поле кода 2 аутентификации

        $(By.xpath("//form[@method='POST']//button")).click();      // клик по кнопке отправить
        if ($$(By.name("email")).size() != 0) {    // если код на двухфакторку еподошол пробуем авторизоватся еще раз
            sleep(25000);
            $(By.name("email")).sendKeys(Values.admin_email);
            $(By.name("password")).sendKeys(Values.admin_password);
            $(By.className("btn-modal")).click();
            code = gAuth.getTotpPassword(Values.fa2_secret_key);
            System.out.println("Code2 = " + code + ", Time = " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            $(By.id("one_time_password")).sendKeys(Integer.toString(code));
            $(By.xpath("//form[@method='POST']//button")).click();
        }
        if ($$(By.name("email")).size() != 0) {        // если авторизация не удалась тест падает с выводом текста ошибки
            System.out.println("2 раза введено неверно код провер двухфакторки");
            throw new Error();      // после етой команды тест падает
        }
    }


    public void logAutAdmin() {
       openHomePage(); // открить стартовую страницу
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click(); // клик на личный кабинет
        $(By.xpath("//ul[@class='-visible']//li[7]/a")).click();              // клик по пункту меню выход
    }

    public void logAutUser() {
        openHomePage(); // открить стартовую страницу
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click(); // клик на личный кабинет
        $(By.xpath("//ul[@class='-visible']//li[6]/a")).click();        // клик по пункту меню выход
    }


    public void createRequestRecoveryPassword() {
        $(By.xpath("//div[@class='nav-lang']/../a[2]")).click();    // клик по кнопке вход
        $(By.xpath("//div[@class='form__ithem form_forgot']/a[2]")).click();    // клик по ссылке  Забыли пароль?
        $(By.name("email")).sendKeys(Values.userReset_email);   // ввод емайла для востановления пароля
        $(By.className("btn-modal")).click();    // нажатия кнопки Отправить
    }


    public void recsetPassword() {
        switchTo().window(1);       // переключения на 2 вкладку
        tempStr = "Df";
        tempInt = getRandomNumber(8000000, 8999999);
        password = tempStr + tempInt;
        System.out.println(password);
        $(By.name("email")).sendKeys(Values.ukrnet_email);      // ввод емаийла
        $(By.name("password")).sendKeys(password);              // ввод сгенерированого рандомного пароля
        $(By.name("password_confirmation")).sendKeys(password); // подтверждения пароля
        $(By.xpath("//button[@class='btn-yel btn-modal']")).click();           // нажатия кнопки Отправить
    }



    public void checkingUserAuthorization() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']//li[5]/a")).click();
        if ($$(By.name("phone")).size() == 0) {
            System.out.println("Невозможно авторизоваться используя новый пароль");
            throw new Error();
        }

    }

    public void register() {        // регистрация нового пользователя
        $(By.xpath("//div[@class='nav-lang']/../a[1]")).click();  // клик по кнопке регистрация
        $(By.name("first_name")).sendKeys(randomStringEE(7));  // ввод в поле имя
        $(By.name("last_name")).sendKeys(randomStringEE(7));  // ввод в поле фамилия
        $(By.name("company")).sendKeys(randomStringEE(7));  // ввод в поле компания
        $(By.name("vat")).sendKeys(randomStringEE(2) + "02555787");  // ввод в поле ват
        $(By.name("email")).sendKeys(Values.userRegDelMail);  // ввод в поле email
        $(By.name("phone")).sendKeys("+380980000058");  // ввод в поле номер телефона
        $(By.id("country_id-selectized")).click();
        randomSelect("country_id", "Страна", 2);    // ввод в поле Страна
        $(By.name("index")).sendKeys("22400");  // ввод в поле индекс
        $(By.name("region")).sendKeys("Винницкий");  // ввод в поле регион
        $(By.name("city")).sendKeys("Vinnitsa");  // ввод в поле город
        $(By.name("address")).sendKeys("Vinnitsa");  // ввод в поле Адрес для выставления счетов
         randomSelect("delivery_id", "Способ доставки", 1);  // ввод в поле Способ доставки
        $(By.name("shipping_address")).sendKeys(randomStringEE(7) + " "+ "35");  // ввод в поле Адрес доставки
        $(By.name("recommendations")).sendKeys(randomStringEE(5) );  // ввод в поле рекомендации
        $(By.name("max_credit_limit")).sendKeys("555" );  // ввод в поле кредитный лимит
        $(By.name("password")).sendKeys(Values.userRegDelPassword);  // ввод в поле пароль
        $(By.name("password_confirmation")).sendKeys(Values.userRegDelPassword);  // ввод в поле пароль
        $(By.id("rules_policy_confirmation")).click(); // клик чекбокс подтверждения правил
        $(By.xpath("//div[@class='form__cont sing-form']//button")).click(); // клик зарегистрироватся
    }

    public void createDelet() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click(); // клик на личный кабинет
        $(By.xpath("//ul[@class='-visible']//li[5]/a")).click();        // клик по пункту меню профиль
        tempStr = $(By.xpath("//*[@id='name']")).getAttribute("value");
        System.out.println(tempStr);
        $(By.xpath("//div[@class='wrapp__row row']//a")).click();        // клик по пункту удалить профиль
        $(By.xpath("//div[@class='message__btn-wrapp']/button[2]")).click();        // клик по  кнопке подтвердить
    }

    public String parsName() {
    String r = tempStr ;
    return r;
    }

    public void createDelet2() {
        $(By.xpath("//div[@class='wrapp__row row']//a")).click();        // клик по пункту удалить профиль
        $(By.xpath("//div[@class='message__btn-wrapp']/button[2]")).click();        // клик по  кнопке подтвердить
    }

    public void randomSelect(String nameId, String printName, int startRandomNumber) {
        $(By.id(nameId+"-selectized")).click();
        size = $$(By.xpath("//select[@id='" + nameId + "']/..//div[@class='selectize-dropdown-content']/div/span")).size();
        size = getRandomNumber(startRandomNumber, size);
        $(By.xpath("//select[@id='" + nameId + "']/..//div[@class='selectize-dropdown-content']/div[" + size + "]/span")).click();
//        temp = $(By.name(nameId)).getSelectedText();
//        System.out.println(printName + "     " + temp);
    }

    public void checkLogin (String user_email, String user_password) {
        $(By.xpath("//div[@class='nav-lang']/../a[2]")).click();         // клик по кнопке вход
        $(By.name("email")).sendKeys(user_email);    // ввод в поле емаил емаил юзера
        $(By.name("password")).sendKeys(user_password);      // ввод в поле пароль пароль юзера
        $(By.className("btn-modal")).click();   // клик по кнопке
        tempBool = finde(By.xpath("//div[@class='alert alert-danger']//li"));

        if (tempBool == true) {
            System.out.println("Пользователь удалился");
        } else {
            System.out.println("Пользователь не удалился");
            throw new Error();
        }

    }

    public void goToProfile() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click(); // клик на личный кабинет
        $(By.xpath("//ul[@class='-visible']//li[5]/a")).click();        // клик по пункту меню профиль
        tempStr = $(By.xpath("//*[@id='name']")).getAttribute("value"); // парсится и записуется значения имени и фамилии
        System.out.println(tempStr);
    }
}