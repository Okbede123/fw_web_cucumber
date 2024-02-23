package commons;

import org.openqa.selenium.WebDriver;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;

public class PageGeneralManager {
    private static HomePageObject homePageObject;
    private static LoginPageObject loginPageObject;
    private static RegisterPageObject registerPageObject;

    public static HomePageObject openHomePage(WebDriver driver){
//        if(homePageObject == null){
            homePageObject = new HomePageObject(driver);
//        }
        return homePageObject;
    }
    public static LoginPageObject openLoginPage(WebDriver driver){
//        if(loginPageObject == null){
            loginPageObject = new LoginPageObject(driver);
//        }
        return loginPageObject;
    }

    public static RegisterPageObject openRegisterPage(WebDriver driver){
//        if(registerPageObject == null){
            registerPageObject = new RegisterPageObject(driver);
//        }
        return registerPageObject;
    }

}
