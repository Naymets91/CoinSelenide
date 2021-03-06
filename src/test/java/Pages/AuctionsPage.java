package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AuctionsPage extends Page {


    boolean tempBool;
    boolean numberBool;

    List<String> favoritesLot = new ArrayList<>();
//    String[] favoritesLot ;

    String temp;
    String templot;

    String nameAuctionEE;

    String priceaAuction;
    String priceaFavorites;
    String priceTemp;

    Integer size;
    Integer sizeRandom;

    String afterBet;
    String beaforeBet;
    String tempBet;


///////////////////////////////////////////////   AddAuction  ///////////////////////////////////
@Step("Нажатия на кнопку создать")
    public void buttonCreateAuction() {
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();
    }
    @Step("Нажатия на кнопку редактирования")
    public void buttonEditAuction() {
        $(By.xpath("//input[@type='search']")).sendKeys(nameAuctionEE);
        $(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']")).click();
    }

    public void number() {
        sizeRandom = getRandomNumber(1, 100);
        $(By.id("number")).sendKeys("№ " + sizeRandom + randomStringEE(2));
    }

    public void name() {
        nameAuctionEE = String.valueOf(randomStringEE(6));
        System.out.println( "Название аукциона на ее = " + nameAuctionEE);
        $(By.name("name")).clear();
        $(By.name("name")).sendKeys(nameAuctionEE);
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
    @Step("Нажатия на кнопку сохранить")
    public void buttonSave() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }
    @Step("Поиск наличия аукциона")
    public void findAuction() {
        sleep(3000);
        $(By.xpath("//input[@class='form-control form-control-sm']")).sendKeys(nameAuctionEE);
        sleep(3000);
        finndSizeTrue(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']"),
                "Аукцион найден",
                "!!Аукцион не найден");

    }


    @Step("Удаления созданого аукциона")
    public void delete() {
        $(By.xpath("//input[@type='search']")).sendKeys(nameAuctionEE);
        $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light btn-delete']")).click();
        $(By.xpath("//button[@class='btn btn-danger confirm-delete-btn waves-effect waves-light']")).click();
    }
    @Step("Проверка на удаления аукциона")
    public void findDelAuction() {
        sleep(3000);
        $(By.xpath("//input[@class='form-control form-control-sm']")).sendKeys(nameAuctionEE);
        sleep(3000);
        finndSizeFalse(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']"),
                "Аукцион не найден",
                "!!Аукцион найден");

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
        if (tempBool == false) {
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
    @Step("Рандомное добавление лота в избранное")
    public void randomAddFavorites() {
        $(By.xpath("//div[@class='auction-one']")).shouldBe(visible);
        size = $$(By.xpath("//div[@class='auction-one']")).size();
        System.out.println("Найдено - " + size + " Лотов");
        for (int i = 0; i < 10; i++) {
            sizeRandom = getRandomNumber(1, size);
            System.out.println("sizeRandom = " + sizeRandom);
            tempBool = findIf(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//div[@class='auction-one__favorite ng-scope']"));
            if (tempBool == true) {
                System.out.println("Tyta");
                break;
            }
        }
        temp = $(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//p")).getText();
        templot = $(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//a[@class='auction-one__lots']/span")).getText();
        priceTemp = $(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//div[@class='ng-scope']//span")).getText();
        priceaAuction = priceTemp + " €";
        System.out.println("Вибран лот  № " + templot);
        System.out.println("Имя вибранного лота = " + temp);
        System.out.println("цена = " + priceaAuction);
        $(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//div[@class='auction-one__favorite ng-scope']/img")).click();
        Allure.attachment("Имя лота", "Имя =  " + temp );
        Allure.attachment("№ лота", "№ лота = " + templot );
        Allure.attachment("Цена лота", "Цена =  " + priceaAuction );
    }

    @Step("Проверка на добавление лота в избранное")
    public void equalsAddFavorites() {
        sleep(2000);
        tempBool = findIf(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//div[@class='auction-one__favorite checked ng-scope']"));
        if (tempBool == true) {
            System.out.println("Добалено в избранное");
            Allure.attachment("Значение", "Добалено в избранное");
        } else {
            System.out.println("Не добалено в избранное");
            Allure.attachment("Значение", "!!Не добалено в избранное!!");
            throw new Error();
        }
    }

    @Step("Проверка на удаления лота с избранного")
    public void equalsDellFavorites() {
        sleep(2000);
        tempBool = findIf(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//div[@class='auction-one__favorite checked ng-scope']"));
        if (tempBool != true) {
            System.out.println("Удалено с избранного");
            Allure.attachment("Значение", "Удалено с избранного");
        } else {
            System.out.println("Не удалено с избранного");
            Allure.attachment("Значение", "!!Не удалено с избранного!!");
            throw new Error();
        }
    }

    public boolean findIf(By xpath) {
        if ($$(xpath).size() != 0) {
            System.out.println("Poisk = true");
            return true;
        } else {
            System.out.println("Poisk = false");
            return false;
        }
    }

    @Step("Удаление лота из избранного")
    public void delAddFavorites() {
        $(By.xpath("//div[@class='col-xs-12 ng-scope col-sm-6'][" + sizeRandom + "]//div[@class='auction-one__favorite checked ng-scope']")).click();
    }

    @Step("Проверка отображается на странице избранное")
    public void equalsAddFavoritesPage() {
        sleep(2000);
        tempBool = findIf(By.xpath("//*[text()='" + temp + "']"));
        if (tempBool == true) {
            System.out.println("Отображается на странице избранное");
            Allure.attachment("Значение", "Отображается на странице избранное");
        } else {
            System.out.println("Не отображается на странице избранное");
            Allure.attachment("Значение", "!!Не отображается на странице избранное!!");
            throw new Error();
        }
        priceaFavorites = $(By.xpath("//*[text()='" + temp + "']/../../..//p[@class='price ng-binding']")).getText();

    }

    @Step("Удаление лота из избранного на странице избранное")
    public void delAddFavoritesPageFavorites() {
        $(By.xpath("//*[text()='" + temp + "']/../../../../..//div[@class='auction-one__favorite checked ng-scope']")).click();
    }

    @Step("Проверка на удаления лота с избранного страница избранное")
    public void equalsDellFavoritesPageFavorites() {
        sleep(2000);
        tempBool = findIf(By.xpath("//*[text()='" + temp + "']"));
        if (tempBool != true) {
            System.out.println("Удалено с избранного");
            Allure.attachment("Значение", "Удалено с избранного");
        } else {
            System.out.println("Не удалено с избранного");
            Allure.attachment("Значение", "!!Не удалено с избранного!!");
            throw new Error();
        }
    }

    public void randomAddManyFavorites() {
        Integer j;
        for (j = 0; j < 5; j++) {
            randomAddFavorites();
            favoritesLot.add(temp);
            sleep(1000);
        }
    }

    public void equalsAddManyFavorites() {
        Integer j;
        for (j = 0; j < 5; j++) {
            temp = favoritesLot.get(j);
            sleep(2000);
            tempBool = findIf(By.xpath("//*[text()='" + temp + "']/../../../../..//div[@class='auction-one__favorite checked ng-scope']"));
            if (tempBool == true) {
                System.out.println("Добалено в избранное");
                Allure.attachment("Значение", "Добалено в избранное");
            } else {
                System.out.println("Не добалено в избранное");
                Allure.attachment("Значение", "!!Не добалено в избранное!!");
                throw new Error();
            }
        }
    }

    public void delAddManyFavorites() {
        Integer j;
        for (j = 0; j < 5; j++) {
            temp = favoritesLot.get(j);
            delAddFavoritesPageFavorites();
            sleep(1000);
        }
    }

    public void equalsDellManyFavorites() {
        Integer j;
        for (j = 0; j < 5; j++) {
            temp = favoritesLot.get(j);
            sleep(2000);
            tempBool = findIf(By.xpath("//*[text()='" + temp + "']/../../../../..//div[@class='auction-one__favorite checked ng-scope']"));
            if (tempBool != true) {
                System.out.println("Удалено с избранного");

            } else {
                System.out.println("Не удалено с избранного");
                Allure.attachment("Значение", "!!Не удалено с избранного!!");
                throw new Error();
            }
        }
    }
    @Step("Обновление странице")
    public void refreshPage(){
        refresh();
        sleep(5000);
    }

    public void equalsPriceFavoritesPage() {
        System.out.println("на странице аукциона = " + priceaAuction);
       tempBool = (priceaAuction.equals(priceaFavorites));
        System.out.println("temmpBool = " + tempBool);
       if (tempBool == true) {
           System.out.println( "Цена сооветствует " + priceaAuction + " = "+ priceaFavorites);
           Allure.attachment("Значение", "Цена сооветствует " + priceaAuction + " = " + priceaFavorites );
       }else  {
           System.out.println( "Цена не сооветствует " + priceaAuction + " != "+ priceaFavorites);

           Allure.attachment("Значение", "Цена не сооветствует " + priceaAuction + " != " + priceaFavorites );
           throw new Error();
       }

    }
    @Step("Заполнения данных о аукционе")
    public void fillAuction(boolean numberBool) {
        if (numberBool == true) {
        number();
        }
        name();
//       descrintion(20,30,25);
        dateStart("2021/06/22 20:00");
        dateFinish("2021/06/26 23:00");
        intervalEnd();
        prolongation();
        currency();
        statys();
    }



}
