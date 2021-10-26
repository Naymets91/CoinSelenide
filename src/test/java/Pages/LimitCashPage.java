package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
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

    @Step("Поиск нужногого пользователя")
    public void searchUser() {
        sleep(2000);
        $(By.xpath("//select[@id='status']")).should(visible).click();
        $(By.xpath("//select[@id='status']/option[2]")).should(visible).click();
        sleep(2000);
        $(By.xpath("//table[@id='dataTableRequests']//tr")).should(visible).click();
        $(byXpath("//*[text()='В ожидании']")).should(visible);
    }
    @Step("Нажатия на кнопку редактировать")
    public void userEdit() {
        $(byXpath("//*[contains(text(),'Serega Limit Edit')]/..//a[1]")).should(visible).click();
    }

    public void userDel() {
        $(byXpath("//*[contains(text(),'Serega Limit Edit')]/..//a[2]")).should(visible).click();
        sleep(4000);
    }
    @Step("Соглашения с выбраным кредитным лимитом")
    public void acceptNewCash() {
        $(By.xpath("//select[@id='status']")).should(visible).click();
        $(By.xpath("//select[@id='status']/option[3]")).should(visible).click();
        $(By.xpath("//div[@class='card-body']//button")).should(visible).click();
    }
    @Step("Изменения статуса на отменено")
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

    @Step("Создания запроса на изменения кредитного лимита")
    public void editLimitCash () {
        yourLimitBeafore =  $(By.xpath("//div[@class='wrapp__col col-xs-12 col-sm-9 col-md-4'][3]//input")).getAttribute("value");
        System.out.println("Було " + yourLimitBeafore);
        size = getRandomNumber(1000,5000);
        $(By.xpath("//input[@id='max_credit_limit']")).clear();
        $(By.xpath("//input[@id='max_credit_limit']")).sendKeys(Integer.toString(size));
        desiredLimit = $(By.xpath("//input[@id='max_credit_limit']")).getAttribute("value");
        System.out.println("желаемый кредит  " + desiredLimit);
        Allure.attachment("Результат", "желаемый кредит  " + desiredLimit );
        $(By.xpath("//button[@id='maxLimitBtn']")).click();

    }
    @Step("После подтверждения")
    public void parsCash() {
        yourLimitAfter =  $(By.xpath("//div[@class='wrapp__col col-xs-12 col-sm-9 col-md-4'][3]//input")).getAttribute("value");
        System.out.println("После подтверждения " + yourLimitAfter);
        Allure.attachment("Результат", "После подтверждения " + yourLimitAfter );
    }
    @Step("Сравнения текущего и желаемого лимита")
    public void equalsCash(){
        System.out.println(yourLimitAfter.equals(desiredLimit));
        if (yourLimitAfter.equals(desiredLimit) == false) {
            System.out.println("Кредитный  лимит не равен желаемому");
            Allure.attachment("Результат", "После подтверждения " + yourLimitAfter + "Желаемый  лимит " + desiredLimit );
            throw new Error();
        } else {
            System.out.println("Кредитный  лимит равен желаемому");
            Allure.attachment("Результат", "После подтверждения " + yourLimitAfter + "Желаемый  лимит " + desiredLimit );
        }
    }
    @Step("Проверка после отмены/удаления кредитного лимимта  ")
    public void equalsCashReject() {
        finndSizeFalse(By.xpath("//span[@class='limit-status text-warning']"),
                "Статус изминен на отклонено или заявка на лимит удалена",
                "Ошибка изминения статуса или удаления кредитного запроса");
        if (yourLimitAfter.equals(yourLimitBeafore) == true) {
            System.out.println("Кредитный  лимит не изменен ");
            Allure.attachment("Результат", "Кредитный  лимит не изменен " );
        } else {
            System.out.println("Кредитный  лимит изменен");
            Allure.attachment("Результат", "!!!Кредитный  лимит изменен " );
            throw new Error();
        }

    }
}
