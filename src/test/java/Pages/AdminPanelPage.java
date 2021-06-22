package Pages;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class AdminPanelPage extends Page {


    public void auctions() {

        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();
    }

    public void loti () {
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();
    }

    public void auctionAdd() {
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[1]//span")).click();
    }
}

