package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;

public class Page {

    public boolean finde (By locator) {
        try {
            $(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
