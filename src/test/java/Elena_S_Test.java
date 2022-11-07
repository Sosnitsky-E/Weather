import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class Elena_S_Test {
//    TC_1_1  - Тест кейс:
    //1. Открыть страницу https://openweathermap.org/
    //2. Набрать в строке поиска город Paris
    //3. Нажать пункт меню Search
    //4. Из выпадающего списка выбрать Paris, FR
    //5. Подтвердить, что заголовок изменился на "Paris, FR"
    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id=\"weather-widget\"]//input[@placeholder='Search city']")
        );
        Thread.sleep(4000);
        searchCityField.click();
        searchCityField.sendKeys(cityName);

        WebElement searchButton = driver.findElement(
                By.xpath("//div[@id=\"weather-widget\"]//button[@type='submit']")
        );
        searchButton.click();
        Thread.sleep(1000);

        WebElement choiceDropDownMenuParisFrance = driver.findElement(
                By.xpath("//ul[@class='search-dropdown-menu']/li/span[text()='Paris, FR ']")
        );
        choiceDropDownMenuParisFrance.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id='weather-widget']//h2")
        );
        Thread.sleep(5000);

        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult,expectedResult);

//        Thread.sleep(5000);

        driver.quit();
//        driver.close();
    }

//    TC_11_01
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню Guide
//3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и что title этой страницы OpenWeatherMap API guide - OpenWeatherMap

    @Test
    public void testOpenWeatherPageGuidMaximixe() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedURL = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        driver.get(url);
        driver.manage().window().maximize(); //Maximize current window
        WebElement searchButtonGUid = driver.findElement(
                By.xpath("//div[@id='desktop-menu']/ul/li/a[@href='/guide']")
        );

        Thread.sleep(5000);
        searchButtonGUid.click();
        String actualResultURL = driver.getCurrentUrl();
        String actualResult = driver.getTitle();
        Assert.assertEquals(actualResultURL,expectedURL);
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

    @Test
    public void testOpenWeatherPageGuidMinimize() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        String expectedURL = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";
        driver.get(url);
//        driver.manage().window().maximize(); //Maximize current window
        WebElement searchHamburger = driver.findElement(
                By.xpath("//li[@id='hamburger']")
        );
        Thread.sleep(5000);
        searchHamburger.click();

        WebElement searchButtonGUid = driver.findElement(
                By.xpath("//ul[@id='mobile-menu']//li/a[@href='/guide']")
        );
        searchButtonGUid.click();
        String actualResultURL = driver.getCurrentUrl();
        String actualResult = driver.getTitle();
        Assert.assertEquals(actualResultURL,expectedURL);
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();

    }

//    TC_11_02
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//3.  Подтвердить, что температура для города показана в Фарингейтах
    @Test
    public void testTemperatureFormatSelectionInFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        driver.get(url);
        WebElement switchToFahrenheit = driver.findElement(
                By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
        );
        Thread.sleep(5000);
        switchToFahrenheit.click();
        char expectedResult = 'F';
        WebElement findFahrenheit = driver.findElement(
                By.xpath("//span[@class='heading'][contains(text(),'F')]")
        );
        String result = findFahrenheit.getText();
        char actualResult = result.charAt(result.length()-1);
        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }

//   TC_11_03
//1.  Открыть базовую ссылку
//2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for
// the site to work. We also use non-essential cookies to help us improve our services.
// Any data collected is anonymised. You can allow all cookies or manage them individually.”
//3. Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
    @Test
    public void testApprovinfButtonsAllow_allAndManage_cookies(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        driver.get(url);
        String expectedResultPanelText = "We use cookies which are essential for the site to work. We also use non-essential" +
                " cookies to help us improve our services. Any data collected is anonymised. You can allow all cookies" +
                " or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";
        WebElement panelText = driver.findElement(
                By.xpath("//p[@class='stick-footer-panel__description']")
        );
        String actualResultPanelText = panelText.getText();
        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//button[@class='stick-footer-panel__link']")
        );
        String actualResultAllowAll = buttonAllowAll.getText();
        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//a[@class='stick-footer-panel__link'][text()=' Manage cookies ']"));
        String actualResultManageCookies = buttonManageCookies.getText();
        Assert.assertEquals(actualResultPanelText,expectedResultPanelText);
        Assert.assertEquals(actualResultAllowAll,expectedResultAllowAll);
        Assert.assertEquals(actualResultManageCookies,expectedResultManageCookies);
        driver.quit();

    }

//    TC_11_04
//1.  Открыть базовую ссылку
//2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и “Ask a question”

    @Test
    public void textSupportSubmenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        driver.manage().window().maximize(); //Maximize current window
        driver.get(url);
         String expectedResultLink1 = "FAQ";
        String expectedResultLink2 = "How to start";
        String expectedResultLink3 = "Ask a question";
        WebElement upperPanelMenuSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']")
        );
      Thread.sleep(5000);
        upperPanelMenuSupport.click();
        WebElement link1 = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href=\"/faq\"][text()='FAQ']")
        );
        WebElement link2 = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href=\"/appid\"][text()='How to start']")
        );
        WebElement link3 = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']" +
                        "[text()='Ask a question']")
        );
        String actualResultLink1 = link1.getText();
        String actualResultLink2 = link2.getText();
        String actualResultLink3 = link3.getText();
        Assert.assertEquals(actualResultLink1,expectedResultLink1);
        Assert.assertEquals(actualResultLink2,expectedResultLink2);
        Assert.assertEquals(actualResultLink3,expectedResultLink3);
        driver.quit();
    }
//    TC_11_05
//1. Открыть базовую ссылку
//2. Нажать пункт меню Support → Ask a question
//3. Заполнить поля Email, Subject, Message
//4. Не подтвердив CAPTCHA, нажать кнопку Submit
//5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
@Test
public void textDisplayCaptureError() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    String url = "https://openweathermap.org/";
    driver.manage().window().maximize(); //Maximize current window
    driver.get(url);
    String expectedResultErrorText = "reCAPTCHA verification failed, please try again.";

    WebElement upperPanelMenuSupport = driver.findElement(
            By.xpath("//div[@id='support-dropdown']")
    );
    Thread.sleep(5000);
    upperPanelMenuSupport.click();

    WebElement askQuestion = driver.findElement(
            By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']" +
                    "[text()='Ask a question']")
    );
    askQuestion.click();
    Thread.sleep(1000);


// Creating list arrayList of opened tabs.
    ArrayList tabs = new ArrayList(driver.getWindowHandles());
//    System.out.println(tabs.size());
    driver.switchTo().window((String) tabs.get(1));

    String email = "email@gmai.com";
    String message = "Message for support test";



    WebElement fieldEmail = driver.findElement(
            By.xpath("//input[@id='question_form_email']")
    );

    Thread.sleep(1000);
    fieldEmail.click();
    fieldEmail.sendKeys(email);
    Thread.sleep(1000);
//    WebElement fieldSubject = driver.findElement(
//            By.xpath("//select[@id='question_form_subject']")
//    );
//    fieldSubject.click();

    WebElement valueOfFieldSubject = driver.findElement(
            By.xpath("//select[@id='question_form_subject']/option[@value='Other']")
    );
    valueOfFieldSubject.click();
    Thread.sleep(1000);
    WebElement fieldMessage = driver.findElement(
            By.xpath("//textarea[@id='question_form_message']")
    );
    fieldMessage.click();
    fieldMessage.sendKeys(message);
//    Thread.sleep(1000);

    WebElement buttonSubmit = driver.findElement(
            By.xpath("//input[@value='Submit']")
    );
    buttonSubmit.click();
//    Thread.sleep(1000);
    WebElement errorMessageResult = driver.findElement(
            By.xpath("//div[@class='help-block']")
    );
    String actualResultErrorText = errorMessageResult.getText();
    Assert.assertEquals(actualResultErrorText,expectedResultErrorText);


    driver.quit();
}
// TC_11_06
//1.  Открыть базовую ссылку
//2.  Нажать пункт меню Support → Ask a question
//3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
//4. Оставить пустым поле Email
//5. Заполнить поля  Subject, Message
//6. Подтвердить CAPTCHA
//7. Нажать кнопку Submit
//8. Подтвердить, что в поле Email пользователю будет показана ошибка “can't be blank”
@Test
public void textDisplayEmailError() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    String url = "https://openweathermap.org/";
    driver.manage().window().maximize(); //Maximize current window
    driver.get(url);

    WebElement upperPanelMenuSupport = driver.findElement(
            By.xpath("//div[@id='support-dropdown']")
    );
    Thread.sleep(5000);
    upperPanelMenuSupport.click();

    WebElement askQuestion = driver.findElement(
            By.xpath("//ul[@id='support-dropdown-menu']//a[@href='https://home.openweathermap.org/questions']" +
                    "[text()='Ask a question']")
    );
    askQuestion.click();
    Thread.sleep(1000);


// Creating list arrayList of opened tabs.
    ArrayList tabs = new ArrayList(driver.getWindowHandles());
//    System.out.println(tabs.size());
    driver.switchTo().window((String) tabs.get(1));

    String expectedResultEmailErrorText = "can't be blank";
    String message = "Message for support test";


    WebElement valueOfFieldSubject = driver.findElement(
            By.xpath("//select[@id='question_form_subject']/option[@value='Other']")
    );
    valueOfFieldSubject.click();
    Thread.sleep(1000);
    WebElement fieldMessage = driver.findElement(
            By.xpath("//textarea[@id='question_form_message']")
    );
    fieldMessage.click();
    fieldMessage.sendKeys(message);
    Thread.sleep(5000);
    WebElement captureCheckbox = driver.findElement(
            By.xpath("//iframe[@title='reCAPTCHA']")
    );
    Thread.sleep(5000);
    captureCheckbox.click();
    Thread.sleep(5000);


    WebElement buttonSubmit = driver.findElement(
            By.xpath("//input[@value='Submit']")
    );
    buttonSubmit.click();

    WebElement errorEmailResult = driver.findElement(
            By.xpath("//span[@class='help-block']")
    );
    String actualResultEmailErrorText = errorEmailResult.getText();
    Assert.assertEquals(actualResultEmailErrorText,expectedResultEmailErrorText);


    driver.quit();
}

//TC_11_07
//1.  Открыть базовую ссылку
//2.  Нажать на единицы измерения Imperial: °F, mph
//
//3.  Нажать на единицы измерения Metric: °C, m/s
//4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
@Test
public void testTemperatureFormatSelectionInCelsius() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    String url = "https://openweathermap.org/";
    driver.get(url);
    WebElement switchToFahrenheit = driver.findElement(
            By.xpath("//div[@class='option'][text()='Imperial: °F, mph']")
    );
    Thread.sleep(5000);
    switchToFahrenheit.click();

    WebElement switchToCelsius = driver.findElement(
            By.xpath("//div[@class='option'][text()='Metric: °C, m/s']")
    );
    switchToCelsius.click();
    char expectedResult = 'C';
    WebElement findCelsius = driver.findElement(
            By.xpath("//span[@class='heading'][contains(text(),'C')]")
    );
    String result = findCelsius.getText();
    char actualResult = result.charAt(result.length()-1);
    Assert.assertEquals(actualResult,expectedResult);

    driver.quit();
}
//TC_11_08
//1.  Открыть базовую ссылку
//2.  Нажать на лого компании
//
//3.  Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
    @Test
    public void testPageRefresh() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://openweathermap.org/";
        driver.manage().window().maximize();
        driver.get(url);
        Thread.sleep(5000);
        WebElement logoImage = driver.findElement(
                By.className("logo")
        );
        logoImage.click();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(actualResult,url);
        driver.quit();
    }

//    TC_11_09
//1.  Открыть базовую ссылку
//2.  В строке поиска в навигационной панели набрать “Rome”
//
//3.  Нажать клавишу Enter
//4.  Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
//5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
@Test
public void testSearchCityPage() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    String url = "https://openweathermap.org/";
    driver.manage().window().maximize();
    driver.get(url);
    Thread.sleep(5000);
    String cityName = "Rome";
    String expectedResultCityURL = "https://openweathermap.org/find?q=Rome";
    String expectedResultCityName = "Rome";

    WebElement fieldSearch = driver.findElement(
            By.name("q")
    );
    fieldSearch.click();
    fieldSearch.sendKeys(cityName);
    fieldSearch.submit();
    String actualResultCityURL = driver.getCurrentUrl();
    WebElement cityNameIntheField = driver.findElement(
            By.id("search_str")
    );
    String actualResultCityName = cityNameIntheField.getAttribute("value");
    Assert.assertEquals(actualResultCityURL,expectedResultCityURL);
    Assert.assertEquals(actualResultCityName,expectedResultCityName);

    driver.quit();
}
//TC_11_10
//1.  Открыть базовую ссылку
//2.  Нажать на пункт меню API
//3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
@Test
public void testCountOfAPIButtons() throws InterruptedException {
    System.setProperty("webdriver.chrome.driver", "C:\\Users\\Elena\\Documents\\Drivers\\chromedriver.exe");
    WebDriver driver = new ChromeDriver();
    String url = "https://openweathermap.org/";
    int expectedResultCountOrangeButtons = 30;
    driver.manage().window().maximize();
    driver.get(url);
    Thread.sleep(5000);
    WebElement apiLink = driver.findElement(
            By.xpath("//a[@href='/api']")
    );
    apiLink.click();
   int actualResultCountOrangeButtons = driver.findElements(
           By.xpath("//a[@type='button'][@class='btn_block orange round']")
   ).size()+
           driver.findElements(
            By.xpath("//a[@class='ow-btn round btn-orange']")
                                                            ).size();
   Assert.assertEquals(actualResultCountOrangeButtons,expectedResultCountOrangeButtons);



    driver.quit();
}





}
