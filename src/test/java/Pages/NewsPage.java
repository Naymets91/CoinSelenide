package Pages;

import org.openqa.selenium.By;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class NewsPage extends Page {

    Integer sizeRandom;
    String nameEe;
    String nameRu;
    String nameEn;

    String newsEe;
    String newsRu;
    String newsEn;

    String tempName;
    String rangomNameNews;

    Boolean tempBool;

    public void clickButtonAddNews() {
        $(By.xpath("//a[@class='btn btn-primary navbar-btn waves-effect waves-light']")).click();
    }


    @Step("Заполнения полей")
    public void fillNews() {
        sizeRandom = getRandomNumber(50, 100);
        setName("Ee", "title");
        setName("En", "___title[en]");
        setName("Ru", "___title[ru]");
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
            rangomNameNews = String.valueOf(randomStringEE(7));
            nameEe = String.valueOf(rangomNameNews);
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameNews);
            Allure.attachment("Название новости  Ee", "Имя и описание = " + rangomNameNews);
            $(By.xpath("//div[@class='note-editable']")).clear();
            $(By.xpath("//div[@class='note-editable']")).sendKeys(rangomNameNews);
        }
        if (langEn.equals(lang)) {
            rangomNameNews = String.valueOf(randomStringEN(8));
            nameEn = String.valueOf(rangomNameNews);
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameNews);
            Allure.attachment("Название новости  Еn ", "Имя и описание  = " + rangomNameNews);
            $(By.xpath("//div[@class='note-editable']")).clear();
            $(By.xpath("//div[@class='note-editable']")).sendKeys(rangomNameNews);
        }
        if (langRu.equals(lang)) {
            rangomNameNews = String.valueOf(randomStringRU(5));
            nameRu = rangomNameNews;
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameNews);
            Allure.attachment("Названия новости Ru ", "Имя и описание = " + rangomNameNews);
            $(By.xpath("//div[@class='note-editable']")).clear();
            $(By.xpath("//div[@class='note-editable']")).sendKeys(rangomNameNews);
        }
        return rangomNameNews;
    }

    @Step("Клик на кнопку Сохранить")
    public void clickButtonSaveNews() {
        $(By.xpath("//button[@class='btn btn-info waves-effect waves-light']")).click();
    }
    @Step("Проверка создание/ редактирование новости")
    public void equalsAddEditNews() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
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
    @Step("Нажатие на кнопку редактировать")
    public void clickButtonEditNews() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).clear();
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//*[text()='" + nameEe + "']/..//a")).click();
    }
    @Step("Удаление новости")
    public void delNews() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).click();
    }
    @Step("Проверка удаление новости")
    public void equalsDelNews() {
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
    @Step("Переход на страницу созданой новости")
    public void openLastAddNews() {
        $(By.xpath("//*[contains(text(), '"+ nameRu +"')]/../..//div[@class='main-auction__btn btn-yel']")).click();
    }

    public void equalsInLanguages() {
        readEquals(nameRu);
        switchToLanguages("Ee");
        readEquals(nameEe);
        switchToLanguages("En");
        readEquals(nameEn);
    }

    private void switchToLanguages(String lang) {
        String langEe = "Ee";
        String langEn = "En";
        if (langEe.equals(lang)) {
            $(By.className("flag")).click();
            $(By.xpath("//div[@class='nav-lang__list']/a[2]")).click();
        }
        if (langEn.equals(lang)) {
            $(By.className("flag")).click();
            sleep(1000);
            $(By.xpath("//div[@class='nav-lang__list']/a[2]")).click();
        }
    }

    public void readEquals(String name){
        tempName = $(By.xpath("//p[@class='title']")).getText();
        tempBool = (name.equals(tempName));
        if (tempBool = true){
            System.out.println("Имя новости соответствует");
            Allure.attachment("Имя новости", nameRu + " = " + tempName);
        } else {
            System.out.println("Елемент не создан/не редактирован ");
            Allure.attachment("Имя новости", nameRu + " != " + tempName);
            throw new Error();
        }
        tempName = $(By.xpath("//div[@id='static-page-text']/p")).getText();
        tempBool = (name.equals(tempName));
        if (tempBool = true){
            System.out.println("Описания новости соответствует");
            Allure.attachment("Описания новости", nameRu + " = " + tempName);
        } else {
            System.out.println("Елемент не создан/не редактирован ");
            Allure.attachment("Описания новости", nameRu + " != " + tempName);
            throw new Error();
        }
    }
}
