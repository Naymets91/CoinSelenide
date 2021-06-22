package Pages;


import org.openqa.selenium.By;


import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;


public class LotiPagePage extends Page {
    boolean tempBool;

    String temp;
    String parsName;

    Integer size;
    Integer sizeRandom;

    List<String> before = new ArrayList<>();
    List<String> after = new ArrayList<>();

    public void createLot() {
        $(By.id("create_lot")).click();
    }


    public void name(int ee, int en, int ru) {
        create("title", ee);
        create("___title[en]", en);
        create("___title[ru]", ru);
        System.out.println();
    }

    public void descrintion(int ee, int en, int ru) {
        $(By.xpath("//div[@class='col-12']/textarea[1]")).clear();
        $(By.xpath("//div[@class='col-12']/textarea[1]")).sendKeys(randomStringEE(ee));
//        System.out.println("Описание лота ee " + temp);

        create("___description[en]", en);
        create("___description[ru]", ru);
        System.out.println();

    }


    public void sender() {                  // Сдатчик
        randomLotiSelect("deliverer_id", "Сдатчик", 2);
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


    public void buttonSave() {                // кнопка сохранить
        $(By.xpath("//button[@type='submit']")).click();
        sleep(5000);
        $(byText(parsName)).click();
    }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////


///////////////////////////////////////////////             EditLot          /////////////////////////////////

    public void randomEditLot() {
        $(By.xpath("//tr[@class='odd']")).click();
        size = $$(By.xpath("//td[@class='reorder sorting_1']")).size();
        System.out.println(size);
        if (size == 0) {
            createLot();
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
            size = $$(By.xpath("//td[@class='reorder sorting_1']")).size();
            sizeRandom = getRandomNumber(1, size);
            System.out.println("Вибрано лот " + sizeRandom);

        } else {
            sizeRandom = getRandomNumber(1, size);
            System.out.println("Вибрано лот для редагування " + sizeRandom);
        }

    }

    public void editLot() {
        $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[" + sizeRandom + "]//a[2]")).click();
    }


    public void parsLotBefore() {
//        $(By.xpath("//*[@id='dataTablesLot']/tbody/t")).click();

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
    }

    public void parsLotAfter() {
        after.add($(By.name("title")).getAttribute("value"));
        after.add($(By.name("___title[en]")).getAttribute("value"));
        after.add($(By.name("___title[ru]")).getAttribute("value"));
        after.add($(By.xpath("//div[@class='col-12']/textarea[1]")).getAttribute("value"));
        after.add($(By.name("___description[en]")).getAttribute("value"));
        after.add($(By.name("___description[ru]")).getAttribute("value"));
        after.add($(By.name("deliverer_id")).getSelectedText());
        after.add($(By.name("country_id")).getSelectedText());
        after.add($(By.name("category_id")).getSelectedText());
        after.add($(By.id("year")).getAttribute("value"));
        after.add($(By.name("min_price")).getAttribute("value"));
        after.add($(By.name("start_price")).getAttribute("value"));
        after.add($(By.name("material_id")).getSelectedText());
        after.add($(By.name("nominal_id")).getSelectedText());
        after.add($(By.name("period_id")).getSelectedText());
        after.add($(By.id("top_lot")).getSelectedText());
        after.add($(By.name("certification_id")).getSelectedText());
        after.add($(By.id("is_max_safety")).getSelectedText());
        after.add($(By.id("is_rare")).getSelectedText());
        after.add($(By.name("safety")).getAttribute("value"));
        System.out.println(after.size());
        for (String s : after) {
            System.out.println(s);
        }
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

/////////////////////////////////////////// delete Lote///////////////////////////////////

    public void delete() {
        temp = $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[" + sizeRandom + "]//td[4]")).getText();
        System.out.println(temp);
        $(By.xpath("//*[@id='dataTablesLot']/tbody/tr[" + sizeRandom + "]//a[4]")).click();
        $(By.xpath("//div[@class='modal modal-admin confirm-delete']//button")).should(visible).click();

        tempBool = find(By.xpath("//*[text()='" + temp + "']"));
        // $(By.xpath("//*[text()='"+ temp +"']")).should(visible);
        if (tempBool != true) {
            System.out.println("Лот удалился");
        } else {
            System.out.println("Лот не удалился");
            $(By.xpath("//*[@id='t']/t")).click();  // чтоб тест упал когда найден удаляемый лот
        }
        System.out.println(tempBool);
    }

///////////////////////////////////////////////////////////////////////////////////////////////




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

    public void create(String name, int language) {
        $(By.name(name)).clear();
        $(By.name(name)).sendKeys(randomStringEE(language));
//        System.out.println("Имя лота " + language + temp);
    }


    public int getRandomNumber(int a1, int b1) {
        int a = a1;
        int b = b1;
        int x = a + (int) (Math.random() * ((b - a) + 1));
        //System.out.println("Случайное число x: " + x);
        return x;
    }

    // Рандомное заполнения имен
    public StringBuilder randomStringEN(int s) {
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randString = new StringBuilder();
        int count = s;
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
//        System.out.print(randString);
//        temp = String.valueOf(randString);
        return randString;
    }

    public StringBuilder randomStringEE(int s) {
        String symbols = "abdefghijklmnoprsšzžtuvõäöü";
        StringBuilder randString = new StringBuilder();
        int count = s;
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
//        temp = String.valueOf(randString);
        return randString;
    }

    public StringBuilder randomStringRU(int s) {
        String symbols = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
        StringBuilder randString = new StringBuilder();
        int count = s;
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
//        temp = String.valueOf(randString);
        return randString;
    }

    public void randomLotiSelect(String nameId, String printName, int startRandomNumber) {
        $(By.name(nameId)).click();
        size = $$(By.xpath("//select[@name='" + nameId + "']/option")).size();
        size = getRandomNumber(startRandomNumber, size);
        $(By.xpath("//select[@name='" + nameId + "']/option[" + size + "]")).click();
//        temp = $(By.name(nameId)).getSelectedText();
//        System.out.println(printName + "     " + temp);
    }


}
