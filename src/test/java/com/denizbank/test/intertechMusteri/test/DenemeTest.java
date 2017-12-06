package com.denizbank.test.intertechMusteri.test;

import com.denizbank.test.intertechMusteri.base.BaseTest;
import com.denizbank.test.intertechMusteri.base.PageFactory;
import com.denizbank.test.intertechMusteri.page.MainPage;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by esrakurtlar on 6.12.2017.
 */
public class DenemeTest extends BaseTest {
    /**
     * Uygulama açılmasını kontrol etmek
     */
    @Test
    public void denemeCase1() {
        PageFactory.getInstance().createPage(MainPage.class).isAppStart();
    }

    /**
     * Tanıtım katoloğuna basıldığında liste sayfası açıldı mı?
     */
    @Test
    public void denemeCase2() {
        MainPage mainPage = PageFactory.getInstance().createPage(MainPage.class).isAppStart().clickCatologueButton();
        Assert.assertTrue("Liste açılmadı",mainPage.isListExist());
    }
}
