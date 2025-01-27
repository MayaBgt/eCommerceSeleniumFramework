package org.selenium.pom.tests;

import com.beust.ah.A;
import org.selenium.pom.api.actions.CartApi;
import org.selenium.pom.api.actions.SignUpApi;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.objects.Product;
import org.selenium.pom.objects.User;
import org.selenium.pom.pages.AccountPage;
import org.selenium.pom.pages.CheckoutPage;
import org.selenium.pom.utils.FakerUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckout() throws Exception {
        String username = "demouser" + new FakerUtils().generateRandomNumber();
        User user = new User().
                setUsername(username).
                setPassword("demopwd").
                setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);

        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckoutPage checkoutPage = new CheckoutPage(getDriver()).load();
        injectCookiesToBrowser(cartApi.getCookies());
        checkoutPage.load().
                clickToLogin().
                login(user);
        Assert.assertTrue(checkoutPage.getProductName().contains(product.getName()));
    }

    @Test
    public void loginFailsNoCredentialsEntered() {
        String errorMessage = "Error: Username is required.";
        AccountPage accountPage = new AccountPage(getDriver()).
                load().
                clickLoginBtn();
        Assert.assertEquals(accountPage.getErrorMessageText(), errorMessage);
    }

    @Test
    public void loginFailsNoPasswordEntered() {
        String errorMessage = "Error: The password field is empty.";
        AccountPage accountPage = new AccountPage(getDriver()).
                load().
                enterUsername().
                clickLoginBtn();
        Assert.assertEquals(accountPage.getErrorMessageText(), errorMessage);
    }
}
