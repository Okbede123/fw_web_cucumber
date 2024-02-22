package pageobject;

import commons.BasePage;
import interfaceui.HomePageUI;
import org.openqa.selenium.WebDriver;

public class HomePageObject extends BasePage {

    public HomePageObject(WebDriver driver) {
        super(driver);
    }

    public void isDisplayWelcomeManager(){
        isElementDisplayed(HomePageUI.WELCOME_MANAGER);
    }
}
