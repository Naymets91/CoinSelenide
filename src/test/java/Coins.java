
import Config.Values;
import Pages.AdminPanelPage;
import Pages.LotiPagePage;


import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;

import org.testng.annotations.*;

import static com.codeborne.selenide.Selenide.*;


public class Coins {

    LotiPagePage lotiPg;
    AdminPanelPage adminPanelPg;
    Values values;

    @BeforeMethod
    public void setUp() {
        lotiPg = new LotiPagePage();
        adminPanelPg = new AdminPanelPage();
        values = new Values();

        Configuration.timeout = 6000;
       Configuration.startMaximized = true;
//        Configuration.browserSize = "1920x1080";
        values.openHomePage();
        values.Login();
        SwitchLanguage();
    }

    @Test
    public void addLot() {
        gotoAdminPanel();
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
        lotiPg.buttonSave();
    }
    @Test
    public void editLot () {
        gotoAdminPanel();
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
        gotoAdminPanel();
        adminPanelPg.auctions();
        adminPanelPg.loti();
        lotiPg.randomEditLot();
        lotiPg.delete();
    }




    private void SwitchLanguage() {
        $(By.className("flag")).click();
        $(By.xpath("//div[@class='nav-lang__list']/a[3]")).click();
    }

    private void gotoAdminPanel() {
        $(By.xpath("//div[@class='header-nav__col col-lg-4']/ul")).click();
        $(By.xpath("//ul[@class='-visible']/li[1]/a")).click();
    }




}
