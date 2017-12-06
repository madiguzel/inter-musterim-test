package com.denizbank.test.intertechMusteri.base;

import com.denizbank.test.intertechMusteri.page.MainPage;
import com.denizbank.test.intertechMusteri.page.android.AndroidMainPage;
import com.denizbank.test.intertechMusteri.page.ios.IOSMainPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;

/**
 * Created by esrakurtlar on 2.11.2017.
 */
public class PageFactory {
    private static PageFactory instance = null;
    ThreadLocal<AppiumDriver<MobileElement>> driver = new ThreadLocal<AppiumDriver<MobileElement>>();

    private PageFactory() {

    }

    public <T> T createPage (Class<T> pageType) {
        AppiumDriver<MobileElement> appiumDriver = driver.get();
        if (appiumDriver instanceof AndroidDriver) {
            AndroidDriver<MobileElement> androidDriver = (AndroidDriver<MobileElement>)appiumDriver;
            if (pageType.isAssignableFrom(MainPage.class)){
                return (T) new AndroidMainPage(androidDriver);
            }
            Assert.assertTrue("Esra sayfayı page factorye tanımla",false);
        }
        else if (appiumDriver instanceof IOSDriver) {
            IOSDriver<MobileElement> iosDriver = (IOSDriver<MobileElement>)appiumDriver;
            if (pageType.isAssignableFrom(MainPage.class)){
                return (T) new IOSMainPage(iosDriver);
            }
            Assert.assertTrue("Esra sayfayı page factorye tanımla",false);
        }return null;
    }


    public void setDriver(AppiumDriver<MobileElement> driver) {
        this.driver.set(driver);
    }

    public static PageFactory getInstance() {
        if (instance == null) {
            instance = new PageFactory();
        }
        return instance;
    }
}
