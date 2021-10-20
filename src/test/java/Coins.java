
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

    @Test (priority = 1, groups = "regress")                                           // Тест двойной аутентификации
    @Epic(value = "Регрес")
    public void correct2FALogin() {
        loginPg.loginAdmin();
        mainPg.equals2fa();
    }
    @Test (priority = 2, groups = "regress")                                           // некоректный Тест двойной аутентификации
    @Epic(value = "Регрес")
    public void incorrect2FALogin() {
        loginPg.loginUser(Values.admin_email, Values.admin_password);
        loginPg.incorrect2fa();
    }
    @Test (priority = 3, groups = "regress")                    // Регистрация с пустыми данными
    @Epic(value = "Регрес")
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
    @Test (priority = 4, groups = "regress")  // Регистрация пользователя и удаления через запрос в профиле клиента добавить !!!!!!!!!!!!!!!проверку ввведеного при регистрации и кабинете!!!!!!!!!
    @Epic(value = "Регрес")
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

    @Test (priority = 5, groups = "regress")           // Регистрация пользователя с заполнениям другого адреса доставки и удаления администратором
    @Epic(value = "Регрес")
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
    @Test (priority = 6, groups = "regress")      // Восстановления пароля через запрос забили пароль.
    @Epic(value = "Регрес")
    public void recoveryPassword() {
        loginPg.createRequestRecoveryPassword();
        openUkrnetPage();
        ukrnetPg.loginUkrnet(Values.ukrnet_email, Values.ukrnet_password);
        ukrnetPg.selectionLastLetterPassword();
        loginPg.resetPassword();
        loginPg.loginUser(Values.ukrnet_email,loginPg.getMyPass());
        loginPg.checkingUserAuthorization();
    }

    @Test (priority = 7, groups = "regress")                   // Изменение пароля через панель администратора
    @Epic(value = "Регрес")
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

    @Test (priority = 8, groups = "regress")      // Авторизация с невалидными данными
    @Epic(value = "Регрес")
    @Description(value = "Авторизация с невалидными данными")
    public void autorizationIvalidData() {
        loginPg.goToPageAutorization();
        loginPg.clickButtonLogin();
        loginPg.checkAuthtTextEror();
        loginPg.incorrectPassword();
        loginPg.clickButtonLogin();
        loginPg.checkAuthtTextEror();
    }

    @Test (priority = 9, groups = "regress")                                      // Изменение кредитного лимита , статус одобрено
    @Epic(value = "Регрес")
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

    @Test  (priority = 10, groups = "regress")                                     // Изменение кредитного лимита , статус отменено
    @Epic(value = "Регрес")
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

    @Test  (priority = 11, groups = "regress")                                     // Изменение кредитного лимита , удаления запроса
    @Epic(value = "Регрес")
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

    @Test (priority = 12, groups = "regress")                   // Избранное  Добавление, удаление лотов на странице аукциона
    @Epic(value = "Регрес")
    public void addDelFavoritesPageAuctions(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction49();
        auctionsPg.randomAddFavorites();
        auctionsPg.equalsAddFavorites();
        auctionsPg.delAddFavorites();
        auctionsPg.equalsDellFavorites();
    }

    @Test (priority = 13, groups = "regress")           // Избранное Добавление, удаление лота на странице Избранные лоты
    @Epic(value = "Регрес")
    public void addDelFavoritesPageFavorites(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction49();
        auctionsPg.randomAddFavorites();
        auctionsPg.equalsAddFavorites();
        mainPg.goFavoritesPage();
        auctionsPg.equalsAddFavoritesPage();
        auctionsPg.delAddFavoritesPageFavorites();
        auctionsPg.equalsDellFavoritesPageFavorites();
    }

    @Test (priority = 14, groups = "regress")           // Избранное Добавление, удаление много лотов на странице аукциона
    @Epic(value = "Регрес")
    public void addDelManyFavorites(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction49();
        auctionsPg.randomAddManyFavorites();
        auctionsPg.equalsAddManyFavorites();
        auctionsPg.refreshPage();
        auctionsPg.equalsAddManyFavorites();
        auctionsPg.delAddManyFavorites();
        auctionsPg.equalsDellManyFavorites();
    }

    @Test (priority = 15, groups = "regress")           // Сравнение цен на странице аукциона и странице избранные лоты
    @Epic(value = "Регрес")
    public void equalsPricePageAuctionPageFavorites(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction49();
        auctionsPg.randomAddFavorites();
        auctionsPg.equalsAddFavorites();
        mainPg.goFavoritesPage();
        auctionsPg.equalsAddFavoritesPage();
        auctionsPg.equalsPriceFavoritesPage();
        auctionsPg.delAddFavoritesPageFavorites();
        auctionsPg.equalsDellFavoritesPageFavorites();
    }
    





    @Test  (priority=3)                                           // Добавить лот
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
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


    @Test  (priority=4)                              // Изменение лотав  ПЕРЕРОБИТИ
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
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

    @Test (priority = 5)                                      // Удаление лотов
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Лоты")
    public void deleteLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.loti();
        lotiPg.delete();
        lotiPg.findDelLot();
    }

    @Test  (priority = 6)                                         // Создание аукциона
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
    public void createAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.buttonCreateAuction();
        auctionsPg.fillAuction(true);
        auctionsPg.buttonSave();
        auctionsPg.findAuction();
    }

    @Test  (priority = 7)                                         // Редактирование аукциона
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
    public void editAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.buttonEditAuction();
        auctionsPg.fillAuction(false);
        auctionsPg.buttonSave();
        auctionsPg.findAuction();
    }

    @Test (priority = 8)                                      // Удаление аукциона
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
    public void deleteAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.delete();
        auctionsPg.findDelAuction();
    }

    @Test (priority = 14,enabled= false)                                  //  Добавить много лотов с помощу кнопки дублировать лот  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ1!!!!!
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

    @Test (priority = 15,enabled= false)                                  //  Старт предварительного аукциона  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ и добавить!!!!!
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню Аукцион")
    @Story(value = "Аукцион")
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

     @Test (priority = 16,enabled= false)  // ставки пользователей на предварительном аукционе  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ и добавить!!!!!
     @Epic(value = "Администратор")
     @Feature(value = "Админка => Пункт меню Аукцион")
     @Story(value = "Аукцион")
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

   @Test (priority = 19)
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

    @Test (priority = 20)
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

    @Test (priority = 21)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Категория")
    public void usageNewCategor() {     // Проверка использования новой категории
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        categoryPg.usageNewCategory();
    }

    @Test (priority = 22)
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

    @Test (priority = 23)
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

    @Test (priority = 24)
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

    @Test (priority = 25)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Период")
    public void usageNewPeriod() {      // Использование нового периода
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        periodPg.usageNewPeriod();
    }

    @Test (priority = 26)
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

@Test(priority = 27 , groups={"test"})
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

   @Test (priority = 28, groups={"test"})
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
    @Test (priority = 29, groups={"test"})
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Номинал")
    public void useDenomination() {     // Использвание нового номинала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        denominationPg.usageNewDenomination();
    }

    @Test (priority = 30, groups={"test"})
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


    @Test (priority = 31)
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

    @Test (priority = 32)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void editMaterials() {        // Редактирование нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.clickButtonEditMaterials();
        materialsPg.fillMaterials();
        materialsPg.clickButtonUpdateMaterials();
        materialsPg.equalsAddEditMaterials();
    }

    @Test (priority = 33)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void useMaterials() {     // Использвание нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        materialsPg.usageNewMaterials();
    }

    @Test (priority = 34)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Материалы")
    public void delMaterials() {     // Удаление нового Материала
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.delMaterials();
        materialsPg.equalsMaterials();
    }



    @Test (priority = 39)
    @Epic(value = "Администратор")
    @Feature(value = "Информация")
    @Story(value ="Новости")   // Создание новости
    public void addNews(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.clickButtonAddNews();
        newsPg.fillNews();
        newsPg.clickButtonSaveNews();
        newsPg.equalsAddEditNews();
    }
    @Test (priority = 40)
    @Epic(value = "Администратор")
    @Feature(value = "Информация")
    @Story(value ="Новости")   // Редактирование новости
    public void editNews(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.clickButtonEditNews();
        newsPg.fillNews();
        newsPg.clickButtonSaveNews();
        newsPg.equalsAddEditNews();
    }

    @Test (priority = 41)
    @Epic(value = "Администратор")
    @Feature(value = "Информация")
    @Story(value ="Новости")   // Отображения новости на всех языках
    public void newsDisplayInLanguages(){
        loginPg.loginUser(Values.user1_email,Values.user1_password);
        mainPg.gotoNews();
        newsPg.openLastAddNews();
        newsPg.equalsInLanguages();
    }

    @Test (priority = 42)
    @Epic(value = "Администратор")
    @Feature(value = "Админка => Пункт меню настройка")
    @Story(value = "Новости")  // Удаление новой новости
    public void delNews() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.delNews();
        newsPg.equalsDelNews();
    }


    @Test (priority = 102)
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
