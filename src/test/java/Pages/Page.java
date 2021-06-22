package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class Page {


    public void createEE(String name, int language) {
        $(By.name(name)).clear();
        $(By.name(name)).sendKeys(randomStringEE(language));
//        System.out.println("Имя лота " + language + temp);
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
//        temp = String.valueOf(randString);
        return randString;
    }

    public boolean finde (By locator) {
        try {
            $(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
