package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class InvoicePage extends Page {

  Integer delivery ;
  Integer tempInteger = 0;
  Double tempDooble = 0.0;

  Double soumCommissionNDS;
  String soumCommissionNotNDS;
  String soumCommission20NDS;

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
    System.out.println("<<<проверка итоговой цены молотка>>>");
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
        tempInteger = tempInteger + intPriceHammer;
    }
    String soumPriceHammer = String.valueOf(tempInteger);
    System.out.println();
    print("Итоговая цена молотка = " + soumPriceHammer);
    String siteSoumPriceHammer = $(By.xpath("//th[@id='turnover']")).getText();
    siteSoumPriceHammer = siteSoumPriceHammer.substring(0, siteSoumPriceHammer.indexOf("."));
    print("Итоговая цена молотка указана на сайте = " + siteSoumPriceHammer);
   if (soumPriceHammer.equals(siteSoumPriceHammer)) {
       print("Итоговая цена на сайте верно подсчитана");
       System.out.println();
    }else {
       print("!!!!!!Итоговая цена на сайте не верно подсчитана!!!!!!!");
       throw new Error();
   }
    }


    @Step ("Комиссионные без НДС")
    public void checkFinalCommissionNotNDS() {
        System.out.println("<<<Комиссионные без НДС>>>");
        Integer sizeLots = $$(By.xpath("//table[@class='table dataTable']//tbody/tr")).size();
        print("Количество лотов = " + sizeLots);
        System.out.println();
        Integer i = 1;
        while (i <= sizeLots) {
            String priceHammer = $(By.xpath("//table[@class='table dataTable']//tbody/tr[" + i +"]/td[6]")).getText();
            String nameLot = $(By.xpath("//table[@class='table dataTable']//tbody/tr[" + i +"]/td[3]")).getText();
            print(nameLot + " = " + priceHammer);
            i = i + 1;
            Double intPriceHammer = Double.valueOf(priceHammer);
            tempDooble = tempDooble + intPriceHammer;
        }
        soumCommissionNDS = tempDooble;
        tempDooble = tempDooble / 1.20 ;
        soumCommissionNotNDS = String.format("%.2f", tempDooble).replace(',', '.');
        System.out.println();
        print("Комиссионные без НДС = " + soumCommissionNotNDS);
        String siteSoumCommissionNotNDS = $(By.xpath("//tr[@id='commission_without_vat']/th[2]")).getText();
        siteSoumCommissionNotNDS = siteSoumCommissionNotNDS.substring(0, siteSoumCommissionNotNDS.indexOf(" €"));
        print("Комиссионные без НДС указана на сайте = " + siteSoumCommissionNotNDS);
        if (soumCommissionNotNDS.equals(siteSoumCommissionNotNDS)){
            print("Комиссионные без НДС на сайте верно подсчитаны");
            System.out.println();
        }else {
            print("!!!!!!Комиссионные без НДС на сайте не верно подсчитаны!!!!!!!");
            throw new Error();
        }
    }
    @Step ("20% НДС на Комиссионные ")
    public void checkFinalCommission20NDS() {
        System.out.println("<<<20% НДС на Комиссионные>>>");
        boolean nds = findIfXpath("//th[@id='commission_VAT']");
        System.out.println("NDS = " + nds);
        if (nds = true) {
        tempDooble = soumCommissionNDS - Double.valueOf(soumCommissionNotNDS);
        soumCommission20NDS = String.format("%.2f", tempDooble).replace(',', '.');
        print("20% НДС на Комиссионные = " + soumCommission20NDS);
        String siteSoumCommission20NDS = $(By.xpath("//th[@id='commission_VAT']")).getText();
        siteSoumCommission20NDS = siteSoumCommission20NDS.substring(0, siteSoumCommission20NDS.indexOf(" €"));
        print("20% НДС на Комиссионные указаны на сайте = " + siteSoumCommission20NDS);
        if (soumCommission20NDS.equals(siteSoumCommission20NDS)){
            print("20% НДС на Комиссионные на сайте верно подсчитаны");
            System.out.println();
        }else {
            print("!!!!!!20% НДС на Комиссионные на сайте не верно подсчитана!!!!!!!");
            throw new Error();
        }
        }
    }
    @Step ("Цена доставки ")
    public void checkFinalDelivery() {
        System.out.println("<<<Цена доставки>>>");
        String temp = $(By.xpath("//input[@id='invoice_delivery']")).getAttribute("value");
        if (temp.equals("SmartPost Itella")) {delivery = 7;}
        if (temp.equals("Omniva (Läti, Leedu)")) {delivery = 15;}
        if (temp.equals("Omniva")) {delivery = 7;}
        if (temp.equals("Tähitult postisaadetis")) {delivery = 7;}
        if (temp.equals("Rahvusvaheline postisaadetis")) {delivery = 15;}
        if (temp.equals("FedEx kullersaadetis")) {delivery = 40;}

        print("Цена доставки = " + delivery);
        String siteDelivery = $(By.xpath("//th[@id='delivery_cost']")).getText();
        siteDelivery = siteDelivery.substring(0, siteDelivery.indexOf("."));
        print("Цена доставки указана на сайте = " + siteDelivery);
        if (String.valueOf(delivery).equals(siteDelivery)){
            print("Цена доставки указана на сайте верно указаны");
            System.out.println();
        }else {
            print("!!!!!!Цена доставки указана на сайте верно указаны не верно подсчитана!!!!!!!");
            throw new Error();
        }
    }


}
