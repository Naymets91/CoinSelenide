package Pages;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class InvoicePage extends Page {

  Integer soumPriceHammer = 0;

   @Step ("Выбрант заказчик")
    public void selectCustomer(){
       sleep(3000);
    $(By.xpath("//select[@id='invoice_type']")).click();
       $(By.xpath("//select[@id='invoice_type']//option[2])")).click();
   }



   @Step("Поиск определенного клиента")
    public void search(String name){
       sleep(3000);
    $(By.xpath("//input[@class='form-control form-control-sm']")).sendKeys(name);
    }
    @Step("Нажатия на кнопку изменить")
    public void clickButtonEdit() {
       $(By.xpath("//a[@class='btn table-btn_ico btn-warning waves-effect waves-light']")).click();
    }
@Step ("проверка итоговой цены молотка")
    public void checkFinalPriceHammer() {
    Integer sizePriceHammer = $$(By.xpath("//table[@class='table dataTable']//tbody/tr")).size();
    print("Количество лотов = " + sizePriceHammer);
    System.out.println();
    Integer i = 1;
    while (i <= sizePriceHammer) {
        String priceHammer = $(By.xpath("//table[@class='table dataTable']//tbody/tr[" + i +"]/td[4]")).getText();
        String nameLot = $(By.xpath("//table[@class='table dataTable']//tbody/tr[" + i +"]/td[3]")).getText();
        print(nameLot + " = " + priceHammer);
        i = i + 1;
        Integer intPriceHammer = Integer.valueOf(priceHammer);
        soumPriceHammer = soumPriceHammer + intPriceHammer;
    }
    System.out.println();
    print("Итоговая цена молотка = " + soumPriceHammer);
    String siteSoumPriceHammer = $(By.xpath("//tfoot/tr/th[2]")).getText();
    siteSoumPriceHammer = substr(siteSoumPriceHammer, 3 );
    print("Итоговая цена молотка указана на сайте = " + siteSoumPriceHammer);
    }
}
