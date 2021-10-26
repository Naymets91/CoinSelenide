
import Config.JsonRead;
import Config.Values;
import Pages.*;


import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import listeners.AllureOnFailListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;

@Listeners({AllureOnFailListener.class})
@Title("FlyTest Test Suite")
public class Regress extends Page {
     AuctionsPage auctionsPg = new AuctionsPage();
     LotiPagePage lotiPg = new LotiPagePage();
     AdminPanelPage adminPanelPg = new AdminPanelPage();
     MainPage mainPg = new MainPage();
     LimitCashPage limitCashPg = new LimitCashPage();
     LoginPage loginPg = new LoginPage();
     UkrnetPage ukrnetPg = new UkrnetPage();
     EditUserPage editUserPg = new EditUserPage();
     CategoryPage categoryPg = new CategoryPage();
     PeriodPage periodPg = new PeriodPage();
     DenominationPage denominationPg = new DenominationPage();
     MaterialsPage materialsPg = new MaterialsPage();
     NewsPage newsPg = new NewsPage();
     InvoicePage invoicePg = new InvoicePage();
     JsonRead jsonRead = new JsonRead();

     @BeforeClass
     public void red(){
         jsonRead.read();
     }

    @BeforeMethod
    public void setUp() {
        com.codeborne.selenide.Configuration.browser = "chrome";      //браузер для тестов
        System.setProperty("webdriver.chrome.driver", "chromedriver\\nux\\chromedriver");
        com.codeborne.selenide.Configuration.timeout = 15000;   //максимальный интервал ожидания вебэлементов в милисекундах
        ChromeOptions options = new ChromeOptions();  //создать обьект для установки опций браузера хром
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false); // вспливающие окна
        prefs.put("password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});    // Отключить расширения режима разработчика , отключить панель автоматизации
        options.addArguments("--no-sandbox");   // для докера
        options.addArguments("--disable-dev-shm-usage"); // для докера
        WebDriver myWebDriver = new ChromeDriver(options);
        WebDriverRunner.setWebDriver(myWebDriver);
        myWebDriver.manage().window().maximize();
        openHomePage();
        mainPg.SwitchLanguageRu();
    }
    //***********************************************//
    //                 REGRESS                       //
    //***********************************************//

    @Test (priority = 1, groups = {"regress"})                                           // Тест двойной аутентификации
    @Epic(value = "Регресс")
    public void correct2FALogin() {
        loginPg.loginAdmin();
        mainPg.equals2fa();
    }
    @Test (priority = 2, groups = {"regress"})                                           // некоректный Тест двойной аутентификации
    @Epic(value = "Регресс")
    public void incorrect2FALogin() {
        loginPg.loginUser(Values.admin_email, Values.admin_password);
        loginPg.incorrect2fa();
    }
    @Test (priority = 3, groups = {"regress"})                    // Регистрация с пустыми данными
    @Epic(value = "Регресс")
    @Description(value = "Регистрация с пустыми данными")
    public void registerEmptyData() {
        loginPg.goToPageRegisteration();
        loginPg.checkWithoutContractPolicy();
        loginPg.clickPolicy();
        loginPg.checkPoliticsClick();
        loginPg.clickPolicy();
        loginPg.clickContract();
        loginPg.checkContractClick();
        loginPg.clickPolicy();
        loginPg.checkContractPolicyClick();
        loginPg.clickButtonRegistration();
        loginPg.checkFindTextEror();
    }
    @Test (priority = 4, groups = {"regress"})  // Регистрация пользователя и удаления через запрос в профиле клиента добавить !!!!!!!!!!!!!!!проверку ввведеного при регистрации и кабинете!!!!!!!!!
    @Epic(value = "Регресс")
    @Description(value = "Регистрация пользователя и удаления через запрос в профиле клиента")
    public void RegisterDel(){
        loginPg.goToPageRegisteration();
        loginPg.fillRegistrateDate();
        loginPg.clickButtonRegistration();
        // loginPg.pars
        openUkrnetPage();
        ukrnetPg.loginUkrnet(Values.userRegDelMail, Values.userRegDelPassword);
        ukrnetPg.selectionLastLetterRegister();
        switcToWindowsTab(1);
        loginPg.logAutUser();
        loginPg.loginUser(Values.userRegDelMail, Values.userRegDelPassword);
        loginPg.createDelet();
        // loginPg.pars
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.requestDelUser();
        adminPanelPg.delUser(loginPg.parsName());
        loginPg.logAutAdmin();
        loginPg.checkLogin(Values.userRegDelMail,Values.userRegDelPassword);
    }

    @Test (priority = 5, groups = {"regress"})           // Регистрация пользователя с заполнениям другого адреса доставки и удаления администратором
    @Epic(value = "Регресс")
    @Description(value = "Регистрация пользователя с заполнениям другого адреса доставки и удаления администратором")
    public  void RegisterAnotherAddressDelAdmin (){
        loginPg.goToPageRegisteration();
        loginPg.fillRegistrateDate();
        loginPg.fillAnotherAdres();
        // loginPg.pars
        loginPg.clickButtonRegistration();
        openUkrnetPage();
        ukrnetPg.loginUkrnet(Values.userRegDelMail, Values.userRegDelPassword);
        ukrnetPg.selectionLastLetterRegister();
        switcToWindowsTab(1);
        loginPg.logAutUser();
        loginPg.loginUser(Values.userRegDelMail, Values.userRegDelPassword);
        // loginPg.pars
        loginPg.goToProfile();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.User();
        adminPanelPg.DelUserAdmin(loginPg.parsName());
        loginPg.logAutAdmin();
        loginPg.checkLogin(Values.userRegDelMail,Values.userRegDelPassword);
    }
    @Test (priority = 6, groups = {"regress"})      // Восстановления пароля через запрос забили пароль.
    @Epic(value = "Регресс")
    public void recoveryPassword() {
        loginPg.createRequestRecoveryPassword();
        openUkrnetPage();
        ukrnetPg.loginUkrnet(Values.ukrnet_email, Values.ukrnet_password);
        ukrnetPg.selectionLastLetterPassword();
        loginPg.resetPassword();
        loginPg.loginUser(Values.ukrnet_email,loginPg.getMyPass());
        loginPg.checkingUserAuthorization();
    }

    @Test (priority = 7, groups = {"regress"})                   // Изменение пароля через панель администратора
    @Epic(value = "Регресс")
    public void editPasswordUser() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.editPassUser();
        adminPanelPg.searchUser();
        editUserPg.editPassword();
        loginPg.logAutAdmin();
        loginPg.loginUserUkrnet();
        loginPg.checkingUserAuthorization();
    }

    @Test (priority = 8, groups = {"regress"})      // Авторизация с невалидными данными
    @Epic(value = "Регресс")
    @Description(value = "Авторизация с невалидными данными")
    public void autorizationIvalidData() {
        loginPg.goToPageAutorization();
        loginPg.clickButtonLogin();
        loginPg.checkAuthtTextEror();
        loginPg.incorrectPassword();
        loginPg.clickButtonLogin();
        loginPg.checkAuthtTextEror();
    }

    @Test (priority = 9, groups = {"regress"})                                      // Изменение кредитного лимита , статус одобрено
    @Epic(value = "Регресс")
    public void editLimitStatusApproved() {
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoProfile();
        limitCashPg.editLimitCash();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.limitCahEdit();
        limitCashPg.searchUser();
        limitCashPg.userEdit();
        limitCashPg.acceptNewCash();
        loginPg.logAutAdmin();
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCash();
    }

    @Test  (priority = 10, groups = {"regress"})                                     // Изменение кредитного лимита , статус отменено
    @Epic(value = "Регресс")
    public void editLimitStatusReject() {
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoProfile();
        limitCashPg.editLimitCash();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.limitCahEdit();
        limitCashPg.searchUser();
        limitCashPg.userEdit();
        limitCashPg.rejectNewCash();
        loginPg.logAutAdmin();
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCashReject();
    }

    @Test  (priority = 11, groups = {"regress"})                                     // Изменение кредитного лимита , удаления запроса
    @Epic(value = "Регресс")
    public void editLimitDel() {
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoProfile();
        limitCashPg.editLimitCash();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.limitCahEdit();
        limitCashPg.searchUser();
        limitCashPg.userDel();
        loginPg.logAutAdmin();
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCashReject();
    }






    @AfterMethod
    public void after() {
        closeWebDriver();
    }
}
