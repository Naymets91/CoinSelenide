
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
     PeriodPage periodPg = new PeriodPage();
     DenominationPage denominationPg = new DenominationPage();
     MaterialsPage materialsPg = new MaterialsPage();
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

    @Test (priority = 1)                                           // Тест двойной аутентификации
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    public void correct2FALogin() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
    }

    @Test  (priority=2)                                           // Добавить лот
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
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


    @Test  (priority=3)                              // Изменение лотав
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
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

    @Test (priority = 4)                                      // Удаление лотов
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
    public void deleteLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.randomEditLot();
        lotiPg.delete();
    }

    @Test  (priority = 5)                                         // Создание аукциона
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
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

    @Test (priority = 6)                                      // Удаление аукциона
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
    public void deleteAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.auctionAdd();
        auctionsPg.randomEditorAuction();
        auctionsPg.delete();
    }

    @Test (priority = 7)                                      // Изменение кредитного лимита , статус одобрено
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Кредитный лимит")
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

    @Test  (priority = 8)                                     // Изменение кредитного лимита , статус отменено
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Кредитный лимит")
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

    @Test  (priority = 9)                                     // Изменение кредитного лимита , удаления запроса
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Кредитный лимит")
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

    @Test (priority = 10)      // Востановления пароля через запрос забили пароль.
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Пароль")
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

    @Test (priority = 11)                                  // Изминение пароля через панель администратора
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Пароль")
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


    @Test (priority = 12)                                  //  Добавить много лотов с помощу кнопки дублировать лот
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
    public void  addManyLots(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.checkingExistencelots();
        lotiPg.addManyLoti(20);
    }

    @Test (priority = 13,enabled= false)                                  //  Старт предварительного аукциона
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
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

     @Test (priority = 14,enabled= false)  // ставки пользователей на предварительном аукционе
     @Epic(value = "Администратор")
     @Feature(value = "Админка => Пункт меню Аукцион")
     @Story(value = "Аукцион")
    public void BidsUsersPreliminaryAuction (){
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

    @Test (priority = 15)
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Пароль")
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

    @Test (priority = 16)
    @Epic(value = "Администратор")
    @Feature(value = "Учетная запись")
    @Story(value = "Пароль")
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

    @Test (priority = 17)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Категория")
    public void addCategory() {     // Создание новой катеории
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.clickButtonAddCategoty();
        categoryPg.fillCategory();
        categoryPg.clickButtonSaveCategoty();
        categoryPg.equalsAddEditCategory();
    }

    @Test (priority = 18)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Категория")
    public void editCategory() {            // Редактирование новой катеории
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.clickButtonEditCategoty();
        categoryPg.fillCategory();
        categoryPg.clickButtonUpdateCategoty();
        categoryPg.equalsAddEditCategory();
    }

    @Test (priority = 19)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Категория")
    public void usageNewCategor() {     // Проверка использования новой категории
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        categoryPg.usageNewCategory();
    }

    @Test (priority = 20)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Категория")
    public void delCategor() {      // Удаление новой категории
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.delCategory();
        categoryPg.equalsDelCategory();
    }

    @Test (priority = 21)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Период")
    public  void addPeriod() {      // Создание нового периода
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.clickButtonAddPeriod();
        periodPg.fillPeriod();
        periodPg.clickButtonSavePeriod();
        periodPg.equalsAddEditPeriod();
    }

    @Test (priority = 22)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Период")
    public  void editPeriod() {         // Редактирование нового периода
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.clickButtonEditPeriod();
        periodPg.fillPeriod();
        periodPg.clickButtonUpdatePeriod();
        periodPg.equalsAddEditPeriod();
    }

    @Test (priority = 23)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Период")
    public void usageNewPeriod() {      // Использование нового периода
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        periodPg.usageNewPeriod();
    }

    @Test (priority = 24)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Период")
    public void delPeriod() {       // Удаление нового периода
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.delPeriod();
        periodPg.equalsDelPeriod();
    }

    @Test (priority = 25)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Номинал")
    public void addDenomination() {         // Создание нового номинала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.clickButtonAddDenomination();
        denominationPg.fillDenomination();
        denominationPg.clickButtonSaveDenomination();
        denominationPg.equalsAddEditDenomination();
    }

    @Test (priority = 26)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Номинал")
    public void editDenomination() {        // Редактирование нового номинала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.clickButtonEditDenomination();
        denominationPg.fillDenomination();
        denominationPg.clickButtonUpdateDenomination();
        denominationPg.equalsAddEditDenomination();
    }
    @Test (priority = 27)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Номинал")
    public void useDenomination() {     // Использвание нового номинала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.usageNewDenomination();
        mainPg.gotoAdminPanel();
    }

    @Test (priority = 28)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Номинал")
    public void delDenomination() {     // Удаление нового номинала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.delDenomination();
        denominationPg.equalsDenomination();
    }


    @Test (priority = 29)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void addMaterials() {         // Создание нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.clickButtonAddMaterials();
        materialsPg.fillMaterials();
        materialsPg.clickButtonSaveMaterials();
        materialsPg.equalsAddEditMaterials();
    }

    @Test (priority = 30)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void editMaterials() {        // Редактирование нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        materialsPg.clickButtonEditMaterials();
        materialsPg.fillMaterials();
        materialsPg.clickButtonUpdateMaterials();
        materialsPg.equalsAddEditMaterials();
    }
    @Test (priority = 31)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void useMaterials() {     // Использвание нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        materialsPg.usageNewMaterials();
        mainPg.gotoAdminPanel();
    }

    @Test (priority = 32)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void delMaterials() {     // Удаление нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        materialsPg.delMaterials();
        materialsPg.equalsMaterials();
    }


    @Test (priority = 33)
    @Epic(value = "Пользователь")
    @Feature(value = "Лоты")
    @Story(value ="Избранне")   // Добавление, удаление лотов на странице аукциона
    public void addDelFavoritesPageAuctions(){
    loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
    mainPg.gotoAuction();
    auctionsPg.randomAddFavorites();
    auctionsPg.equalsAddFavorites();
    auctionsPg.delAddFavorites();
    auctionsPg.equalsDellFavorites();
    }

    @Test (priority = 34)
    @Epic(value = "Пользователь")
    @Feature(value = "Лоты")
    @Story(value ="Избранне")
    public void addDelFavoritesPageFavorites(){     // Добавление, удаление лотов на странице Избранные лоты
    loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
    mainPg.gotoAuction();
    auctionsPg.randomAddFavorites();
    auctionsPg.equalsAddFavorites();
    mainPg.goFavoritesPage();
    auctionsPg.equalsAddFavoritesPage();
    auctionsPg.delAddFavoritesPageFavorites();
    auctionsPg.equalsDellFavoritesPageFavorites();
    }
    @Test (priority = 35)
    @Epic(value = "Пользователь")
    @Feature(value = "Лоты")
    @Story(value ="Избранне")   // Добавление, удаление много лотов на странице аукциона
    public void addDelManyFavorites(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction();
        auctionsPg.randomAddManyFavorites();
        auctionsPg.equalsAddManyFavorites();
        auctionsPg.refreshPage();
        auctionsPg.equalsAddManyFavorites();
        auctionsPg.delAddManyFavorites();
        auctionsPg.equalsDellManyFavorites();
    }

    @Test (priority = 36)
    @Epic(value = "Пользователь")
    @Feature(value = "Лоты")
    @Story(value ="Избранне")   // Сравнение цен на странице аукциона и странице избранные лоты
    public void equalsPricePageAuctionPageFavorites(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction();
        auctionsPg.randomAddFavorites();
        auctionsPg.equalsAddFavorites();
        mainPg.goFavoritesPage();
        auctionsPg.equalsAddFavoritesPage();
        auctionsPg.equalsPriceFavoritesPage();
        auctionsPg.delAddFavoritesPageFavorites();
        auctionsPg.equalsDellFavoritesPageFavorites();
    }
//    @Test (priority = 50, enabled= false)
//    @Epic(value = "Администратор")
//    @Feature(value = "Админка => Пункт меню настройка")
//    @Story(value = "Катигория")
//    public void addEditDelCategory() {
//        loginPg.loginAdmin();
//        mainPg.gotoAdminPanel();
//        adminPanelPg.category();
//        categoryPg.clickButtonAddCategoty();
//        categoryPg.fillCategory();
//        categoryPg.clickButtonSaveCategoty();
//        categoryPg.equalsAddEditCategory();
//        categoryPg.clickButtonEditCategoty();
//        categoryPg.fillCategory();
//        categoryPg.clickButtonUpdateCategoty();
//        categoryPg.equalsAddEditCategory();
//        categoryPg.usageNewCategory();
//        mainPg.gotoAdminPanel();
//        adminPanelPg.category();
//        categoryPg.delCategory();
//        categoryPg.equalsDelCategory();
//    }

//    @Test (priority = 51, enabled= false)
//    @Epic(value = "Администратор")
//    @Feature(value = "Админка => Пункт меню настройка")
//    @Story(value = "Период")
//    public  void addEditUseDelPeriod() {
//        loginPg.loginAdmin();
//        mainPg.gotoAdminPanel();
//        adminPanelPg.period();
//        periodPg.clickButtonAddPeriod();
//        periodPg.fillPeriod();
//        periodPg.clickButtonSavePeriod();
//        periodPg.equalsAddEditPeriod();
//        periodPg.clickButtonEditPeriod();
//        periodPg.fillPeriod();
//        periodPg.clickButtonUpdatePeriod();
//        periodPg.equalsAddEditPeriod();
//        periodPg.usageNewPeriod();
//        mainPg.gotoAdminPanel();
//        adminPanelPg.period();
//        periodPg.delPeriod();
//        periodPg.equalsDelPeriod();
//    }
//    @Test (priority = 52, enabled= false)
//    @Epic(value = "Администратор")
//    @Feature(value = "Админка => Пункт меню настройка")
//    @Story(value = "Номинал")
//    public void addEditUseDelDenomination() {
//        loginPg.loginAdmin();
//        mainPg.gotoAdminPanel();
//        adminPanelPg.denomination();
//        denominationPg.clickButtonAddDenomination();
//        denominationPg.fillDenomination();
//        denominationPg.clickButtonSaveDenomination();
//        denominationPg.equalsAddEditDenomination();
//        denominationPg.clickButtonEditDenomination();
//        denominationPg.fillDenomination();
//        denominationPg.clickButtonUpdateDenomination();
//        denominationPg.equalsAddEditDenomination();
//        denominationPg.usageNewDenomination();
//        mainPg.gotoAdminPanel();
//        adminPanelPg.denomination();
//        denominationPg.delDenomination();
//        denominationPg.equalsDenomination();
//    }

    @AfterMethod
    public void after() {
        closeWebDriver();
    }
}
