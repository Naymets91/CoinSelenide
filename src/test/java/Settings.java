
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
public class Settings extends Page {
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
    //                 SETTING                       //
    //***********************************************//

    @Test (priority = 16, groups = {"settings"})      // Создание новой катеории
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

    @Test (priority = 17, groups = {"settings"})      // Редактирование новой катеории
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

    @Test (priority = 19, groups = {"settings"})      // Удаление новой категории
    @Epic(value = "Настройки")
    public void delCategor() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.category();
        categoryPg.delCategory();
        categoryPg.equalsDelCategory();
    }

    @Test (priority = 20 , groups = {"settings"})     // Создание нового периода
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

    @Test (priority = 21, groups = {"settings"})      // Редактирование нового периода
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

    @Test (priority = 22, groups = {"settings"})      // Использование нового периода
    @Epic(value = "Настройки")
    public void usageNewPeriod() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        periodPg.usageNewPeriod();
    }

    @Test (priority = 23, groups = {"settings"})      // Удаление нового периода
    @Epic(value = "Настройки")
    public void delPeriod() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.period();
        periodPg.delPeriod();
        periodPg.equalsDelPeriod();
    }

    @Test(priority = 24, groups = {"settings"})       // Создание нового номинала
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

    @Test (priority = 25, groups = {"settings"})      // Редактирование нового номинала
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

    @Test (priority = 26, groups = {"settings"})      // Использвание нового номинала
    @Epic(value = "Настройки")
    public void useDenomination() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        denominationPg.usageNewDenomination();
    }

    @Test (priority = 27, groups = {"settings"})      // Удаление нового номинала
    @Epic(value = "Настройки")
    public void delDenomination() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.denomination();
        denominationPg.delDenomination();
        denominationPg.equalsDenomination();
    }

    @Test (priority = 28, groups = {"settings"})      // Создание нового Материала
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

    @Test (priority = 29, groups = {"settings"})      // Редактирование нового Материала
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

    @Test (priority = 30, groups = {"settings"})      // Использвание нового Материала
    @Epic(value = "Настройки")
    public void useMaterials() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        materialsPg.usageNewMaterials();
    }

    @Test (priority = 31, groups = {"settings"})      // Удаление нового Материала
    @Epic(value = "Настройки")
    public void delMaterials() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.materials();
        materialsPg.delMaterials();
        materialsPg.equalsMaterials();
    }

    @Test (priority = 32, groups = {"settings"})      // Создание новости
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
    @Test (priority = 33, groups = {"settings"})      // Редактирование новости
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

    @Test (priority = 34, groups = {"settings"})      // Отображения новости на всех языках
    @Epic(value = "Настройки")
    public void newsDisplayInLanguages(){
        loginPg.loginUser(Values.user1_email,Values.user1_password);
        mainPg.gotoNews();
        newsPg.openLastAddNews();
        newsPg.equalsInLanguages();
    }

    @Test (priority = 35, groups = {"settings"})      // Удаление новой новости
    @Epic(value = "Настройки")
    public void delNews() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.news();
        newsPg.delNews();
        newsPg.equalsDelNews();
    }




    @AfterMethod
    public void after() {
        closeWebDriver();
    }
}
