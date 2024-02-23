package stepdefinitions;

import com.epam.reportportal.cucumber.AbstractReporter;
import com.epam.reportportal.cucumber.ScenarioReporter;
import com.epam.reportportal.service.ReportPortal;
import com.epam.reportportal.service.ReportPortalClient;
import com.epam.reportportal.utils.MemoizingSupplier;
import com.epam.ta.reportportal.ws.model.StartTestItemRQ;
import com.google.common.base.Suppliers;
import commons.Hooks;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.reactivex.Maybe;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Supplier;

public class Setup  {

    public static WebDriver driver;

    @After
    public void after(Scenario scenario){
        System.out.println("run vao teardown");
        if(Hooks.getDriver() != null){
            if (scenario.isFailed()) {
                final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                System.out.println("run vao fail");
                scenario.embed(screenshot, "image/png");
            }
            Hooks.getDriver().quit();
            Hooks.setDriver(null);
            after();
        }

    }

    @Before
    public void before(){
        System.out.println("run before");
        driver = Hooks.openAndQuitBrowserNotUrl();

    }

    public void testReport() {
        System.out.println("after junit");
        File reportOutputDirectory = new File("target/test_report");
        List<String> jsonFiles = new ArrayList<>();
        File file = new File("target/site");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            if(files[i].isFile() && files[i].getName().endsWith(".json")){
                jsonFiles.add(files[i].getAbsolutePath());
            }
        }
        String buildNumber = "1";
        String projectName = "fw_web_cucumber";
        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable reportable = reportBuilder.generateReports();
        System.out.println(reportable.getPassedFeatures() + " get pass");
        System.out.println(reportable.getScenarios());
        System.out.println(reportable.getPassedScenarios());
        System.out.println(reportable.getDuration());
        System.out.println(reportable.getPassedSteps());
        System.out.println("get fail scenario " + reportable.getFailedScenarios());
    }

    public void after()  {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Thực hiện các công việc cần thiết khi ứng dụng kết thúc
            System.out.println("Executing shutdown hook...");
            // Gọi hàm khác sau khi quá trình runtime dừng lại
            testReport();
        }));
    }


}
