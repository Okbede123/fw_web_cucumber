package stepdefinitions;

import commons.Hooks;
import commons.PageGeneralManager;
import commons.TestContext;
import cucumber.api.java.vi.Cho;
import cucumber.api.java.vi.Và;
import org.openqa.selenium.WebDriver;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;

public class LoginPageSteps {
    WebDriver driver;
    HomePageObject homePageObject;
    LoginPageObject loginPageObject;
    RegisterPageObject registerPageObject;

    private TestContext testContext;


    public LoginPageSteps(TestContext testContext){
        this.testContext = testContext;
        driver = Hooks.openAndQuitBrowserNotUrl();
    }

    @Cho("^truy cập vào trang \"([^\"]*)\"$")
    public void truyCậpVàoTrang(String arg0)  {
        loginPageObject = PageGeneralManager.openLoginPage(driver);
        loginPageObject.goToUrl(arg0);
    }

    @Và("^ấn vào trang tạo tài khoản$")
    public void ấnVàoTrangTạoTàiKhoản() {
       registerPageObject = loginPageObject.clickToRegister();
    }

    @Và("^đăng nhập với tài khoản và mật khẩu vừa tạo$")
    public void đăngNhậpVớiTàiKhoảnVàMậtKhẩuVừaTạo() {
        String user = (String) testContext.getContext("user");
        String pass = (String) testContext.getContext("pass");
       homePageObject = loginPageObject.loginWithUserNameAndPassword(user,pass);
    }
}
