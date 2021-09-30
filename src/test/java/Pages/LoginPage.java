package Pages;

import Config.Values;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class LoginPage extends Page {

    boolean tempBool;

    StringBuilder tempStrB;

    String tempStr;
    Integer tempInt;

    String password;

    List<String> beforeReg = new ArrayList<>();
    List<String> afterReg = new ArrayList<>();

    public void loginUserUkrnet() {
        $(By.className("btn-yel")).click();         // клик по кнопке вход
        $(By.name("email")).sendKeys(Values.user1_email);    // ввод в поле емаил емаил юзера
        $(By.name("password")).sendKeys(Values.user1_password);      // ввод в поле пароль пароль юзера
        $(By.className("btn-modal")).click();   // клик по кнопке
    }
    @Step("Авторизация пользователя")
    public void loginUser(String user_email, String user_password) {
        $(By.xpath("//div[@class='nav-lang']/../a[2]")).click();         // клик по кнопке вход
        $(By.name("email")).sendKeys(user_email);    // ввод в поле емаил емаил юзера
        $(By.name("password")).sendKeys(user_password);      // ввод в поле пароль пароль юзера
        $(By.className("btn-modal")).click();   // клик по кнопке
    }

    @Step("Авторизация администратором")
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

    @Step("Выход из учетной записи пользователя")
    public void logAutUser() {
        System.out.println(Values.homePage);
        open(Values.homePage);
        openHomePage(); // открить стартовую страницу
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click(); // клик на личный кабинет
        $(By.xpath("//ul[@class='-visible']//li[6]/a")).click();        // клик по пункту меню выход
    }

@Step ("Pапроса для сброса пароля")
    public void createRequestRecoveryPassword() {
        $(By.xpath("//div[@class='nav-lang']/../a[2]")).click();    // клик по кнопке вход
        $(By.xpath("//div[@class='form__ithem form_forgot']/a[2]")).click();    // клик по ссылке  Забыли пароль?
        $(By.name("email")).sendKeys(Values.userReset_email);   // ввод емайла для востановления пароля
        $(By.className("btn-modal")).click();    // нажатия кнопки Отправить
    }

@Step ("Генерация нового пароля ")
    public String recsetPassword() {
        switchTo().window(1);       // переключения на 2 вкладку
        tempStr = "Df";
        tempInt = getRandomNumber(8000000, 8999999);
        password = tempStr + tempInt;
        System.out.println(password);
        $(By.name("password")).sendKeys(password);              // ввод сгенерированого рандомного пароля
        $(By.name("password_confirmation")).sendKeys(password); // подтверждения пароля
        $(By.xpath("//button[@class='btn-yel btn-modal']")).click();// нажатия кнопки Отправить
        tempStrPage = password;
    System.out.println("tempStrPage " + tempStrPage);
        return password;
    }

    public String getMyPass(){
        return this.password;
    }
@Step ("Проверка авторизации используя повый пароль")
    public void checkingUserAuthorization() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']//li[5]/a")).click();
        if ($$(By.name("phone")).size() == 0) {
            System.out.println("Невозможно авторизоваться используя новый пароль");
            throw new Error();
        }

    }
    @Step("Регистрация пользователя")
    public void register() {        // регистрация нового пользователя
        $(By.xpath("//div[@class='nav-lang']/../a[1]")).click();  // клик по кнопке регистрация

        setAllurStrRandom("first_name", "Имя = "); // ввод в поле имя
        setAllurStrRandom("last_name", "Фамилия = "); // ввод в поле фамилия
        setAllurStrRandom("company", "Компания = "); // ввод в поле компания
        setAllurStr_IntRandom("vat", "Ват = ",5000,10000);  // ввод в поле ват
        setAllurStrMail("email", "email = "); // ввод в поле email
        setAllurStrSave_IntRandom("phone", "телефон = ",10000000,999999999); // ввод в поле номер телефона

        $(By.id("country_id-selectized")).click();
        randomSelect("country_id", 2);    // ввод в поле Страна

        setAllurStrSave("index","индекс =","22400");// ввод в поле индекс
        setAllurStrSave("region","регион =","Винницкий");// ввод в поле регион

        $(By.name("city")).sendKeys("Vinnitsa");  // ввод в поле город
        $(By.name("address")).sendKeys("Vinnitsa");  // ввод в поле Адрес для выставления счетов

        randomSelect("delivery_id",  1);  // ввод в поле Способ доставки

        $(By.name("shipping_address")).sendKeys(randomStringEE(7) + " "+ "35");  // ввод в поле Адрес доставки
        $(By.name("recommendations")).sendKeys(randomStringEE(5) );  // ввод в поле рекомендации
        $(By.name("max_credit_limit")).sendKeys("555" );  // ввод в поле кредитный лимит
        $(By.name("password")).sendKeys(Values.userRegDelPassword);  // ввод в поле пароль
        $(By.name("password_confirmation")).sendKeys(Values.userRegDelPassword);  // ввод в поле пароль

        $(By.xpath("//input[@id='is_confirmed_policy']")).click();// клик чекбокс подтверждения политики
        $(By.xpath("//input[@id='is_confirmed_agreement']")).click();// клик чекбокс подтверждения правил

        parsReg();
        $(By.xpath("//div[@class='form__cont sing-form']//button")).click();
        $(By.xpath("//div[@class='alert alert-success']//p")).click();
    }


    private void setAllurStr_IntRandom(String by_Name, String contentName, Integer a1, Integer a2 ) {
        tempStr = String.valueOf(randomStringEE(2)) + getRandomNumber(a1,a2);
        $(By.name(by_Name)).sendKeys(tempStr);
        Allure.addAttachment("Результат", "application/json", contentName + String.valueOf(tempStr));
    }
    private void setAllurStrSave(String by_Name, String contentName, String sendKeys) {
        tempStr = sendKeys;
        $(By.name(by_Name)).sendKeys(tempStr);
        Allure.addAttachment("Результат", "application/json", contentName + tempStr);
    }
    private void setAllurStrSave_IntRandom(String by_Name, String contentName, Integer a1, Integer a2 ) {
        tempStr = "+380" + getRandomNumber(a1,a2);
        $(By.name(by_Name)).sendKeys(tempStr);
        Allure.addAttachment("Результат", "application/json", contentName + String.valueOf(tempStr));
    }

    private void setAllurStrMail(String by_Name, String contentName ) {
        tempStr = Values.userRegDelMail;
        $(By.name(by_Name)).sendKeys(tempStr);
        Allure.addAttachment("Результат", "application/json", contentName + tempStr);
    }

    private void setAllurStrRandom(String by_Name, String contentName) {
        tempStrB = randomStringEE(7);
        $(By.name(by_Name)).sendKeys(tempStrB);
        Allure.addAttachment("Результат", "application/json", contentName + String.valueOf(tempStrB));
    }
    private void setAllurSelectRandom(String by_Name, String contentName) {
        tempStrB = randomStringEE(7);
        $(By.name(by_Name)).sendKeys(tempStrB);
        Allure.addAttachment("Результат", "application/json", contentName + String.valueOf(tempStrB));
    }
    private void parsReg() {

    }

    @Step("Создать запрос на удаления пользователя")
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

    public void randomSelect(String nameId, int startRandomNumber) {
        $(By.id(nameId+"-selectized")).click();
        size = $$(By.xpath("//select[@id='" + nameId + "']/..//div[@class='selectize-dropdown-content']/div/span")).size();
        size = getRandomNumber(startRandomNumber, size);
        $(By.xpath("//select[@id='" + nameId + "']/..//div[@class='selectize-dropdown-content']/div[" + size + "]/span")).click();

    }
    @Step("Проверка авторизации удаленным пользователем")
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
    @Step("Переход в профиль")
    public void goToProfile() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click(); // клик на личный кабинет
        $(By.xpath("//ul[@class='-visible']//li[5]/a")).click();        // клик по пункту меню профиль
        tempStr = $(By.xpath("//*[@id='name']")).getAttribute("value"); // парсится и записуется значения имени и фамилии
        System.out.println(tempStr);
    }

    public void incorrect2fa() {
        $(By.id("one_time_password")).sendKeys("111111");
        $(By.xpath("//form[@method='POST']//button")).click();      // клик по кнопке отправить
        if ($$(By.name("email")).size() != 0) {
            Allure.attachment("Результат", "Успешно");
            System.out.println("успешно");
        }else {
            Allure.attachment("Результат", " !Не успешно");
            throw new Error();
        }
    }
}