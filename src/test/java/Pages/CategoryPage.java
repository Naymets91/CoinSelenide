package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CategoryPage extends Page {

    Integer sizeRandom;
    String nameEe;
    String nameRu;
    String tempName;
    String rangomNameCategory;

    @Step("Клик на кнопку Добавить категорию")
    public void clickButtonAddCategoty() {
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();// Нажать на кнопку добавить категорию
    }

    @Step("Заполнения полей")
    public void fillCategory() {
        sizeRandom = getRandomNumber(50, 100);
        setNameDescrintion("Ee", "name", "description");
        nameEe = $(By.name("name")).getAttribute("value");;
        setNameDescrintion("En", "___name[en]", "___description[en]");
        setNameDescrintion("Ru", "___name[ru]", "___description[ru]");
        $(By.id("sort")).clear();
        $(By.id("sort")).sendKeys("" + sizeRandom);
    }


    public String setNameDescrintion(String lang, String xPathName, String xPathDescription) {
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
            rangomNameCategory = String.valueOf(randomStringEE(7));
            nameEe = String.valueOf(rangomNameCategory);
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameCategory);
            $(By.id("description")).clear();
            $(By.id("description")).sendKeys(rangomNameCategory);
            Allure.attachment("Имя/описания категории Ee", "Имя =  " + rangomNameCategory + "   Описание =  " + rangomNameCategory );

        }
        if (langEn.equals(lang)) {
            rangomNameCategory = String.valueOf(randomStringEN(8));
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameCategory);
            $(By.name(xPathDescription)).clear();
            $(By.name(xPathDescription)).sendKeys(rangomNameCategory);
            Allure.attachment("Имя/описания категории Еn ", "Имя = " + rangomNameCategory + "  Описание = " + rangomNameCategory );
        }
        if (langRu.equals(lang)) {
            rangomNameCategory = String.valueOf(randomStringRU(5));
            nameRu = rangomNameCategory;
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameCategory);
            $(By.name(xPathDescription)).clear();
            $(By.name(xPathDescription)).sendKeys(rangomNameCategory);
            Allure.attachment("Имя/описания категории Ru ", "Имя = " + rangomNameCategory + "   Описание =  " + rangomNameCategory );
        }
        return rangomNameCategory ;
    }

    @Step("Клик на кнопку Сохранить")
    public void clickButtonSaveCategoty() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Проверка создания категории / редактирования категории")
    public void equalsAddEditCategory() {
      $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
      sleep(1000);
       Boolean name = find((By.xpath("//*[text()='"+nameEe+"']")));
if (name == true){
    System.out.println("Елемент успешно создан/редактирован");
    Allure.attachment("Результат", ">>> Елемент успешно создан/редактирован <<<" );
} else {
    System.out.println("Елемент не создан/не редактирован ");
    Allure.attachment("Результат", ">>> Елемент не создан/не редактирован  <<<" );
    throw new Error();
}
    }

    @Step("Клик на кнопку Редактирования созданой катеории ")
    public void clickButtonEditCategoty() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).clear();
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//*[text()='"+nameEe+"']/..//a")).click();
    }

    @Step("Клик на кнопку Обновить")
    public void clickButtonUpdateCategoty() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Использование новой категории")
    public void usageNewCategory() {

        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();      //  клик по меню лоты
        $(By.xpath("//select[@id='auction_id']")).click();
        $(By.xpath("//select[@id='auction_id']/option[@value='31']")).click();
        sleep(8000);
        $(By.xpath("//*[@id='dataTablesLot']/tbody/tr//a[2]")).click(); //  редактировать

        $(By.name("category_id")).click();                                              // добавить в лот новую категорию
        size = $$(By.xpath("//select[@name='category_id']/option")).size();
        $(By.xpath("//select[@name='category_id']/option[contains(text(), '" + nameEe +"')]")).click();
        System.out.println(size);
        tempName = $(By.name("category_id")).getSelectedText();
        System.out.println("Temp name =" + tempName);
        System.out.println("NameEE =" + nameEe);
        if (tempName.equals(nameEe) != true) {
            Allure.attachment("Результат", ">>> Невозможно выбрать созданую категорию <<<");
            throw new Error();
            }
        else {Allure.attachment("Результат", ">>> Новая категория успешно добавленая в лот <<<" );}
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();// клик по кнопке обновить
        open("https://coins.dd-dev.club/auction/show/31");
        $(By.xpath("//div[@class='auction-filter__wrapp dash-filter__wrapp filter-mob']")).shouldBe(visible);

        Boolean visible = $(By.xpath("//a[@class='category__resset ng-binding ng-scope']/span")).isDisplayed();
        System.out.println( "Кнопка найдена? = " + visible);
        if (visible == true){
            $(By.xpath("//a[@class='category__resset ng-binding ng-scope']/span")).click(); // показать все категории
            sleep(1000);
            System.out.println( "Кнопка показать больше найдена " );
        }

        Boolean nameBool = $(By.xpath("//*[text()='"+nameRu+"']")).exists();
        System.out.println("NameRu = " + nameRu);
        System.out.println("Name = " + nameBool);
        if (nameBool == true){
            System.out.println(">>> Елемент присутствует в фильтре <<<");
            Allure.attachment("Результат", ">>> Елемент присутствует в фильтре <<<" );
        } else {
            System.out.println("Елемент отсутствует в фильтре");
            Allure.attachment("Результат", ">>> Елемент отсутствует в фильтре <<<" );
            throw new Error();
        }

    }


    @Step("Удаления созданой категории")
    public void delCategory() {
        System.out.println(nameEe);
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).click();
    }
    @Step("Проверка удаления категории")
    public void equalsDelCategory() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        Boolean nameB = $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).isDisplayed();
        System.out.println("Поиск удаления = "+ nameB);
        if (nameB != true){
            System.out.println("Елемент успешно удален");
            Allure.attachment("Результат", ">>> Елемент успешно удален <<<" );
        } else {
            System.out.println("Елемент не создан/не редактирован ");
            Allure.attachment("!!Результат!!", ">>> Елемент не удален  <<<" );
            throw new Error();
        }
    }





}
