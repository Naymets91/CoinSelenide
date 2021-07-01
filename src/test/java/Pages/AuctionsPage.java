package Pages;

import Config.Values;
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

    String afterBet;
    String beaforeBet;
    String tempBet ;


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

    public void startAuctionDate() {
        $(By.xpath("//table[@id='dataTable']//tbody//td[6]/a")).click();       // нажать на кнопку редактировать (1 в списку аукцион)

        $(By.xpath("//input[@name='start']")).click();
        $(By.xpath("//input[@name='start']")).clear();
        $(By.xpath("//input[@name='start']")).sendKeys("2021/07/30 11:00"); //  заполнить поле старта аукциона
        sleep(1000);

        $(By.xpath("//input[@name='finish']")).click();
        $(By.xpath("//input[@name='finish']")).clear();
        $(By.xpath("//input[@name='finish']")).sendKeys("2021/07/30 18:00");    //  заполнить поле конца аукциона
        sleep(1000);

        $(By.xpath("//select[@id='status']")).click();                  // клик по выбору статуса
        $(By.xpath("//select[@id='status']/option[3]")).click();        // вибор статус ДА

        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();    // нажатия кнопик обновить

    }


    public void onlineAuction() {
        $(By.xpath("//ul[@class='header-nav__nav -horizontal']/li[2]")).click();    // клик по випадающем меню аукционы
        $(By.xpath("//ul[@class='header-nav__nav -horizontal']/li[2]//li")).click();    // клик по пункту онлайн аукционы
    }

    public void equalsStartAuction() {
        $(By.xpath("//div[@class='auction-ithem__desc']//a[2]")).click();       // клик на кнопку редактировать
        temp = $(By.xpath("//p[@id='timer-inner']")).getText();             // получам информацию про время до начала аукциона
        System.out.println("До начала  " + temp);

    }

    public void stopAuction() {
        $(By.xpath("//table[@id='dataTable']//tbody//td[5]/a")).click();       // клик на кнопку админ панель (1 в списку аукцион)
        $(By.xpath("//div[@class='panel__btn-set'][3]/img")).click();        // нажать на кнопку остановить аукион
        $(By.xpath("//div[@class='message__btn-wrapp']/button[2]")).click();    // нажать на кнопку подтверждения
        openHomePage();     // перейти на главную
        $(By.xpath("//ul[@class='header-nav__nav -horizontal']/li[2]")).click();   // клик на меню аукционы
        $(By.xpath("//ul[@class='header-nav__nav -horizontal']/li[2]//li")).click();        // клик на меню онлайн аукционы
        if ($$(By.xpath("//div[@class='auction-ithem']")).size() != 0) {
            System.out.println("Аукцион не остановлен");
            throw new Error();
        } else {
            System.out.println("Аукцион успершно остановлен");
        }
    }

    public void startAuction() {
        $(By.xpath("//table[@id='dataTable']//tbody//td[5]/a")).click();       // клик на кнопку админ панель (1 в списку аукцион)
        $(By.xpath("//div[@class='panel__btn-set'][5]/img")).click();        // нажать на настройки аукциона
        $(By.xpath("//div[@class='panel__btn-wrapp ng-scope']/p[3]")).click();  // клик на кноку рестарт аукциона
        $(By.xpath("//div[@class='message__btn-wrapp']/button[2]")).click();    // нажать на кнопку подтверждения
    }

    public void placeBet() {
        $(By.xpath("//div[@class='auction-ithem']//a[2]")).click(); // перейти в онлайн аукцион
        if (tempBet == null) {
            tempBet = $(By.xpath("//div[@class='auction-one__rate ng-scope']//input")).getAttribute("value");   // узнать суму ставки до ставки
            beaforeBet = $(By.xpath("//div[@class='auction-one__rate ng-scope']//input")).getAttribute("value");    // чтоб при первом запуске неупал тест
        }
        tempBool = tempBet.equals(tempBet);
        if (tempBool == false){
            System.out.println("ХЗ");
            throw new Error();
        }
        sleep(1500);
        beaforeBet = $(By.xpath("//div[@class='auction-one__rate ng-scope']//input")).getAttribute("value");  // узнать суму ставки до ставки
        System.out.println("Цена до ставки " + beaforeBet);
        $(By.xpath("//div[@class='auction-one__rate ng-scope']//button")).click();
        sleep(1500);// сделать ставку
        $(By.xpath("//div[@class='message__btn-wrapp']//button")).click();      // подтвердить ставку
        sleep(1500);
        afterBet = $(By.xpath("//div[@class='auction-one__rate ng-scope']//input")).getAttribute("value");  // узнать суму после ставки
        System.out.println("После ставки " + afterBet);
        System.out.println("");
        tempBet = afterBet;
    }


    /////////////////////////////////////////////////////////////////////////////////////////
}
