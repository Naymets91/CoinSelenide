public class Shpargalka { }

  /*

  import org.openqa.selenium.chrome.ChromeOptions;

        Configuration.timeout = 20000;              // таймаут ожидание времени
        Configuration.startMaximized = true;        // розвернуть окно браузера на весь екран
        ChromeOptions options = new ChromeOptions();    // Отключить всплывающие окна
        options.addArguments("test-type");              // Отключить всплывающие окна
        options.addArguments("disable-popup-blocking"); // Отключить всплывающие окна



    $(By.xpath("//table[@id='dataTable']//tbody//td[6]/a")).click();        // нажать на кнопку
    с = $$(By.xpath("//div[@class='auction-ithem']")).size()                    //  узнать количество елементов
/////////////////////////////////////////////////////////////////

if ($$(By.xpath("//div[@class='auction-ithem']")).size() != 0) {
            System.out.println("Аукцион не остановлен");
            throw new Error();                                         // остановить тест с ошибкой + текст ошибки
        } else {
            System.out.println("Аукцион успершно остановлен");
        }



///////////////////////////////////////////////////////////
   $(By.name("___description[ru]")).getAttribute("value");        // узнать текс елемента
        $(By.name("deliverer_id")).getText();                      // узнать текс елемента 2 способ

        $(By.name("deliverer_id")).getSelectedText();       // узнать текст вибраного селектора



///////////////////////////////

tempBool = $(By.xpath("//*[text()='тут нужный текст]"));  // поиск по нужному тексту

//////////////////////////////



public static final String user1_email = "емаил";           // если так написать то можно получить текст переменной в любом классе
public static final String user1_password = "пароль123";  // если так написать то можно получить текст переменной в любом классе

loginPg.loginUser(Values.user1_email, Values.user1_password); // визов переменнои в другом классе (Values ето имя класа в котором находится переменная)

///////////////////////////////////////////////


public int getRandomNumber(int a1, int b1) {    // рандомний генератор чисел  нужно подать диапазон чисел
        int a = a1;
        int b = b1;
        int x = a + (int) (Math.random() * ((b - a) + 1));
        //System.out.println("Случайное число x: " + x);
        return x;
    }

//////////////////////////


    public StringBuilder randomStringEN(int s) {            // Рандомное заполнения имен нужно подать количество символов
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder randString = new StringBuilder();
        int count = s;
        for (int i = 0; i < count; i++)
            randString.append(symbols.charAt((int) (Math.random() * symbols.length())));
//        System.out.print(randString);
//        temp = String.valueOf(randString);
        return randString;
    }

///////////////////////////////////

//  $(".loading_progress").should(disappear); // Само подождёт, пока элемент исчезнет
 // $("#username").shouldHave(text("Hello, Johny!")); // Само подождёт, пока у элемента появится нужный текст

$("#username").shouldBe(visible);   // ждёт, пока элемент появится
  $("#username").shouldHave(text("Hello, Johny!")); // ждёт, пока текст элемента изменится на "Hello, Johny!"
  $("#login-button").shouldHave(cssClass("green-button")); // ждёт, пока кнопка станет зелёной
  $("#login-button").shouldBe(disabled); // ждёт, пока кнопка станет неактивной
  $(".error").shouldNotBe(visible);  // ждёт, пока элемент исчезнет
  $(".error").should(disappear);     // амо подождёт, пока элемент исчезнет

clearBrowserCache();       // очистить кеш браузера
   */


