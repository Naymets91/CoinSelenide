package Pages;


import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;


import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;


public class LotiPagePage extends Page {
    boolean tempBool;


    String temp;
    String parsName;
    String eeNameLot;

    Integer size;
    Integer sizeRandom;

    List<String> before = new ArrayList<>();
    List<String> after = new ArrayList<>();


////////////////////////////////////////// createLot ////////////////////////////////////
@Step("Нажатия на кнопку создать лот")
    public void buttonCreateLot() {
        $(By.id("create_lot")).click(); //нажать кнопку создать лот
    }


    public void name(int ee, int en, int ru) {
        createEE("title", ee);      // Заполнение поля имя рандомными символами на эстонском
        createEN("___title[en]", en);       // Заполнение поля имя рандомными символами на английско
        createRU("___title[ru]", ru);       // Заполнение поля имя рандомными символами на русском
        System.out.println();
    }

    public void descrintion(int ee, int en, int ru) {
        $(By.xpath("//div[@class='col-12']/textarea[1]")).clear();
        $(By.xpath("//div[@class='col-12']/textarea[1]")).sendKeys(randomStringEE(ee)); // Заполнение поля описания рандомными символами на эстонском
//        System.out.println("Описание лота ee " + temp);

        createEN("___description[en]", en);     // Заполнение поля описания рандомными символами на английском
        createRU("___description[ru]", ru);     // Заполнение поля описания рандомными символами на русском
        System.out.println();

    }


    public void sender() {                  // Сдатчик
        randomLotiSelect("deliverer_id", "Сдатчик", 2); // рандомное заполнения здатчика
    }

    public void country() {                // Страна
        randomLotiSelect("country_id", "Страна", 2);
    }

    public void category() {               // Категория
        randomLotiSelect("category_id", "Категория", 2);
    }

    // Год
    public void year() {
        String r = "2021";
        $(By.id("year")).sendKeys(r);
//        System.out.println("Год   " + r);
    }

    public void minPrice() {               // Минимальная цена
        String r = "50";
        $(By.name("min_price")).click();
        $(By.name("min_price")).clear();
        $(By.name("min_price")).sendKeys(r);
//        System.out.println("Минимальная цена   " + r);
    }

    public void startPrice() {             // Стартовая цена
        String r = "20";
        $(By.id("start_price")).sendKeys(r);
//        System.out.println("Стартовая цена   " + r);
    }

    public void material() {             // Материал
        randomLotiSelect("material_id", "Материал", 2);
    }

    public void denomination() {               // Номинал
        randomLotiSelect("nominal_id", "Номинал", 2);
    }

    public void period() {               // Период
        randomLotiSelect("period_id", "Период", 2);
    }


    public void topLot() {                      // Топ-лот
        randomLotiSelect("top_lot", "Топ-лот", 1);
    }

    public void certification() {                      // Сертификация
        randomLotiSelect("certification_id", "Сертификация", 2);
    }

    public void maxSafety() {                      // Максимальная сохранность
        randomLotiSelect("is_max_safety", "Максимальная сохранность", 1);
    }

    public void reve() {                      // Редкий
        randomLotiSelect("is_rare", "Редкий", 1);
    }

    public void safety() {                      // Сохранность
        String r = "20";
        $(By.id("safety")).sendKeys(r);
//        System.out.println("Сохранность " + r);
    }

    @Step("Нажатия на кнопку сохранить")
    public void buttonSave() {                // кнопка сохранить
        parsName = $(By.xpath("//input[@id='article']")).getAttribute("value");
        $(By.xpath("//button[@type='submit']")).click();
    }
    @Step("Проверка создания лота")
    public void findLot() {
        sleep(6000);
        $(By.xpath("//input[@class='form-control form-control-sm']")).sendKeys(eeNameLot);
        sleep(6000);
        finndSizeTrue(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']"),
                "Лот найден",
                "!!Лот не найден");

    }
    @Step("Проверка на удаления лота")
    public void findDelLot() {
        sleep(7000);
        $(By.xpath("//input[@class='form-control form-control-sm']")).sendKeys(eeNameLot);
        sleep(7000);
        finndSizeFalse(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']"),
                "Лот удален",
                "!!Лот не удален");

    }

///////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////             EditLot          /////////////////////////////////

    @Step("Нажатия на кнопку редактировать")
    public void buttonEditLot() {
        $(By.xpath("//input[@type='search']")).sendKeys(eeNameLot);
        sleep(4000);
        $(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']")).click();
    }

    @Step("Парсинг лотов до редактирования")
    public void parsLotBefore() {
        before.add($(By.name("title")).getAttribute("value"));
        before.add($(By.name("___title[en]")).getAttribute("value"));
        before.add($(By.name("___title[ru]")).getAttribute("value"));
        before.add($(By.xpath("//div[@class='col-12']/textarea[1]")).getAttribute("value"));
        before.add($(By.name("___description[en]")).getAttribute("value"));
        before.add($(By.name("___description[ru]")).getAttribute("value"));
        before.add($(By.name("deliverer_id")).getSelectedText());
        before.add($(By.name("country_id")).getSelectedText());
        before.add($(By.name("category_id")).getSelectedText());
        before.add($(By.id("year")).getAttribute("value"));
        before.add($(By.name("min_price")).getAttribute("value"));
        before.add($(By.name("start_price")).getAttribute("value"));
        before.add($(By.name("material_id")).getSelectedText());
        before.add($(By.name("nominal_id")).getSelectedText());
        before.add($(By.name("period_id")).getSelectedText());
        before.add($(By.id("top_lot")).getSelectedText());
        before.add($(By.name("certification_id")).getSelectedText());
        before.add($(By.id("is_max_safety")).getSelectedText());
        before.add($(By.id("is_rare")).getSelectedText());
        before.add($(By.name("safety")).getAttribute("value"));

        System.out.println(before.size());
        for (String s : before) {
            System.out.println(s);
        }
        Allure.attachment("Результат", String.valueOf(before));
    }
    @Step("Парсинг данных о лоте")
    public void parsLotAfter() {
        after.add("Название [ee] = " + $(By.name("title")).getAttribute("value"));
        after.add("Название [en] = " + $(By.name("___title[en]")).getAttribute("value"));
        after.add("Название [ru] = " + $(By.name("___title[ru]")).getAttribute("value"));
        after.add("Описание [ee] = " + $(By.xpath("//div[@class='col-12']/textarea[1]")).getAttribute("value"));
        after.add("Описание [en] = " + $(By.name("___description[en]")).getAttribute("value"));
        after.add("Описание [ru] = " + $(By.name("___description[ru]")).getAttribute("value"));
        after.add("Сдатчик = " + $(By.name("deliverer_id")).getSelectedText());
        after.add("Страна = " + $(By.name("country_id")).getSelectedText());
        after.add("Категория = " + $(By.name("category_id")).getSelectedText());
        after.add("Год выпуска = " + $(By.id("year")).getAttribute("value"));
        after.add("Минимальная цена = " + $(By.name("min_price")).getAttribute("value"));
        after.add("Стартовая цена = " + $(By.name("start_price")).getAttribute("value"));
        after.add("Материал = " + $(By.name("material_id")).getSelectedText());
        after.add("Номинал = " + $(By.name("nominal_id")).getSelectedText());
        after.add("Период = " + $(By.name("period_id")).getSelectedText());
        after.add("Топ-лот = " + $(By.id("top_lot")).getSelectedText());
        after.add("Сертификация = " + $(By.name("certification_id")).getSelectedText());
        after.add("Максимальная сохранность = " + $(By.id("is_max_safety")).getSelectedText());
        after.add("Редкий = " + $(By.id("is_rare")).getSelectedText());
        after.add("Сохранность = " + $(By.name("safety")).getAttribute("value"));
        System.out.println(after.size());
        Allure.attachment("Результат", String.valueOf(after));
        for (String s : after) {
            System.out.println(s);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////// delete Lote///////////////////////////////////
@Step("Удаления созданного лота")
    public void delete() {
        $(By.xpath("//input[@type='search']")).sendKeys(eeNameLot);
        $(By.xpath("//a[@class='btn table-btn_ico btn-danger waves-effect waves-light btn-delete']")).click();
        $(By.xpath("//button[@class='btn btn-danger confirm-delete-btn waves-effect waves-light']")).click();
    }

///////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////// checkingExistencelots //////////////////////////

    public void checkingExistencelots() {
        $(By.xpath("//tr[@class='odd']")).click();
        size = $$(By.xpath("//td[@class='reorder sorting_1']")).size();
        System.out.println(size);
        if (size == 0) {
            buttonCreateLot();
            name(12, 10, 8);
            descrintion(20, 12, 10);
            sender();
            country();
            category();
            year();
            minPrice();
            startPrice();
            material();
            denomination();
            period();
            topLot();
            certification();
            maxSafety();
            reve();
            safety();
            buttonSave();
            $(By.xpath("//tr[@class='odd']")).click();
        }

    }

/////////////////////////////////////////////////////////////////////////////////////////////////

    @Step("сравнения данных о лоте до редактирования и после")
    public void equalsLot() {
        System.out.println(before.size());
        for (int i = 0; i < before.size(); i++) {
            for (int j = 0; j < before.size(); j++) {
                if (before.get(i).equals(after.get(j)) != true) {
                    System.out.println("Элемент " + i +
                            " первого массива равен элементу " + j + " второго массива.");
                } else {
                    System.out.println("Элемент " + i +
                            " первого массива не равен элементу " + j + " второго массива.");
                }
            }
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////



    public void randomLotiSelect(String nameId, String printName, int startRandomNumber) {
        $(By.name(nameId)).click();
        size = $$(By.xpath("//select[@name='" + nameId + "']/option")).size();
        size = getRandomNumber(startRandomNumber, size);
        $(By.xpath("//select[@name='" + nameId + "']/option[" + size + "]")).click();
//        temp = $(By.name(nameId)).getSelectedText();
//        System.out.println(printName + "     " + temp);
    }


    public void addManyLoti(Integer r) {
for(int i=0; i==r; i++){
    $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[1]//a[3]")).click();
    $(By.xpath("//button[@class='btn btn-info waves-input-wrapper waves-effect waves-light']")).click();
}

    }
    @Step("Заполнения данных о лоте")
    public void fillLot() {
        name(12, 15, 10);
        descrintion(20, 30, 25);
        sender();
        country();
        category();
        year();
        minPrice();
        startPrice();
        material();
        denomination();
        period();
        topLot();
        certification();
        maxSafety();
        reve();
        eeNameLot = $(By.name("title")).getAttribute("value") ;
        System.out.println("имя созданого/редактированного лота" + eeNameLot);
        safety();
    }


}
