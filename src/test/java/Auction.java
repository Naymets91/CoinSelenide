
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
public class Auction extends Page {
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
    //                 AUCTION                       //
    //***********************************************//

    @Test  (priority=36, groups = {"auction"})                                          // Добавить лот
    @Epic(value = "Аукцион")
    public void addLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.loti();
        lotiPg.buttonCreateLot();
        lotiPg.fillLot();
        lotiPg.parsLotAfter();
        lotiPg.buttonSave();
        lotiPg.findLot();
    }

    @Test  (priority=37, groups = {"auction"})                                  // Изменение лотав  ПЕРЕРОБИТИ
    @Epic(value = "Аукцион")
    public void editLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.loti();
        lotiPg.buttonEditLot();
        lotiPg.parsLotBefore();
        lotiPg.fillLot();
        lotiPg.parsLotAfter();
        lotiPg.buttonSave();
        lotiPg.equalsLot();
    }

    @Test (priority = 38, groups = {"auction"})                                 // Удаление лотов
    @Epic(value = "Аукцион")
    public void deleteLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.loti();
        lotiPg.delete();
        lotiPg.findDelLot();
    }

    @Test  (priority = 39, groups = {"auction"})                                 // Создание аукциона
    @Epic(value = "Аукцион")
    public void createAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.buttonCreateAuction();
        auctionsPg.fillAuction(true);
        auctionsPg.buttonSave();
        auctionsPg.findAuction();
    }

    @Test  (priority = 49, groups = {"auction"})                                 // Редактирование аукциона
    @Epic(value = "Аукцион")
    public void editAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.buttonEditAuction();
        auctionsPg.fillAuction(false);
        auctionsPg.buttonSave();
        auctionsPg.findAuction();
    }

    @Test (priority = 50, groups = {"auction"})                                  // Удаление аукциона
    @Epic(value = "Аукцион")
    public void deleteAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.delete();
        auctionsPg.findDelAuction();
    }

    @Test (priority = 51, groups = {"auction"}, enabled= false)                   //  Добавить много лотов с помощу кнопки дублировать лот  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ1!!!!!
    @Epic(value = "Аукцион")
    public void  addManyLots(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.checkingExistencelots();
        lotiPg.addManyLoti(20);
    }

    @Test (priority = 52, groups = {"auction"}, enabled= false)                   //  Старт предварительного аукциона  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ и добавить!!!!!
    @Epic(value = "Аукцион")
    public  void startAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
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
        auctionsPg.stopAuction();
    }

     @Test (priority = 53, groups = {"auction"}, enabled= false)  // ставки пользователей на предварительном аукционе  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ и добавить!!!!!
     @Epic(value = "Аукцион")
    public void BidsUsersPreliminaryAuction (){
         loginPg.loginAdmin();
         mainPg.gotoAdminPanel();
         adminPanelPg.auctions();
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
         auctionsPg.stopAuction();
     }
     
    @Test (priority = 102, groups = {"auction"})
    @Description(value = "")
    public void invoicesAdmincaCustomer() {
     loginPg.loginAdmin();
     mainPg.gotoAdminPanel();
     adminPanelPg.goToPageInvoices();
//     invoicePg.selectCustomer();
//     invoicePg.search("111");
     invoicePg.clickButtonEdit();
     invoicePg.checkFinalPriceHammer();
     invoicePg.checkFinalCommissionNotNDS();
     invoicePg.checkFinalCommission20NDS();
     invoicePg.checkFinalDelivery();
     }


    @AfterMethod
    public void after() {
        closeWebDriver();
    }
}
