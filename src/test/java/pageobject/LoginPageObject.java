package pageobject;

import commons.BasePage;
import commons.PageGeneralManager;
import interfaceui.LoginPageUI;
import org.openqa.selenium.WebDriver;

public class LoginPageObject extends BasePage {
    RegisterPageObject registerPageObject;
    public LoginPageObject(WebDriver driver) {
        super(driver);
    }

    public void goToUrl(String url){
        goToURl(url);
    }

    public RegisterPageObject clickToRegister(){
        clickToElements(LoginPageUI.CLICK_HERE_TO_REGISTER);
        if(getNameUrl().contains("#google_vignette")){
            goToURl("https://demo.guru99.com/");
        }
        registerPageObject = PageGeneralManager.openRegisterPage(driver);
        return registerPageObject;
    }

    public HomePageObject loginWithUserNameAndPassword(String userName,String password){
        sendKey(LoginPageUI.INPUT_USERID,userName);
        sendKey(LoginPageUI.INPUT_PASSWORD,password);
        clickToElements(LoginPageUI.BUTTON_LOGIN);
        return PageGeneralManager.openHomePage(driver);
    }
}
