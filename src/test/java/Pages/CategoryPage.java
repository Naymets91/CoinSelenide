package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;



import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CategoryPage extends Page {
    String r ="Привіт";
    Integer sizeRandom;
    String nameEe;
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
        $(By.id("sort")).sendKeys("" + sizeRandom);
    }

    @Step("Имя категории ")
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
            Allure.attachment("Результат", rangomNameCategory);
        }
        if (langEn.equals(lang)) {
            rangomNameCategory = String.valueOf(randomStringEN(8));
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameCategory);
            $(By.name(xPathDescription)).clear();
            $(By.name(xPathDescription)).sendKeys(rangomNameCategory);
            Allure.attachment("Результат", rangomNameCategory);
        }
        if (langRu.equals(lang)) {
            rangomNameCategory = String.valueOf(randomStringRU(5));
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameCategory);
            $(By.name(xPathDescription)).clear();
            $(By.name(xPathDescription)).sendKeys(rangomNameCategory);
            Allure.attachment("Результат", rangomNameCategory);
        }
        return rangomNameCategory;
    }

    @Step("Клик на кнопку Сохранить")
    public void clickButtonSaveCategoty() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Проверка создания категории / редактирования категории")
    public void equalsAddEditCategory() {
      $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
      sleep(2000);
       Boolean name = find((By.xpath("//*[text()='"+nameEe+"']")));
if (name == true){
    System.out.println("Елемент успешно создан/редактирован");
    Allure.attachment("Результат", "Елемент успешно создан/редактирован" );
} else {
    System.out.println("Елемент не создан/не редактирован ");
    Allure.addAttachment("Результат", "application/json", "!! Елемент не создан/не редактирован !!" );
    throw new Error();
}
    }

    @Step("Клик на кнопку Редактирования созданой катеории ")
    public void clickButtonEditCategoty() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).clear();
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(2000);
        $(By.xpath("//*[text()='"+nameEe+"']/..//a")).click();
    }

    @Step("Клик на кнопку Обновить")
    public void clickButtonUpdateCategoty() {
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }

    public void equalsEditCategory() {
    }
    @Step("Использование новой категории")
    public void usageNewCategory() {



    }


    @Step("Удаления созданой категории")
    public void delCategory() {
//        $(By.xpath("//*[text()='"+nameEe+"']/..//a")).click();
    }

    public void equalsDelCategory() {
    }





}
