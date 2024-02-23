package stepdefinitions;

import commons.Hooks;
import commons.PageGeneralManager;
import commons.TestContext;
import cucumber.api.java.vi.Khi;
import cucumber.api.java.vi.Và;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;

public class RegisterPageSteps {
    WebDriver driver;
    RegisterPageObject registerPageObject;
    LoginPageObject loginPageObject;
    private TestContext testContext;


    public  RegisterPageSteps(TestContext testContext){
        this.testContext = testContext;
        driver = Setup.driver;
    }

    @Khi("^điền email vào ô textbox$")
    public void điềnEmailVàoÔTextbox() {
        registerPageObject = PageGeneralManager.openRegisterPage(driver);
        registerPageObject.inputEmail();
    }

    @Và("^ấn submit$")
    public void ấnSubmit() {
        registerPageObject.submit();
    }

    @Khi("^lấy tài khoản và mật khẩu$")
    public void lấyTàiKhoảnVàMậtKhẩu() {
       String userName = registerPageObject.getUser();
       String passWord = registerPageObject.getPass();
       testContext.setContext("user",userName);
       testContext.setContext("pass",passWord);
    }

    @Và("^chở về trang login$")
    public void chởVềTrangLogin() {
      loginPageObject = registerPageObject.goBackLoginPage();
    }
}
