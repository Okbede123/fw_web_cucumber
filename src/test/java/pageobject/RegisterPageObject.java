package pageobject;

import commons.BasePage;
import commons.DataHelper;
import commons.PageGeneralManager;
import interfaceui.RegisterPageUI;
import org.openqa.selenium.WebDriver;

public class RegisterPageObject extends BasePage {

    public RegisterPageObject(WebDriver driver) {
        super(driver);
    }

    public void inputEmail(){
        sendKey(RegisterPageUI.EMAIL, DataHelper.getData().getEmail());
    }

    public void submit(){
        clickToElements(RegisterPageUI.BUTTON_SUBMIT);
    }

    public String getUser(){
        return getText(RegisterPageUI.GET_USERID_LOGIN);
    }

    public String getPass(){
        return getText(RegisterPageUI.GET_PASSWORD_LOGIN);
    }
    public LoginPageObject goBackLoginPage(){
        goToURl("https://demo.guru99.com/v4/");
        return PageGeneralManager.openLoginPage(driver);
    }
}
