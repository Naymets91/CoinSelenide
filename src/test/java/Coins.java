
import Pages.*;


import com.codeborne.selenide.Configuration;

import org.testng.annotations.*;


public class Coins extends Page {

    AuctionsPage auctionsPg;
    LotiPagePage lotiPg;
    AdminPanelPage adminPanelPg;
    MainPage mainPg;
    LimitCashPage limitCashPg;
    LoginPage loginPg;
    UkrnetPage ukrnetPg;

    @BeforeMethod
    public void setUp() {
        lotiPg = new LotiPagePage();
        adminPanelPg = new AdminPanelPage();
        mainPg = new MainPage();
        auctionsPg =new AuctionsPage();
        limitCashPg = new LimitCashPage();
        loginPg = new LoginPage();
        ukrnetPg = new UkrnetPage();

        Configuration.timeout = 20000;
       Configuration.startMaximized = true;
//        Configuration.browserSize = "1920x1080";
        openHomePage();
        mainPg.SwitchLanguageRu();
//        LoginUser();
    }

    @Test                                             // Тест двойной аутентификации
    public void correct2FALogin() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
    }

    @Test                                             // Добавить лот
    public void addLot() {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.createLot();
        lotiPg.name(12,15,10);
        lotiPg.descrintion(20,30,25);
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



    @Test                                       // Изменение лотав
    public void editLot () {
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.randomEditLot();
        lotiPg.editLot();
        lotiPg.parsLotBefore();
        lotiPg.name(5,10,8);
        lotiPg.descrintion(15,25,15);
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
        lotiPg.equalsLot( );

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


    @Test                                       // Изменение кредитного лимита
    public  void editLimit() {
        loginPg.loginUser();
        mainPg.gotoProfile();
        limitCashPg.editLimitCash();
        loginPg.logAutUser();
        loginPg.loginAdmin();
        mainPg.gotoAdminPanel();
        adminPanelPg.limitCahEdit();
        limitCashPg.searchUser();
        limitCashPg.acceptNewCash();
//        limitCashPg.goToHomePage();
        loginPg.logAutAdmin();
        loginPg.loginUser();
        mainPg.gotoProfile();
        limitCashPg.parsCash();
        limitCashPg.equalsCash();
    }

@Test                                               // Востановления пароля
    public void  recoveryPassword () {
        loginPg.createRequestRecoveryPassword ();
        openUkrnetPage();
        ukrnetPg.loginUkrnet();
        ukrnetPg.selectionLastLetter();
        loginPg.recsetPassword();
        loginPg.logAutUser();
        loginPg.loginRessetPassword();
}

}
