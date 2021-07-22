package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import static com.codeborne.selenide.Condition.visible;
import java.sql.Struct;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class CategoryPage extends Page {
    Integer sizeRandom;
    String nameEe;

    @Step("Добавить категорию")
    public void addCategory() {
        sizeRandom = getRandomNumber(50, 100);
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();// Нажать на кнопку добавить категорию
        setNameDescrintion("Ee", "name", "description");
        nameEe = $(By.name("name")).getAttribute("value");;
        setNameDescrintion("En", "___name[en]", "___description[en]");
        setNameDescrintion("Ru", "___name[ru]", "___description[ru]");
        $(By.id("sort")).sendKeys("" + sizeRandom);
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
        System.out.println("NameEe = " +  nameEe);
    }


    private void setNameDescrintion(String lang, String name, String description) {
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
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(name)).sendKeys("fdfdfvd");
            $(By.id("description")).sendKeys("dfbghj");
        }
        if (langEn.equals(lang) || langRu.equals(lang)) {
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(name)).sendKeys("fdfdfvd");
            $(By.name(description)).sendKeys("dfbghj");
        }
    }

    @Step("Проверка создания категории")
    public void equalsAddCategory() {

      $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
      sleep(2000);
       Boolean name = find((By.xpath("//*[text()='"+nameEe+"']")));
if (name == true){
    System.out.println("Елемент успешно создан");
} else {
    System.out.println("Елемент не создан");
    throw new Error();
}
    }

    public void editCategory() {
    }

    public void equalsEditCategory() {
    }

    public void delCategory() {
    }

    public void equalsDelCategory() {
    }

    public void usageNewCategory() {
    }
}
