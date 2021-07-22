
import Config.Values;
import Pages.*;


import com.codeborne.selenide.WebDriverRunner;
import listeners.AllureOnFailListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.sleep;

@Listeners({AllureOnFailListener.class})
@Title("FlyTest Test Suite")
public class Coins extends Page {
     AuctionsPage auctionsPg = new AuctionsPage();
     LotiPagePage lotiPg = new LotiPagePage();
     AdminPanelPage adminPanelPg = new AdminPanelPage();
     MainPage mainPg = new MainPage();
     LimitCashPage limitCashPg = new LimitCashPage();
     LoginPage loginPg = new LoginPage();
     UkrnetPage ukrnetPg = new UkrnetPage();
     EditUserPage editUserPg = new EditUserPage();
     CategoryPage categoryPg = new CategoryPage();

    @BeforeMethod
    public void setUp() {
        com.codeborne.selenide.Configuration.browser = "chrome";      //браузер для тестов
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

    @Test (priority = 1)                                            // Тест двойной аутентификации
    public void correct2FALogin() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
    }

    @Test   (priority = 2)                                          // Добавить лот
    public void addLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.createLot();
        lotiPg.name(12, 15, 10);
        lotiPg.descrintion(20, 30, 25);
        lotiPg.sender();
        lotiPg.country();
        lotiPg.category();
        lotiPg.year();
        lotiPg.minPrice();
        lotiPg.startPrice();
        lotiPg.material();
        lotiPg.denomination();
        lotiPg.period();
        lotiPg.topLot();
        lotiPg.certification();
        lotiPg.maxSafety();
        lotiPg.reve();
        lotiPg.safety();
        lotiPg.parsLotAfter();
        lotiPg.buttonSave();
    }


    @Test        (priority = 2)                               // Изменение лотав
    public void editLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.randomEditLot();
        lotiPg.editLot();
        lotiPg.parsLotBefore();
        lotiPg.name(5, 10, 8);
        lotiPg.descrintion(15, 25, 15);
        lotiPg.sender();
        lotiPg.country();
        lotiPg.category();
        lotiPg.year();
        lotiPg.minPrice();
        lotiPg.startPrice();
        lotiPg.material();
        lotiPg.denomination();
        lotiPg.period();
        lotiPg.topLot();
        lotiPg.certification();
        lotiPg.maxSafety();
        lotiPg.reve();
        lotiPg.safety();
//        lotiPg.buttonSave();
//        lotiPg.editLot();
        lotiPg.parsLotAfter();
        lotiPg.equalsLot();

    }

    @Test                                       // Удаление лотов
    public void deleteLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.randomEditLot();
        lotiPg.delete();
    }

    @Test                                           // Создание аукциона
    public void createAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.auctionAdd();
        auctionsPg.create();
        auctionsPg.number();
        auctionsPg.name();
//       auctionsPg.descrintion(20,30,25);
        auctionsPg.dateStart("2021/06/22 20:00");
        auctionsPg.dateFinish("2021/06/26 23:00");
        auctionsPg.intervalEnd();
        auctionsPg.prolongation();
        auctionsPg.currency();
        auctionsPg.statys();
        auctionsPg.buttonSave();
    }

    @Test                                       // Удаление аукциона
    public void deleteAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.auctionAdd();
        auctionsPg.randomEditorAuction();
        auctionsPg.delete();
    }

    @Test                                       // Изменение кредитного лимита , статус одобрено
    public void editLimitStatusApproved() {
        loginPg.loginUser(Values.user1_email, Values.user1_password);
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
        loginPg.loginUser(Values.user1_email, Values.user1_password);
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCash();
    }

    @Test                                       // Изменение кредитного лимита , статус отменено
    public void editLimitStatusReject() {
        loginPg.loginUser(Values.user1_email, Values.user1_password);
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
        loginPg.loginUser(Values.user1_email, Values.user1_password);
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCashReject();
    }

    @Test                                       // Изменение кредитного лимита , удаления запроса
    public void editLimitDel() {
        loginPg.loginUser(Values.user1_email, Values.user1_password);
        mainPg.gotoProfile();
        limitCashPg.editLimitCash();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.limitCahEdit();
        limitCashPg.searchUser();
        limitCashPg.userDel();
        loginPg.logAutAdmin();
        loginPg.loginUser(Values.user1_email, Values.user1_password);
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCashReject();
    }

    @Test                                       // Востановления пароля через запрос забили пароль.
    public void recoveryPassword() {
        loginPg.createRequestRecoveryPassword();
        openUkrnetPage();
        ukrnetPg.loginUkrnet(Values.ukrnet_email, Values.ukrnet_password);
        ukrnetPg.selectionLastLetterPassword();
        loginPg.recsetPassword();
        loginPg.logAutUser();
        loginPg.loginUser(Values.ukrnet_email,loginPg.getMyPass());
        loginPg.checkingUserAuthorization();
    }

    @Test                                   // Изминение пароля через панель администратора
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

    @Test                                   //  Добавить много лотов с помощу кнопки дублировать лот
    public void  addManyLots(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.checkingExistencelots();
        lotiPg.addManyLoti(20);
    }

    @Test                                   //  Старт предварительного аукциона
    public  void startAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.auctionAdd();
//       auctionsPg.showMaxAuctions();
        auctionsPg.startAuctionDate();
        loginPg.logAutAdmin();
        loginPg.loginUser(Values.user1_email, Values.user1_password);
        auctionsPg.onlineAuction();
        auctionsPg.equalsStartAuction();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.auctionAdd();
        auctionsPg.stopAuction();
    }

     @Test
    public void BidsUsersPreliminaryAuction (){     // ставки пользователей на предварительном аукционе
         loginPg.loginAdmin();
         mainPg.gotoAdminPanel();
         adminPanelPg.auctions();
         adminPanelPg.auctionAdd();
         auctionsPg.startAuction();
         loginPg.logAutAdmin();
         loginPg.loginUser(Values.user1_email,Values.user1_password);
         auctionsPg.onlineAuction();
         auctionsPg.placeBet();
         loginPg.logAutUser();
         loginPg.loginUser(Values.user2_email,Values.user2_password);
         auctionsPg.onlineAuction();
         auctionsPg.placeBet();
         loginPg.logAutUser();
         loginPg.loginUser(Values.user1_email,Values.user1_password);
         auctionsPg.onlineAuction();
         auctionsPg.placeBet();
         loginPg.logAutUser();
         loginPg.loginAdmin();
         mainPg.gotoAdminPanel();
         adminPanelPg.auctions();
         adminPanelPg.auctionAdd();
         auctionsPg.stopAuction();
     }

    @Test
    @Description(value = "Регистрация пользователя и удаления через запрос в профиле клиента")
    public void RegisterDel(){ // Регистрация пользователя и удаления через запрос в профиле клиента
        loginPg.register();
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

    @Test
    @Description(value = "Регистрация пользователя и удаления администратором")
    public  void RegisterDelAdminDel (){ // Регистрация пользователя и удаления администратором
        loginPg.register();
        // loginPg.pars
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

    @Test
    public void addEdidDelCategory(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.addCategory();
        categoryPg.equalsAddCategory();
        categoryPg.editCategory();
        categoryPg.equalsEditCategory();
        categoryPg.usageNewCategory();
        categoryPg.delCategory();
        categoryPg.equalsDelCategory();
    }



    @AfterMethod
    public void after() {
        closeWebDriver();
    }
}
