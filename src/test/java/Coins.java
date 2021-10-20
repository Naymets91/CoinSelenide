
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
    //***********************************************//
    //                 REGRESS                       //
    //***********************************************//

    @Test (priority = 1, groups = {"regress"})                                           // Тест двойной аутентификации
    @Epic(value = "Регресс")
    public void correct2FALogin() {
        loginPg.loginAdmin();
        mainPg.equals2fa();
    }
    @Test (priority = 2, groups = "regress")                                           // некоректный Тест двойной аутентификации
    @Epic(value = "Регресс")
    public void incorrect2FALogin() {
        loginPg.loginUser(Values.admin_email, Values.admin_password);
        loginPg.incorrect2fa();
    }
    @Test (priority = 3, groups = "regress")                    // Регистрация с пустыми данными
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
    @Test (priority = 4, groups = "regress")  // Регистрация пользователя и удаления через запрос в профиле клиента добавить !!!!!!!!!!!!!!!проверку ввведеного при регистрации и кабинете!!!!!!!!!
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

    @Test (priority = 5, groups = "regress")           // Регистрация пользователя с заполнениям другого адреса доставки и удаления администратором
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
    @Test (priority = 6, groups = "regress")      // Восстановления пароля через запрос забили пароль.
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

    @Test (priority = 7, groups = "regress")                   // Изменение пароля через панель администратора
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

    @Test (priority = 8, groups = "regress")      // Авторизация с невалидными данными
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

    @Test (priority = 9, groups = "regress")                                      // Изменение кредитного лимита , статус одобрено
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

    @Test  (priority = 10, groups = "regress")                                     // Изменение кредитного лимита , статус отменено
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

    @Test  (priority = 11, groups = "regress")                                     // Изменение кредитного лимита , удаления запроса
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

    @Test (priority = 12, groups = "regress")                   // Избранное  Добавление, удаление лотов на странице аукциона
    @Epic(value = "Регресс")
    public void addDelFavoritesPageAuctions(){
        loginPg.loginUser(Values.user3_Limit_email, Values.user3_Limit_password);
        mainPg.gotoAuction49();
        auctionsPg.randomAddFavorites();
        auctionsPg.equalsAddFavorites();
        auctionsPg.delAddFavorites();
        auctionsPg.equalsDellFavorites();
    }

    @Test (priority = 13, groups = "regress")           // Избранное Добавление, удаление лота на странице Избранные лоты
    @Epic(value = "Регресс")
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
    @Epic(value = "Регресс")
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
    @Epic(value = "Регресс")
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

    //***********************************************//
    //                 SETTING                       //
    //***********************************************//

    @Test (priority = 16, groups = "settings")      // Создание новой катеории
    @Epic(value = "Настройки")
    public void addCategory() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.clickButtonAddCategoty();
        categoryPg.fillCategory();
        categoryPg.clickButtonSaveCategoty();
        categoryPg.equalsAddEditCategory();
    }

    @Test (priority = 17, groups = "settings")      // Редактирование новой катеории
    @Epic(value = "Настройки")
    public void editCategory() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.clickButtonEditCategoty();
        categoryPg.fillCategory();
        categoryPg.clickButtonUpdateCategoty();
        categoryPg.equalsAddEditCategory();
    }

    @Test (priority = 18, groups = "settings")      // Проверка использования новой категории
    @Epic(value = "Настройки")
    public void usageNewCategor() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        categoryPg.usageNewCategory();
    }

    @Test (priority = 19, groups = "settings")      // Удаление новой категории
    @Epic(value = "Настройки")
    public void delCategor() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.delCategory();
        categoryPg.equalsDelCategory();
    }

    @Test (priority = 20 , groups = "settings")     // Создание нового периода
    @Epic(value = "Настройки")
    public  void addPeriod() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.clickButtonAddPeriod();
        periodPg.fillPeriod();
        periodPg.clickButtonSavePeriod();
        periodPg.equalsAddEditPeriod();
    }

    @Test (priority = 21, groups = "settings")      // Редактирование нового периода
    @Epic(value = "Настройки")
    public  void editPeriod() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.clickButtonEditPeriod();
        periodPg.fillPeriod();
        periodPg.clickButtonUpdatePeriod();
        periodPg.equalsAddEditPeriod();
    }

    @Test (priority = 22, groups = "settings")      // Использование нового периода
    @Epic(value = "Настройки")
    public void usageNewPeriod() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        periodPg.usageNewPeriod();
    }

    @Test (priority = 23, groups = "settings")      // Удаление нового периода
    @Epic(value = "Настройки")
    public void delPeriod() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.delPeriod();
        periodPg.equalsDelPeriod();
    }

    @Test(priority = 24, groups = "settings")       // Создание нового номинала
    @Epic(value = "Настройки")
    public void addDenomination() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.clickButtonAddDenomination();
        denominationPg.fillDenomination();
        denominationPg.clickButtonSaveDenomination();
        denominationPg.equalsAddEditDenomination();
    }

    @Test (priority = 25, groups = "settings")      // Редактирование нового номинала
    @Epic(value = "Настройки")
    public void editDenomination() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.clickButtonEditDenomination();
        denominationPg.fillDenomination();
        denominationPg.clickButtonUpdateDenomination();
        denominationPg.equalsAddEditDenomination();
    }

    @Test (priority = 26, groups = "settings")      // Использвание нового номинала
    @Epic(value = "Настройки")
    public void useDenomination() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        denominationPg.usageNewDenomination();
    }

    @Test (priority = 27, groups = "settings")      // Удаление нового номинала
    @Epic(value = "Настройки")
    public void delDenomination() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.delDenomination();
        denominationPg.equalsDenomination();
    }

    @Test (priority = 28, groups = "settings")      // Создание нового Материала
    @Epic(value = "Настройки")
    public void addMaterials() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.clickButtonAddMaterials();
        materialsPg.fillMaterials();
        materialsPg.clickButtonSaveMaterials();
        materialsPg.equalsAddEditMaterials();
    }

    @Test (priority = 29, groups = "settings")      // Редактирование нового Материала
    @Epic(value = "Настройки")
    public void editMaterials() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.clickButtonEditMaterials();
        materialsPg.fillMaterials();
        materialsPg.clickButtonUpdateMaterials();
        materialsPg.equalsAddEditMaterials();
    }

    @Test (priority = 30, groups = "settings")      // Использвание нового Материала
    @Epic(value = "Настройки")
    public void useMaterials() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        materialsPg.usageNewMaterials();
    }

    @Test (priority = 31, groups = "settings")      // Удаление нового Материала
    @Epic(value = "Настройки")
    public void delMaterials() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.delMaterials();
        materialsPg.equalsMaterials();
    }

    @Test (priority = 32, groups = "settings")      // Создание новости
    @Epic(value = "Настройки")
    public void addNews(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.clickButtonAddNews();
        newsPg.fillNews();
        newsPg.clickButtonSaveNews();
        newsPg.equalsAddEditNews();
    }
    @Test (priority = 33, groups = "settings")      // Редактирование новости
    @Epic(value = "Настройки")
    public void editNews(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.clickButtonEditNews();
        newsPg.fillNews();
        newsPg.clickButtonSaveNews();
        newsPg.equalsAddEditNews();
    }

    @Test (priority = 34, groups = "settings")      // Отображения новости на всех языках
    @Epic(value = "Настройки")
    public void newsDisplayInLanguages(){
        loginPg.loginUser(Values.user1_email,Values.user1_password);
        mainPg.gotoNews();
        newsPg.openLastAddNews();
        newsPg.equalsInLanguages();
    }

    @Test (priority = 35, groups = "settings")      // Удаление новой новости
    @Epic(value = "Настройки")
    public void delNews() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.delNews();
        newsPg.equalsDelNews();
    }

    //***********************************************//
    //                 AUCTION                       //
    //***********************************************//

    @Test  (priority=36, groups = "auction")                                          // Добавить лот
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

    @Test  (priority=37, groups = "auction")                                  // Изменение лотав  ПЕРЕРОБИТИ
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

    @Test (priority = 38, groups = "auction")                                 // Удаление лотов
    @Epic(value = "Аукцион")
    public void deleteLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.loti();
        lotiPg.delete();
        lotiPg.findDelLot();
    }

    @Test  (priority = 39, groups = "auction")                                 // Создание аукциона
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

    @Test  (priority = 49, groups = "auction")                                 // Редактирование аукциона
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

    @Test (priority = 50, groups = "auction")                                  // Удаление аукциона
    @Epic(value = "Аукцион")
    public void deleteAuctions() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        auctionsPg.delete();
        auctionsPg.findDelAuction();
    }

    @Test (priority = 51, groups = "auction", enabled= false)                   //  Добавить много лотов с помощу кнопки дублировать лот  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ1!!!!!
    @Epic(value = "Аукцион")
    public void  addManyLots(){
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.checkingExistencelots();
        lotiPg.addManyLoti(20);
    }

    @Test (priority = 52, groups = "auction", enabled= false)                   //  Старт предварительного аукциона  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ и добавить!!!!!
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

     @Test (priority = 53, groups = "auction", enabled= false)  // ставки пользователей на предварительном аукционе  !!!!!АЛЮРА НЕТ ПЕРЕРАБОТАТЬ и добавить!!!!!
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
