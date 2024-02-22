package stepdefinitions;

import commons.Hooks;
import commons.PageGeneralManager;
import commons.TestContext;
import cucumber.api.java.vi.Thì;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;

public class HomePageSteps {
    WebDriver driver;
    LoginPageObject loginPageObject;
    HomePageObject homePageObject;
    RegisterPageObject registerPageObject;
    TestContext testContext;

    public HomePageSteps(TestContext testContext){
        this.testContext = testContext;
        driver = Hooks.openAndQuitBrowserNotUrl();
    }

    @Thì("^truy cập được vào trang homepage$")
    public void truyCậpĐượcVàoTrangHomepage() {
        homePageObject = PageGeneralManager.openHomePage(driver);
        homePageObject.isDisplayWelcomeManager();
    }
}
