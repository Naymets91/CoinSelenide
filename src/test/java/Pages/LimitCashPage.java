package Pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class LimitCashPage extends Page {
    boolean tempBool;

    String beafore;
    String after;
    String temp;
    String parsName;

    Integer size;
    Integer sizeRandom;


    public void searchUser() {
        $(By.xpath("//select[@id='status']")).should(visible).click();
        $(By.xpath("//select[@id='status']/option[2]")).should(visible).click();
        $(By.xpath("//table[@id='dataTableRequests']//tr")).should(visible).click();
        $(byXpath("//*[text()='В ожидании']")).should(visible);
        $(byXpath("//*[text()='Serega Test 1 Serega1']/..//a[1]")).should(visible).click();
    }

    public void acceptNewCash() {
        $(By.xpath("//select[@id='status']")).should(visible).click();
        $(By.xpath("//select[@id='status']/option[3]")).should(visible).click();
        $(By.xpath("//div[@class='card-body']//button")).should(visible).click();
    }
public void goToHomePage(){
        $(By.xpath("//*[@id=\"navbar-mobile\"]//ul[2]/li/a")).click();
    }

    public void editLimitCash () {
        beafore =  $(By.xpath("//div[@class='wrapp__col col-xs-12 col-sm-9 col-md-4'][3]//input")).getAttribute("value");
        System.out.println("Було " + beafore);
        size = getRandomNumber(1000,5000);
        $(By.xpath("//input[@id='max_credit_limit']")).clear();
        $(By.xpath("//input[@id='max_credit_limit']")).sendKeys(Integer.toString(size));
        beafore = $(By.xpath("//input[@id='max_credit_limit']")).getAttribute("value");
        System.out.println("желаемый кредит  " + beafore);
        $(By.xpath("//button[@id='maxLimitBtn']")).click();
        $(By.xpath("//span[@class='limit-status text-warning']")).should(Condition.visible);

    }

    public void parsCash() {
        after =  $(By.xpath("//div[@class='wrapp__col col-xs-12 col-sm-9 col-md-4'][3]//input")).getAttribute("value");
        System.out.println("После подтверждения " + after);

    }

    public void equalsCash(){
        System.out.println(beafore.equals(after));
        if (beafore.equals(after) == false) {
            System.out.println("Кредитный  лимит не равен желаемому");
            throw new Error();
        } else {
            System.out.println("Кредитный  лимит равен желаемому");
        }
    }
}
