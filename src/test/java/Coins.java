
import Config.Values;
import Pages.AdminPanelPage;
import Pages.LotiPagePage;


import Pages.MainPage;
import com.codeborne.selenide.Configuration;

import org.testng.annotations.*;


public class Coins {

    LotiPagePage lotiPg;
    AdminPanelPage adminPanelPg;
    Values values;
    MainPage mainPg;

    @BeforeMethod
    public void setUp() {
        lotiPg = new LotiPagePage();
        adminPanelPg = new AdminPanelPage();
        values = new Values();
        mainPg = new MainPage();

        Configuration.timeout = 6000;
       Configuration.startMaximized = true;
//        Configuration.browserSize = "1920x1080";
        values.openHomePage();
        values.Login();
        mainPg.SwitchLanguageRu();
    }

    @Test
    public void addLot() {
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
    @Test
    public void editLot () {
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

    @Test
    public void deleteLot() {
        mainPg.gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.randomEditLot();
        lotiPg.delete();
    }









}
