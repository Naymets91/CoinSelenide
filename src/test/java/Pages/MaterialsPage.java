package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class MaterialsPage  extends Page{
    Integer sizeRandom;
    String nameEe;
    String nameRu;
    String tempName;
    String rangomNameMaterials;

    @Step("Клик на кнопку Добавить материал")
    public void clickButtonAddMaterials() {
        $(By.xpath("//div[@class='card-body card-dashboard']//a")).click();// Нажать на кнопку добавить номинал
    }


    @Step("Заполнения полей")
    public void fillMaterials() {
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
            rangomNameMaterials = String.valueOf(randomStringEE(7));
            nameEe = String.valueOf(rangomNameMaterials);
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameMaterials);
            Allure.attachment("Название номинала  Ee", "Имя =  " + rangomNameMaterials);
        }
        if (langEn.equals(lang)) {
            rangomNameMaterials = String.valueOf(randomStringEN(8));
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameMaterials);
            Allure.attachment("Название номинала  Еn ", "Имя = " + rangomNameMaterials);
        }
        if (langRu.equals(lang)) {
            rangomNameMaterials = String.valueOf(randomStringRU(5));
            nameRu = rangomNameMaterials;
            $(By.xpath("//ul[@class='dropdown-menu show']/li[" + temp + "]")).click();
            $(By.name(xPathName)).clear();
            $(By.name(xPathName)).sendKeys(rangomNameMaterials);
            Allure.attachment("Название номинала Ru ", "Имя = " + rangomNameMaterials);
        }
        return rangomNameMaterials;
    }

    @Step("Клик на кнопку Сохранить")
    public void clickButtonSaveMaterials() {
        $(By.xpath("//button[@class='mt-2 btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Проверка создание/ редактирование материала ")
    public void equalsAddEditMaterials() {
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

    @Step("Клик на кнопку Редактирования созданого материала")
    public void clickButtonEditMaterials() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).clear();
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//*[text()='" + nameEe + "']/..//a")).click();
    }

    @Step("Клик на кнопку Обновить")
    public void clickButtonUpdateMaterials() {
        $(By.xpath("//button[@class='mt-2 btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
    }


    @Step("Использование нового материла")
    public void usageNewMaterials() {

        $(By.xpath("//ul[@id='main-menu-navigation']/li[4]")).click();  //  клик по меню аукционы
        $(By.xpath("//*[@id='main-menu-navigation']/li[4]//li[2]//span")).click();      //  клик по меню лоты
        $(By.xpath("//select[@id='auction_id']")).click();
        $(By.xpath("//select[@id='auction_id']/option[@value='31']")).click();
        sleep(8000);
        $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[4]//a[2]")).click(); //  редактировать

        $(By.name("material_id")).click();                                              // добавить в лот новый материал
        size = $$(By.xpath("//select[@name='material_id']/option")).size();
        $(By.xpath("//select[@name='material_id']/option[contains(text(), '" + nameEe + "')]")).click();
        System.out.println(size);
        tempName = $(By.name("material_id")).getSelectedText();
        System.out.println("Temp name =" + tempName);
        System.out.println("NameEE =" + nameEe);
        if (tempName.equals(nameEe) != true) {
            Allure.attachment("Результат", ">>> Невозможно выбрать созданый материал <<<");
            throw new Error();
        } else {
            Allure.attachment("Результат", ">>> Новый материал успешно добавленая в лот <<<");
        }
        $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();// клик по кнопке обновить
        open("https://coins.dd-dev.club/auction/show/31");
        $(By.xpath("//div[@class='auction-filter__wrapp dash-filter__wrapp filter-mob']")).shouldBe(visible);
        Boolean visible = $(By.xpath("//div[@class='category__cont'][2]//span[@class='ng-binding']")).isDisplayed();
        if (visible == true){
            $(By.xpath("//div[@class='category__cont'][2]//span[@class='ng-binding']")).click(); // показать все категории
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


    @Step("Удаления созданого материала")
    public void delMaterials() {
        $(By.xpath("//div[@id='dataTable_filter']//input")).sendKeys(nameEe);
        sleep(1000);
        $(By.xpath("//button[@class='btn table-btn_ico btn-danger waves-effect waves-light']")).click();
    }

    @Step("Проверка удаления материала")
    public void equalsMaterials() {
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
