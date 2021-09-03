package Pages;

import Config.Values;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.*;

public class Page {
    Integer size;
    public String tempStrPage;

    @Step("Переход на сайт coins.dd-dev.club")
    public void openHomePage() {
        open(Values.homePage);
    }

    @Step("Переход на сайт Ukr.net")
    public void openUkrnetPage() {
        open(Values.ukrnetPage);
    }

    public void setInput () {

}

    public boolean find(By locator) {
        if($$(locator).size() != 0){
            System.out.println("true");
            return true;
        } else{
            System.out.println("false");
            return false;
        }
    }
    public boolean findIfXpath(String xpath){
        if($$(By.xpath(xpath)).size() != 0){
            System.out.println("true");
            return true;
        }else{
            System.out.println("false");
            return false;
        }
    }

    public void switcToWindowsTab(int index) {
        switchTo().window(index);       // переключения на 2 вкладку
    }

    public void createEN(String name, int language) {
        $(By.name(name)).clear();
        $(By.name(name)).sendKeys(randomStringEN(language));
//        System.out.println("Имя лота " + language + temp);
    }
    public void createRU(String name, int language) {
        $(By.name(name)).clear();
        $(By.name(name)).sendKeys(randomStringRU(language));
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

        return randString;
    }

    public void createEE(String name, int language) {
        $(By.name(name)).clear();
        $(By.name(name)).sendKeys(randomStringEE(language));
//        System.out.println("Имя лота " + language + temp);
    }

    public boolean finde (By locator) {
        try {
            $(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
public String datePlus(int rtime) {
    Date dateNow = new Date();
    SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy/MM/");
    SimpleDateFormat formatForDateNow2 = new SimpleDateFormat("dd");

    String r2 = formatForDateNow2.format(dateNow) ;
    int date = Integer.parseInt(r2);
    date = date + rtime;
    System.out.println(date);
    String strDate = Integer.toString(date);
//    String r = formatForDateNow.format(dateNow);
//    String t = " 21:12:00" ;
//    String rx = r + strDate + t;
//    System.out.println(rx);
    return strDate ;
//    System.out.println(rx);
}




}
