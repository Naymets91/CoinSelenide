package Pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class InvoicePage extends Page {

  Boolean processingFee;
  Boolean insurance;
  Boolean nds;

  Double deliveryDooble;
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
        if (nds == true){
        String siteSoumCommissionNotNDS = $(By.xpath("//tr[@id='commission_without_vat']/th[@class='commission']")).getText();
        siteSoumCommissionNotNDS = siteSoumCommissionNotNDS.substring(0, siteSoumCommissionNotNDS.indexOf(" €"));
        print("Комиссионные без НДС указана на сайте = " + siteSoumCommissionNotNDS);
        if (soumCommissionNotNDS.equals(siteSoumCommissionNotNDS)){
            print("Комиссионные без НДС на сайте верно подсчитаны");
            System.out.println();
        }else {
            print("!!!!!!Комиссионные без НДС на сайте не верно подсчитаны!!!!!!!");
            throw new Error();
        }
       } else if (nds ==false){
            String siteSoumCommissionNotNDS = $(By.xpath("//tr[@id='only_commission']/th[@class='commission']")).getText();
            siteSoumCommissionNotNDS = siteSoumCommissionNotNDS.substring(0, siteSoumCommissionNotNDS.indexOf(" €"));
            print("Комиссионные указана на сайте = " + siteSoumCommissionNotNDS);
            if (soumCommissionNotNDS.equals(siteSoumCommissionNotNDS)){
                print("Комиссионные на сайте верно подсчитаны");
                System.out.println();
            }else {
                print("!!!!!!Комиссионные на сайте не верно подсчитаны!!!!!!!");
                throw new Error();
            }
       }
    }
    @Step ("20% НДС на Комиссионные ")
    public void checkFinalCommission20NDS() {
        if (nds == true) {
        System.out.println("<<<20% НДС на Комиссионные>>>");
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
       if (nds == true) {
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
           if (String.valueOf(delivery).equals(siteDelivery)) {
               print("Цена доставки указана на сайте верно указаны");
               System.out.println();
           } else {
               print("!!!!!!Цена доставки указана на сайте не верно подсчитана!!!!!!!");
               throw new Error();
           }
       }
         if (nds == false) {
            }
            if (temp.equals("Omniva (Läti, Leedu)")) {deliveryDooble = 12.50;}
            if (temp.equals("Omniva")) {deliveryDooble = 5.83;}
            if (temp.equals("Tähitult postisaadetis")) {deliveryDooble = 5.83;}
            if (temp.equals("Rahvusvaheline postisaadetis")) {deliveryDooble = 12.50;}
            if (temp.equals("FedEx kullersaadetis")) {deliveryDooble = 33.33;}
            print("Цена доставки = " + deliveryDooble);
            String siteDelivery = $(By.xpath("//th[@id='delivery_cost']")).getText();
            siteDelivery = siteDelivery.substring(0, siteDelivery.indexOf(" €"));
            print("Цена доставки указана на сайте = " + siteDelivery);
            if (String.valueOf(deliveryDooble).equals(siteDelivery)) {
                print("Цена доставки указана на сайте верно указаны");
                System.out.println();
            } else {
                print("!!!!!!Цена доставки указана на сайте не верно подсчитана!!!!!!!");
                throw new Error();
            }
        }


    @Step("Проверка статуса счета")
    public void checkStatus() {
        System.out.println("");
       String processiongFeString;
       processiongFeString = $(By.xpath("//th[@id='handing_fee']")).getText();
       processiongFeString = processiongFeString.substring(0, processiongFeString.indexOf(" €"));
       if (processiongFeString.equals("0.00"))
       {
           processingFee = false ;
           print("Чекбокс \"Плата за обработку\" не активный ");
       } else {processingFee = true ;
           print("Чекбокс \"Плата за обработку\"  активный ");}

            nds = $(By.xpath("//th[@id='commission_VAT']")).isDisplayed();
             if ( nds == true ) {print("Чекбокс \"НДС\" активный ");}
             else if (nds == false) {print("Чекбокс \"НДС\" не активный ");}
             String insuranceString ;
             insuranceString= $(By.xpath("//th[@id='handing_fee']")).getText();
             insuranceString = insuranceString.substring(0, insuranceString.indexOf(" €"));
            if (insuranceString.equals("0.00"))
            {insurance = false ;
            print("Чекбокс \"Страховка\" не активный ");
            } else {insurance = true ;
            print("Чекбокс \"Страховка\" активный ");}
        System.out.println("");
       }

    }

