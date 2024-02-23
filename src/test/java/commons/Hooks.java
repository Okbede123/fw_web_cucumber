package commons;

import cucumber.api.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.After;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class Hooks {
    private static WebDriver driver;

    public synchronized static WebDriver openAndQuitBrowserNotUrl() {
        String browser = System.getProperty("BROWSER");
        System.out.println("Browser name run by command line = " + browser);
        if (driver == null) {
            if (browser == null) {
                browser = System.getenv("BROWSER");
                if (browser == null) {
                    browser = "chrome";
                }
            }
            switch (browser) {
                case "chrome":
                    driver = new ChromeDriver();
                    break;
                case "ie":
                    WebDriverManager.iedriver().arch32().setup();
                    driver = new InternetExplorerDriver();
                    break;
                default:
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
            }

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            System.out.println("khoi tao driver");
        }
        return driver;
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
    public static WebDriver getDriver(){
        return Hooks.driver;
    }

    public static void setDriver(WebDriver driver) {
        Hooks.driver = driver;
    }

    public synchronized static WebDriver openAndQuitBrowserChrome() {
        System.out.println("run vao before");
        // Run by Maven command line
        String browser = System.getProperty("BROWSER");
        System.out.println("Browser name run by command line = " + browser);

        if (driver == null) {

            try {
                if (browser == null) {
                    // Get browser name from Environment Variable in OS
                    browser = System.getenv("BROWSER");
                    if (browser == null) {
                        browser = "chrome";
                    }
                }
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } catch (UnreachableBrowserException e) {
                driver = new ChromeDriver();
            } catch (WebDriverException e) {
                driver = new ChromeDriver();
            }
            finally {
                Runtime.getRuntime().addShutdownHook(new Thread(new BrowserCleanupChrome()));
            }

            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        }
        return driver;
    }

    private static class BrowserCleanupChrome implements Runnable {
        private BrowserCleanupChrome(){
        }
        @Override
        public void run() {
            closeChrome();
        }
    }

    public static void closeChrome() {
        try {
            if (driver != null) {
                openAndQuitBrowserChrome().quit();
            }
        } catch (UnreachableBrowserException e) {
            System.out.println("Can not close the browser");
        }
    }

}
