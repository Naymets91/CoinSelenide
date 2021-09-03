package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.*;

public class LimitCashPage extends Page {
    boolean tempBool;

    String yourLimitBeafore;
    String yourLimitAfter;
    String desiredLimit;
    String temp;
    String parsName;

    Integer size;
    Integer sizeRandom;


    public void searchUser() {
        $(By.xpath("//select[@id='status']")).should(visible).click();
        $(By.xpath("//select[@id='status']/option[2]")).should(visible).click();
        sleep(2000);
        $(By.xpath("//table[@id='dataTableRequests']//tr")).should(visible).click();
        $(byXpath("//*[text()='В ожидании']")).should(visible);
    }

    public void userEdit() {
        $(byXpath("//*[contains(text(),'Serega limit')]/..//a[1]")).should(visible).click();
    }

    public void userDel() {
        $(byXpath("//*[contains(text(),'Serega limit')]/..//a[2]")).should(visible).click();
        sleep(4000);
    }

    public void acceptNewCash() {
        $(By.xpath("//select[@id='status']")).should(visible).click();
        $(By.xpath("//select[@id='status']/option[3]")).should(visible).click();
        $(By.xpath("//div[@class='card-body']//button")).should(visible).click();
    }

    public void rejectNewCash() {
        $(By.xpath("//select[@id='status']")).click();
        $(By.xpath("//select[@id='status']/option[4]")).click();
        $(By.xpath("//div[@class='top-page-name']//h1")).click();
        sleep(2000);
        $(By.xpath("//div[@class='card-body']//button")).click();
    }

public void goToHomePage(){
        $(By.xpath("//*[@id=\"navbar-mobile\"]//ul[2]/li/a")).click();
    }

    public void editLimitCash () {
        yourLimitBeafore =  $(By.xpath("//div[@class='wrapp__col col-xs-12 col-sm-9 col-md-4'][3]//input")).getAttribute("value");
        System.out.println("Було " + yourLimitBeafore);
        size = getRandomNumber(1000,5000);
        $(By.xpath("//input[@id='max_credit_limit']")).clear();
        $(By.xpath("//input[@id='max_credit_limit']")).sendKeys(Integer.toString(size));
        desiredLimit = $(By.xpath("//input[@id='max_credit_limit']")).getAttribute("value");
        System.out.println("желаемый кредит  " + desiredLimit);
        $(By.xpath("//button[@id='maxLimitBtn']")).click();

    }

    public void parsCash() {
        yourLimitAfter =  $(By.xpath("//div[@class='wrapp__col col-xs-12 col-sm-9 col-md-4'][3]//input")).getAttribute("value");
        System.out.println("После подтверждения " + yourLimitAfter);
    }

    public void equalsCash(){
        System.out.println(yourLimitAfter.equals(desiredLimit));
        if (yourLimitAfter.equals(desiredLimit) == false) {
            System.out.println("Кредитный  лимит не равен желаемому");
            throw new Error();
        } else {
            System.out.println("Кредитный  лимит равен желаемому");
        }
    }

    public void equalsCashReject() {
        boolean size = find(By.xpath("//span[@class='limit-status text-warning']"));
        if (size == false) {
            System.out.println("Статус изминен на отклонено или заявка на лимит удалена");
        } else {
            System.out.println("Ошибка изминения статуса или удаления кредитного запроса");
            throw new Error();
        }

        if (yourLimitAfter.equals(yourLimitBeafore) == true) {
            System.out.println("Кредитный  лимит не изменен ");
        } else {
            System.out.println("Кредитный  лимит изменен");
            throw new Error();
        }

    }
}
