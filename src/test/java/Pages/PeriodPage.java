package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Condition.visible;

public class PeriodPage extends Page {

    Integer sizeRandom;
    String nameEe;
    String nameRu;
    String tempName;
    String rangomNamePeriod;

    @Step("Клик на кнопку Добавить период")
    public void clickButtonAddPeriod() {
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();// Нажать на кнопку добавить период
    }


    @Step("Заполнения полей")
    public void fillPeriod() {
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
            rangomNamePeriod = String.valueOf(randomStringEE(7));
            nameEe = String.valueOf(rangomNamePeriod);
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNamePeriod);
            Allure.attachment("Название периода  Ee", "Имя =  " + rangomNamePeriod);
        }
        if (langEn.equals(lang)) {
            rangomNamePeriod = String.valueOf(randomStringEN(8));
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNamePeriod);
            Allure.attachment("Название периода  Еn ", "Имя = " + rangomNamePeriod);
        }
        if (langRu.equals(lang)) {
            rangomNamePeriod = String.valueOf(randomStringRU(5));
            nameRu = rangomNamePeriod;
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNamePeriod);
            Allure.attachment("Название периода Ru ", "Имя = " + rangomNamePeriod);
        }
        return rangomNamePeriod;
    }

    @Step("Клик на кнопку Сохранить")
    public void clickButtonSavePeriod() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Проверка создания / редактирования периода")
    public void equalsAddEditPeriod() {
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

    @Step("Клик на кнопку Редактирования созданого периода")
    public void clickButtonEditPeriod() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).clear();
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//*[text()='" + nameEe + "']/..//a")).click();
    }

    @Step("Клик на кнопку Обновить")
    public void clickButtonUpdatePeriod() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Использование нового периода")
    public void usageNewPeriod() {

        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();      //  клик по меню лоты
        $(By.xpath("//select[@id='auction_id']")).click();
        $(By.xpath("//select[@id='auction_id']/option[@value='31']")).click();
        sleep(8000);
        $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[2]//a[2]")).click(); //  редактировать

        $(By.name("period_id")).click();                                              // добавить в лот новую категорию
        size = $$(By.xpath("//select[@name='period_id']/option")).size();
        $(By.xpath("//select[@name='period_id']/option[contains(text(), '" + nameEe + "')]")).click();
        System.out.println(size);
        tempName = $(By.name("period_id")).getSelectedText();
        System.out.println("Temp name =" + tempName);
        System.out.println("NameEE =" + nameEe);
        if (tempName.equals(nameEe) != true) {
            Allure.attachment("Результат", ">>> Невозможно выбрать созданый период <<<");
            throw new Error();
        } else {
            Allure.attachment("Результат", ">>> Новый период успешно добавленая в лот <<<");
        }
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();// клик по кнопке обновить
//        $(By.xpath("//li[@class='nav-item d-none d-lg-block']/a")).click();  // Перейти на сайт
        open("https://coins.dd-dev.club/auction/show/31");
        $(By.xpath("//div[@class='category__cont'][4]")).shouldBe(visible);
        Boolean visible = $(By.xpath("//div[@class='category__cont'][4]//span[@class='ng-binding']")).isDisplayed();
        if (visible == true){
            $(By.xpath("//div[@class='category__cont'][4]//span[@class='ng-binding']")).click(); // показать все категории
            sleep(1000);
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
    public void delPeriod() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).click();
    }

    @Step("Проверка удаления периода")
    public void equalsDelPeriod() {
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
