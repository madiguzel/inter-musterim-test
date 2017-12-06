package com.denizbank.test.intertechMusteri.base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Map;

/**
 * Created by esrakurtlar on 2.11.2017.
 */
public class BaseTest {

    public static String currentPhone="GENERALMOBILE";
    public static String APPPACKAGE = "com.denizbank.firsatlardenizi";
    public static String APPACTIVITY ="com.denizbank.firsatlardenizi.SelectHostActivity";
    public static String BROWSER_NAME = "";
    protected AppiumDriver<MobileElement> driver;

    boolean localAndroid =false;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("newCommandTimeout", "120");
        if(StringUtils.isEmpty(System.getProperty("key"))){
            if(localAndroid){
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator");
                capabilities.setCapability(MobileCapabilityType.APP, "/Users/esrakurtlar/Desktop/fdDev-v05.12.2017.apk");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
                capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "6.0.1");
                capabilities.setCapability("appPackage", APPPACKAGE);
                capabilities.setCapability("appActivity",APPACTIVITY);
                driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            }else{
                //capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3.1"); //Esra Tel Version
                //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"10.3.3"); //Gamze's Tel Version
                //capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.2.1"); //Kadir'sIphone Tel Version
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "IPHONE_6");
                capabilities.setCapability(MobileCapabilityType.APP, "/Users/esrakurtlar/Desktop/IntertechMusteriGunleri.ipa");
                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.NO_RESET, "true");
                //capabilities.setCapability("udid", "90b92f90dc8dcb0a25e113d7fc7627a3ec9b9774"); 	//Berkay Telefon ID
                //capabilities.setCapability("udid", "32b02bde4493ddf706b2814e1ff5ec7d26b3bd0a"); 	//Kadir's Iphone Telefon ID
                //capabilities.setCapability("udid","32ae7030d3b891b71c3fbf3d4748feebe4cedc49"); //Gamze's UDID
                capabilities.setCapability("udid", "f2b343057046e030f11f31f5782c0d899215b621"); 	//Esra Telefon ID
                driver = new IOSDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
            }
        }
        PageFactory.getInstance().setDriver(driver);
    }

    private void setCurrentPhone() {
        Map<String, Object> obj = (Map<String, Object>) driver.getCapabilities().getCapability("desired");
        for (Map.Entry s : obj.entrySet()) {
            System.out.println("'" + s.getKey() + "' - '" + s.getValue() + "'");
            if (s.getKey().equals("deviceName")) {
                currentPhone = String.valueOf(s.getValue());
                break;
            }
        }
    }
    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

}
