package com.denizbank.test.intertechMusteri.page.ios;

import com.denizbank.test.intertechMusteri.base.BasePage;
import com.denizbank.test.intertechMusteri.page.MainPage;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

/**
 * Created by esrakurtlar on 6.12.2017.
 */
public class IOSMainPage extends BasePage implements MainPage {

    public IOSMainPage(IOSDriver<MobileElement> iosDriver) {
        super(iosDriver);
    }

    @Override
    public MainPage isAppStart() {
        By ETKINLIK_AJANDASI = MobileBy.ByAccessibilityId.id("ETKİNLİK AJANDASI");
        By INTERTECH = MobileBy.ByAccessibilityId.id("logo-white");
        waitSeconds(1);
        Assert.assertTrue("Etkinlik ajandası açılmadı", isExist(10,ETKINLIK_AJANDASI));
        Assert.assertTrue("Intertech logosu görüntülenemedi",isExist(10,INTERTECH));
        return this;
    }

    @Override
    public MainPage clickCatologueButton() {
        By TANITIM_KATALOGU = MobileBy.ByAccessibilityId.id("katalog-button");
        clickAfterWaitForElement(TANITIM_KATALOGU,10);
        return this;
    }

    @Override
    public boolean isListExist() {
        By TANITIM_KATOLOGU_HEADER = MobileBy.ByAccessibilityId.id("TANITIM KATALOĞU");
        By ONSOZ = MobileBy.ByAccessibilityId.id("ÖNSÖZ");
        return isExist(10,TANITIM_KATOLOGU_HEADER) && isExist(10, ONSOZ);
    }
}
