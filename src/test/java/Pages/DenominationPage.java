package Pages;

import Config.Values;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class DenominationPage extends Page{
    Integer sizeRandom;
    String nameEe;
    String nameRu;
    String tempName;
    String rangomNameDenomination;

    @Step("Клик на кнопку Добавить номинал")
    public void clickButtonAddDenomination() {
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();// Нажать на кнопку добавить номинал
    }


    @Step("Заполнения полей")
    public void fillDenomination() {
        sizeRandom = getRandomNumber(50, 100);
        setName("Ee", "name");
        nameEe = $(By.name("name")).getAttribute("value");
        setName("En", "___name[en]");
        setName("Ru", "___name[ru]");
        $(By.id("sort")).clear();
        $(By.id("sort")).sendKeys("" + sizeRandom);
    }


    public String setName(String lang, String xPathName) {
        Integer temp = null;
        String langEe = "Ee";
        String langEn = "En";
        String langRu = "Ru";
        if (langEe.equals(lang)) {
            temp = 1;
        }
        if (langEn.equals(lang)) {
            temp = 2;
        }
        if (langRu.equals(lang)) {
            temp = 3;
        }
        $(By.id("lang-selector")).click();
        if (langEe.equals(lang)) {
            rangomNameDenomination = String.valueOf(randomStringEE(7));
            nameEe = String.valueOf(rangomNameDenomination);
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameDenomination);
            Allure.attachment("Название номинала  Ee", "Имя =  " + rangomNameDenomination);
        }
        if (langEn.equals(lang)) {
            rangomNameDenomination = String.valueOf(randomStringEN(8));
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameDenomination);
            Allure.attachment("Название номинала  Еn ", "Имя = " + rangomNameDenomination);
        }
        if (langRu.equals(lang)) {
            rangomNameDenomination = String.valueOf(randomStringRU(5));
            nameRu = rangomNameDenomination;
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameDenomination);
            Allure.attachment("Название номинала Ru ", "Имя = " + rangomNameDenomination);
        }
        return rangomNameDenomination;
    }

    @Step("Клик на кнопку Сохранить")
    public void clickButtonSaveDenomination() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Проверка создания / редактирования номинала")
    public void equalsAddEditDenomination() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        Boolean name = find((By.xpath("//*[text()='" + nameEe + "']")));
        if (name == true) {
            System.out.println("Елемент успешно создан/редактирован");
            Allure.attachment("Результат", "Елемент успешно создан/редактирован");
        } else {
            System.out.println("Елемент не создан/не редактирован ");
            Allure.attachment("Результат", "!! Елемент не создан /не редактирован !!");
            throw new Error();
        }
    }

    @Step("Клик на кнопку Редактирования созданого номинала")
    public void clickButtonEditDenomination() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).clear();
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//*[text()='" + nameEe + "']/..//a")).click();
    }

    @Step("Клик на кнопку Обновить")
    public void clickButtonUpdateDenomination() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Использование нового периода")
    public void usageNewDenomination() {

        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();      //  клик по меню лоты
        $(By.xpath("//select[@id='auction_id']")).click();
        $(By.xpath("//select[@id='auction_id']/option[@value='"+ Values.auctionsUseNominal +"']")).click();  // Выбор номера аукциона
        sleep(8000);
        $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[3]//a[2]")).click(); //  редактировать

        $(By.name("nominal_id")).click();                                              // добавить в лот новую категорию
        size = $$(By.xpath("//select[@name='nominal_id']/option")).size();
        $(By.xpath("//select[@name='nominal_id']/option[contains(text(), '" + nameEe + "')]")).click();
        System.out.println(size);
        tempName = $(By.name("nominal_id")).getSelectedText();
        System.out.println("Temp name =" + tempName);
        System.out.println("NameEE =" + nameEe);
        if (tempName.equals(nameEe) != true) {
            Allure.attachment("Результат", ">>> Невозможно выбрать созданый период <<<");
            System.out.println(">>> Невозможно выбрать созданый период <<<");
            throw new Error();
        } else {
            Allure.attachment("Результат", ">>> Новый период успешно добавленая в лот <<<");
            System.out.println(">>> Новый период успешно добавленая в лот <<<");
        }
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();// клик по кнопке обновить
        open("https://coins.dd-dev.club/auction/show/31");
        $(By.xpath("//div[@class='auction-filter__wrapp dash-filter__wrapp filter-mob']")).shouldBe(visible);
        Boolean visible = $(By.xpath("//div[@class='category__cont'][3]//span[@class='ng-binding']")).isDisplayed();
        if (visible == true){
            $(By.xpath("//div[@class='category__cont'][3]//span[@class='ng-binding']")).click(); // показать все категории
            sleep(1000);
            System.out.println("клик на показать все категории");
        }
        Boolean nameBool = $(By.xpath("//*[text()='" + nameRu + "']")).exists();
        System.out.println("NameRu = " + nameRu);
        System.out.println("Name = " + nameBool);
        if (nameBool == true) {
            System.out.println(">>> Елемент присутствует в фильтре <<<");
            Allure.attachment("Результат", ">>> Елемент присутствует в фильтре <<<");
        } else {
            System.out.println("Елемент отсутствует в фильтре");
            Allure.attachment("Результат", ">>> Елемент отсутствует в фильтре <<<");
            throw new Error();
        }

    }


    @Step("Удаления созданого периода")
    public void delDenomination() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).click();
    }

    @Step("Проверка удаления периода")
    public void equalsDenomination() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        Boolean nameB = $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).isDisplayed();
        System.out.println("Поиск удаления = " + nameB);
        if (nameB != true) {
            System.out.println("Елемент успешно удален");
            Allure.attachment("Результат", ">>> Елемент успешно удален <<<");
        } else {
            System.out.println("Елемент не создан/не редактирован ");
            Allure.attachment("!!Результат!!", ">>> Елемент не удален  <<<");
            throw new Error();
        }
    }



}
