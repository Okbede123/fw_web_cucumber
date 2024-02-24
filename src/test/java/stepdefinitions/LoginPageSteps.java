package stepdefinitions;

import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.utils.files.Utils;
import commons.Hooks;
import commons.PageGeneralManager;
import commons.TestContext;
import cucumber.api.java.vi.Cho;
import cucumber.api.java.vi.Và;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobject.HomePageObject;
import pageobject.LoginPageObject;
import pageobject.RegisterPageObject;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

public class LoginPageSteps {
    WebDriver driver;
    HomePageObject homePageObject;
    LoginPageObject loginPageObject;
    RegisterPageObject registerPageObject;

    private static final Logger LOGGER = LoggerFactory.getLogger("binary_data_logger");

    private TestContext testContext;


    public LoginPageSteps(TestContext testContext){
        this.testContext = testContext;
        driver = Hooks.getDriver();
    }

    @Cho("^truy cập vào trang \"([^\"]*)\"$")
    public void truyCậpVàoTrang(String arg0)  {
        loginPageObject = PageGeneralManager.openLoginPage(driver);
        loginPageObject.goToUrl(arg0);
        ReportPortal.emitLog("test","info",new Date(),new File("C:\\Users\\Admin\\Downloads\\huongdan.png"));
    }

    @Và("^ấn vào trang tạo tài khoản$")
    public void ấnVàoTrangTạoTàiKhoản() throws IOException {
       registerPageObject = loginPageObject.clickToRegister();
       //su dung log4j, truyen anh dang b64 len
        LOGGER.info("RP_MESSAGE#BASE64#{}#{}",Base64.getEncoder().encodeToString(Utils.getFileAsByteSource(new File("C:\\Users\\Admin\\Downloads\\huongdan.png")).read()),"test");
    }

    @Và("^đăng nhập với tài khoản và mật khẩu vừa tạo$")
    public void đăngNhậpVớiTàiKhoảnVàMậtKhẩuVừaTạo() {
        String user = (String) testContext.getContext("user");
        String pass = (String) testContext.getContext("pass");
       homePageObject = loginPageObject.loginWithUserNameAndPassword(user,pass);
    }
}
