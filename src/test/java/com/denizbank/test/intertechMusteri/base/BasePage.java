package com.denizbank.test.intertechMusteri.base;

import io.appium.java_client.*;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;

/**
 * Created by esrakurtlar on 2.11.2017.
 */
public class BasePage {

    public static final int DEFAULT_WAIT = 30;
    private final int ELEMENT_MAX_WAIT = 30;
    private final int ELEMENT_SEARCH_SLEEP = 2000;
    protected AppiumDriver<MobileElement> driver;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
    }

    public BasePage() {

    }

    /**
     * Click komutunu lokasyona göre işletir, click metodunun çalışmadığı
     * durumlarda kullanılabilir.
     *
     * @param seconds   : Element'in aranma süresini belirler.
     * @param elementBy : Ulaşılmak istenen elementin attribute değeri.
     */
    public void getLocationClick(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        driver.tap(1, element, 1);
    }

    /**
     * Ulaşılmak istenilen WebElement'i döndürür.
     *
     * @param seconds:   Element'in aranma süresini belirler.
     * @param elementBy: Ulaşılmak istenen elementin attribute değeri.
     * @return
     */
    public WebElement waitForElement(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    /**
     * Ulaşılmak istenilen MobileElement'i döndürür.
     *
     * @param seconds:   Element'in aranma süresini belirler.
     * @param elementBy: Ulaşılmak istenen elementin attribute değeri.
     * @return
     */
    public MobileElement waitForMobileElement(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 1000);
        return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
    }

    /**
     * Ulaşılmak istenilen WebElement'leri liste şeklinde döndürür.
     *
     * @param seconds:   Element'in aranma süresini belirler.
     * @param elementBy: Ulaşılmak istenen elementin attribute değeri.
     * @return
     */
    public List<MobileElement> waitForElements(int seconds, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, seconds, 30);
        List element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
        return element;
    }

    /**
     * Ulaşılmak istenilen WebElement'i döndürür.
     *
     * @param seconds    : Element'in aranma süresini belirler.
     * @param resourceId : Ulaşılmak istenen elementin id değeri string cinsinden.
     * @return
     */
    public WebElement waitForElementById(int seconds, String resourceId) {
        return waitForElement(seconds, By.id(resourceId));
    }

    public void waitForElement(int seconds, String resourceId) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id(resourceId)));
    }

    /**
     * Element'i bekler ve click işlemini gerçekleştirir
     *
     * @param byElement
     * @param Seconds
     */
    public void clickAfterWaitForElement(By byElement, int Seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(byElement)).click();

    }

    /**
     * Element'i bekler ve click işlemini gerçekleştirir.
     */
    public void clickAfterWaitForElement(By byElement) {
        WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT);
        wait.until(ExpectedConditions.presenceOfElementLocated(byElement)).click();

    }

    public void clickAndWaitListElementAtIndex(By byList, By byElement, int index, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(byList)).findElements(byElement).get(index).click();

    }

    /**
     * Textfield alanlarına text göndermeyi sağlar. İşlem sonrası klavyeyi
     * gizler.
     *
     * @param byElement
     * @param Seconds
     * @param text
     */
    public void sendKeysAndWaitForElement(By byElement, int Seconds, String text) {
        WebElement element = waitForElement(Seconds, byElement);
        element.click();
        if (driver instanceof IOSDriver) {
            element.clear();
            element.sendKeys(text);
            tryHideKeyboardIOS();
        } else {
            element.clear();
            element.sendKeys(text);
            tryHideKeyboard();
        }
    }

    /**
     * Textfield alanlarına text göndermeyi sağlar. İşlem sonrası klavyeyi
     * gizler.
     */
    public void sendKeysAndWaitForElement(By byElement, String text) {
        WebElement element = waitForElement(DEFAULT_WAIT, byElement);
        element.click();
        if (driver instanceof IOSDriver) {
            element.clear();
            element.sendKeys(text);
            tryHideKeyboardIOS();
        } else {
            element.clear();
            element.sendKeys(text);
            tryHideKeyboard();
        }
    }

    /**
     * Textfield alanlarına text göndermeyi sağlar, list halindeli elementleri
     * index ile bulur. İşlem sonrası klavyeyi gizler.
     * : liste halindeki elementlerin index değeri.
     */
    public void sendKeysAndWaitForElements(By byElement, int Seconds, String text, int index) {
        WebElement element = waitForElements(DEFAULT_WAIT, byElement).get(index);
        element.click();
        if (driver instanceof IOSDriver)
            element.clear();
        element.sendKeys(text);
    }

    /**
     * Textfield alanlarına text göndermeyi sağlar. Klavyenin gizlenmemesi
     * istenildiğinde kullanılır.
     */
    public void sendKeysAndWaitForElementNotHideKeyboard(By byElement, int Seconds, String text) {
        WebElement element = waitForElement(DEFAULT_WAIT, byElement);
        element.click();
        if (driver instanceof IOSDriver)
            element.clear();
        element.sendKeys(text);
    }


    public void sendKeysAndWaitForElementNotClear(By byElement, int Seconds, String text) {
        WebElement element = waitForElement(Seconds, byElement);
        element.click();
        if (driver instanceof IOSDriver) {
            element.sendKeys(text);
            tryHideKeyboardIOS();
        } else {
            element.sendKeys(text);
            tryHideKeyboard();
        }
    }

    /**
     * Textfield alanlarındaki değeri siler.
     */
    public void clearAndWaitForElement(By byElement, int Seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(byElement)).clear();
    }

    /**
     * Belirtilen saniye boyunca bir sonraki işleme kadar bekler.
     *
     * @param seconds
     */
    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Aldığı parametre sayısınca sola swipe eder.
     *
     * @param repeat
     */
    public void swipeLeftMultiple(int repeat) {
        for (int i = 0; i < repeat; i++) {
            swipeLeftAccordingToPhoneSize();
            waitSeconds(1);
        }
    }

    /**
     * Sola swipe eder. Telefon boyutundan bağımsız çalışır.
     */
    public void swipeLeftAccordingToPhoneSize() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = (width * 60) / 100;
        int swipeEndWidth = (width * 5) / 100;
        int swipeStartHeight = height / 3, swipeEndHeight = height / 3;

        // System.out.println("height= " + height + " width= " + width);
        // System.out.println("start= " + swipeStartWidth + " end= " +
        // swipeEndWidth);

        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

    }

    /**
     * Sağa swipe eder. Telefon boyutundan bağımsız çalışır.
     */
    public void swipeRightAccordingToPhoneSize() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = (width * 50) / 100;
        int swipeEndWidth = (width * 90) / 100;
        int swipeStartHeight = height / 3, swipeEndHeight = height / 3;

        // System.out.println("height= " + height + " width= " + width);
        // System.out.println("start= " + swipeStartWidth + " end= " +
        // swipeEndWidth);

        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

    }

    /**
     * Aşağıya swipe eder. Telefon boyutundan bağımsız çalışır.
     */
    public void swipeDownAccordingToPhoneSize() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = width / 2, swipeEndWidth = width / 2;

        int swipeStartHeight = (height * 80) / 100;
        int swipeEndHeight = (height * 30) / 100;

        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

    }

    /**
     * Daha az swipe etmeyi sağlar
     */
    public void miniSwipeDownAccordingToPhoneSize() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = width / 2, swipeEndWidth = width / 2;

        int swipeStartHeight = (height * 80) / 100;
        int swipeEndHeight = (height * 50) / 100;

        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

    }

    //ekranın sağına dokunup slide yapmayı dener.
    public void swipeDownAccordingToPhoneSizeVersion2() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = width - 10, swipeEndWidth = width - 10;
        int swipeStartHeight = (height * 80) / 100, swipeEndHeight = (height * 30) / 100;
        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);
    }

    public void swipeDown(By element) {
        MobileElement el = (MobileElement) waitForElement(DEFAULT_WAIT, element);
        int startX = el.getLocation().getX();
        int endX = el.getSize().getWidth() + startX;
        int startY = el.getLocation().getY();
        int height = el.getSize().getHeight();

        By id = MobileBy.ByAccessibilityId.id("PrepaidTLViewController_PrepaidTLButton");

        TouchAction touchAction = new TouchAction(driver);
//		touchAction.press((startX + endX) / 2, startY + 50).waitAction(100)
//				.moveTo((startX + endX) / 2, startY).release().perform();
        touchAction.press((startX + endX) / 2, 300).waitAction(100).moveTo((startX + endX) / 2, 200); //bunun çalışması gerekiyor ama bu da çalışmıyor.

    }

    /**
     * Yukarıya swipe eder. Telefon boyutundan bağımsız çalışır.
     */
    public void swipeUpAccordingToPhoneSize() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = width / 2, swipeEndWidth = width / 2;

        int swipeStartHeight = (height * 30) / 100;
        int swipeEndHeight = (height * 60) / 100;

        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

    }

    /**
     * OTP buton sağa swipe eder
     */
    public void swipeRightOTP() {
        if (driver instanceof IOSDriver) {
            clickAfterWaitForElement(MobileBy.ByAccessibilityId.id("DecisionSlider_OkLabel"));
        } else {
            // Dimension d = driver.manage().window().getSize();
            // int height = d.height;
            // int width = d.width;
            //
            // int swipeStartWidth = (width * 50) / 100;
            // int swipeEndWidth = (width * 90) / 100;
            // int swipeStartHeight = ( height * 2) / 3-20, swipeEndHeight = (
            // height * 2) / 3-20;

            clickAfterWaitForElement(By.id("com.intertech.mobilemoneytransfer.activity:id/swipeAlertRightButton"));
            // driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth,
            // swipeEndHeight, 1000);
            // swipeAlertRightButton swipe çalışmazsa tamam butonunun id'si

        }
    }

    /**
     * Dotlock hareketi, uygulamaya özel el kaldırmadan at hareketi yapar
     */

    public void swipeDotLock() {

        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int swipeStartWidth = (width * 50) / 100;
        int swipeEndWidth = (width * 90) / 100;
        int swipeStartHeight = (height * 2) / 3, swipeEndHeight = (height * 2) / 3;

        driver.swipe(swipeStartWidth, swipeStartHeight, swipeEndWidth, swipeEndHeight, 1000);

        TouchAction touchAction = new TouchAction(driver);

        // appium converts press-wait-moveto-release to a swipe action
        touchAction.press(swipeStartWidth, swipeStartHeight).waitAction(1000).moveTo(swipeEndWidth, swipeEndHeight)
                .press(swipeEndWidth, swipeStartHeight).moveTo(swipeEndWidth, swipeEndHeight);
        touchAction.perform();

    }

    /**
     * Combobox elementinden indexine göre seçim yapmayı sağlar.
     */
    public void selectElementFromCombobox(int seconds, int index, By byElement) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        Select oSelection = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(byElement)));
        oSelection.selectByIndex(index);
    }

    /**
     * Element bazlı swipe işlemi yapar.
     *
     * @param byElement
     */
    public void swipeLeftById(By byElement) {
        MobileElement element = (MobileElement) driver.findElement(byElement);
        element.swipe(SwipeElementDirection.LEFT, 400);
    }

    /**
     * Default IOS HideKeyboard metodu her durumda doğru çalışmadığı için
     * geliştirilmiş metodtur. Default metodta ekranın ortasına tıklayıp
     * klavyeyi kapatmakta fakat tıkladığı alanda buton veya textfield gibi bir
     * element var ise onlara aksiyon sağladığı için işlem başarısız olmakta bu
     * sebeple element bulunmayacağı tahmin edilen noktaya göre klavye gizleme
     * işlemi sağlanmıştır.
     */
    public void tryHideKeyboardIOS() {
        try {
            Dimension d = driver.manage().window().getSize();
            int height = d.height;
            int width = d.width;

            driver.tap(2, width - 1, height / 2, 1);
        } catch (Exception e) {
        }

    }

    /**
     * Appium default klavye gizleme işlemidir. Android platformunda stabil
     * çalışırken IOS platformunda stabil çalışmamaktadır.
     */
    public void tryHideKeyboard() {
        try {
            driver.hideKeyboard();
        } catch (Exception e) {
        }

    }

    /**
     * Testin çalıştığı telefonun boyutlarını yazdıran metod.
     */
    public void getPhoneSize() {
        Dimension d = driver.manage().window().getSize();
        int height = d.height;
        int width = d.width;
        System.out.println("height : " + height + "\nwidth : " + width);
    }

    /**
     * Element'in varlığını kontrol eder, parametre ile belirtilen süre boyunca
     * elementi bulursa true bulamaz ise false döner bu sayede assertion
     * kontrolü sağlanır.
     *
     * @param seconds
     * @param by
     * @return
     */
    public boolean isExist(int seconds, By by) {
        try {
            waitForElement(seconds, by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Element'in attribute değerini konsol a basar.
     *
     * @param element
     */
    public void foundedElement(WebElement element) {
        System.out.println("Founded element ->" + element.toString().split("->")[1]);
    }

    public boolean isInvisible(int seconds, By by) {
        boolean value = false;
        for (int i = 0; i <= seconds; i++) {
            if (driver.findElements(by).size() > 0) {
                value = false;
                waitSeconds(1);
                continue;
            } else {
                value = true;
                break;
            }
        }
        return value;
    }

    public List<MobileElement> getElements(By byElement) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_MAX_WAIT, ELEMENT_SEARCH_SLEEP);
        List element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(byElement));
        return element;
    }

    public MobileElement getElement(By byElement) {
        WebDriverWait wait = new WebDriverWait(driver, ELEMENT_MAX_WAIT, ELEMENT_SEARCH_SLEEP);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(byElement));
        return (MobileElement) element;
    }

    public void WifiController() {

        int height = driver.findElementByClassName("UIAWindow").getSize().getHeight();
        int width = driver.findElementByClassName("UIAWindow").getSize().getWidth();
        System.out.println(height + "--" + width);
        if (BaseTest.currentPhone.contains("IPHONE_4")) {
            driver.swipe(width - 100, height, width - 100, height - 200, 500);
            waitSeconds(2);
            System.out.println("Status : " + driver.findElementById("Wi‑Fi").getAttribute("value"));
            if (!(driver.findElementById("Wi‑Fi").getAttribute("value").equals("1"))) {
                driver.findElementById("Wi-Fi").click();
                waitSeconds(3);
            }
            driver.executeScript("mobile: tap", new HashMap<String, Double>() {
                {
                    put("tapCount", 1.0);
                    put("touchCount", 1.0);
                    put("duration", 0.5);
                    put("x", 60.0);
                    put("y", 44.0);
                }
            });

        }
        if (BaseTest.currentPhone.contains("IPHONE_5")) {
            driver.swipe(width - 100, height, width - 100, height - 200, 500);
            waitSeconds(2);
            System.out.println("Status : " + driver.findElementById("Wi‑Fi").getAttribute("value"));
            if (!(driver.findElementById("Wi‑Fi").getAttribute("value").equals("1"))) {
                driver.findElementById("Wi-Fi").click();
                waitSeconds(3);
            }
            driver.executeScript("mobile: tap", new HashMap<String, Double>() {
                {
                    put("tapCount", 1.0);
                    put("touchCount", 1.0);
                    put("duration", 0.5);
                    put("x", 60.0);
                    put("y", 44.0);
                }
            });

        }
    }

    public void dotLock(By element) {

        MobileElement source = driver.findElement(element);
        // MobileElement destination =

        // source = driver.find_element_by_accessibility_id('Source')
        // destination = driver.find_element_by_accessibility_id('Destination')
        // action = TouchAction(driver)
        // action.long_press(source).move_to(destination).wait(500).release()
        // action.perform()

    }

    /**
     * Element var mı bakar varsa clickler. Yoksa herhangi birşey yapmaz devam
     * eder.
     *
     * @param element
     * @param seconds
     */

    public void clickIfExist(By element, int seconds) {
        for (int i = 0; i < seconds; i++) {
            waitSeconds(1);
            if (driver.findElements(element).size() > 0) {
                clickAfterWaitForElement(element, seconds);
                break;
            }
        }
    }

    /**
     * Elementi görene kadar swipe eder
     */
    public void swipeDownUntilSeeTheElement(By element, int limit) {
        for (int i = 0; i < limit; i++) {
            if (driver.findElements(element).size() == 0) {
                swipeDownAccordingToPhoneSize();
            } else {
                break;
            }
        }
    }

    /**
     * Ekran üzerinde gözükmeyen gizli elementlerin click özelliğinin aktif
     * edilmesi amaçlandı
     *
     * @param element : aktif etmeye çalıştığın element
     */
    public void clickInvinsible(By element) {
        WebElement el = driver.findElement(element);
        Point p = ((Locatable) el).getCoordinates().onPage();
        driver.tap(1, p.getX(), p.getY(), 1);
    }

    /**
     * Listede aradığımız textteki elemana click yapar
     *
     * @param element : listede idsi aynı mobileElementler
     * @param name    : listede click yapılmak istenen elementin texti
     */

    public void searchAndClickOnList(By element, String name) {
        List<MobileElement> elementList = waitForElements(5, element);
        for (int i = 0; i < elementList.size(); i++) {
            if (elementList.get(i).getText() == name) {
                System.out.println(name);
                elementList.get(i).click();
                break;
            }
        }
    }

    public void sendKeycode(String... num) {

        for (int count = 0; count < 24; count++) {
            switch (num[count]) {

                case "0":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_0);

                    break;

                case "1":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_1);

                    break;

                case "2":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_2);

                    break;

                case "3":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_3);

                    break;

                case "4":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_4);

                    break;

                case "5":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_5);

                    break;

                case "6":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_6);

                    break;

                case "7":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_7);

                    break;

                case "8":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_8);

                    break;

                case "9":
                    ((AndroidDriver<?>) driver).pressKeyCode(AndroidKeyCode.KEYCODE_9);

                    break;

                default:

                    break;
            }
            waitSeconds(1);
        }

    }

}


