package Pages;

import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AuctionsPage extends Page {

    boolean tempBool;

    String temp;
    String parsName;

    Integer size;
    Integer sizeRandom;


///////////////////////////////////////////////   AddAuction  ///////////////////////////////////

    public void create() {
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();
    }

    public void number() {
        sizeRandom = getRandomNumber(1, 100);
        $(By.id("number")).sendKeys("№ " + sizeRandom);
    }

    public void name() {
        $(By.name("name")).sendKeys("Oksjon № " + sizeRandom);
        $(By.name("___name[en]")).sendKeys("Auction № " + sizeRandom);
        $(By.name("___name[ru]")).sendKeys("Аукцион № " + sizeRandom);
        System.out.println();
    }

    public void descrintion(int ee, int en, int ru) {
        $(By.xpath("//div[@class='col-12']/textarea[1]")).clear();
        $(By.xpath("//div[@class='col-12']/textarea[1]")).sendKeys(randomStringEE(ee));
//        System.out.println("Описание лота ee " + temp);

        createEN("___description[en]", en);
        createRU("___description[ru]", ru);
        System.out.println();

    }


    public void dateStart(String start) {
        $(By.xpath("//input[@name='start']")).sendKeys(start);
    }

    public void dateFinish(String finish) {
        $(By.xpath("//input[@name='finish']")).sendKeys(finish);
    }

    public void intervalEnd() {
        $(By.xpath("//input[@name='interval_end']")).sendKeys("10");
    }

    public void prolongation() {
        $(By.xpath("//input[@name='prolongation']")).sendKeys("10");
    }

    public void currency() {
        $(By.xpath("//select[@name='currency_id']")).click();
        $(By.xpath("//select[@name='currency_id']/option[2]")).click();
    }

    public void statys() {
        $(By.xpath("//select[@id='status']")).click();
        $(By.xpath("//select[@id='status']/option[2]")).click();
    }

    public void buttonSave() {
        parsName = $(By.id("number")).getAttribute("value");
        System.out.println(parsName);
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
        $(By.xpath("//select[@name='dataTable_length']")).click();
        $(By.xpath("//select[@name='dataTable_length']//option[4]")).click();
        $(byText(parsName)).click();
    }

    public void randomEditorAuction() {
        $(By.xpath("//select[@name='dataTable_length']")).click();
        $(By.xpath("//select[@name='dataTable_length']//option[4]")).click();

        $(By.xpath("//tr[@class='odd']")).click();
        size = $$(By.xpath("//table[@id='dataTable']//tbody/tr")).size();
        System.out.println(size);
        if (size == 0) {
            create();
            number();
            name();
//       descrintion(20,30,25);
            dateStart("2021/06/22 20:00");
            dateFinish("2021/06/26 23:00");
            intervalEnd();
            prolongation();
            currency();
            statys();
            buttonSave();
            $(By.xpath("//tr[@class='odd']")).click();
            size = $$(By.xpath("//table[@id='dataTable']//tbody/tr")).size();
        } else {
            System.out.println("Вибрано лот для редагування " + size);
        }
    }

    public void delete() {
        temp = $(By.xpath("//table[@id='dataTable']//tbody/tr[" + size + "]//td[2]")).getText();
        System.out.println(temp);
        $(By.xpath("//table[@id='dataTable']//tbody/tr[" + size + "]//button")).click();

        $(By.xpath("//div[@class='modal modal-admin confirm-delete']//button")).should(visible).click();

        tempBool = finde(By.xpath("//*[text()='" + temp + "']"));
        // $(By.xpath("//*[text()='"+ temp +"']")).should(visible);
        if (tempBool != true) {
            System.out.println("Аукцион удалился");
        } else {
            System.out.println("Аукцион не удалился");
            $(By.xpath("//*[@id='t']/t")).click();  // чтоб тест упал когда найден удаляемый лот
        }
        System.out.println(tempBool);
    }

    ///////////////////////////////////////////// startAuctions ////////////////////////////////


    public void showMaxAuctions() {
        $(By.xpath("//div[@id='dataTable_length']//select")).click();
        $(By.xpath("//div[@id='dataTable_length']//select/option[4]")).click();     // показать на странице максимальное количество аукционов
    }

    public void startAuction() {
        $(By.xpath("//table[@id='dataTable']//tbody//td[6]/a")).click();       // нажать на кнопку редактировать
        $(By.xpath("//input[@name='start']")).click();
        $(By.xpath("//button[@class='xdsoft_next']")).click();
        $(By.xpath("//td[@class='xdsoft_date xdsoft_day_of_week4 xdsoft_date xdsoft_current']")).click();


        $(By.xpath("//input[@name='finish']")).click();
        sleep(1000);
        $(By.xpath("//button[@class='xdsoft_next']")).click();
        
        $(By.xpath("//td[@class='xdsoft_date xdsoft_day_of_week4 xdsoft_date xdsoft_current']")).click();

        sleep(1000);

       sleep(15000);



//        $(By.xpath("//select[@id='status']")).click();
//        $(By.xpath("//select[@id='status']/option[3]")).click();
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();

    }


}
