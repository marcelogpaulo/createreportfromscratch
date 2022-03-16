package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class Framework {
    static WebDriver driver;
    static ExtentReports extent;
    static ExtentTest logger;

    static FileReader fileReader;
    static BufferedReader reader;
    static String textStep;

    @BeforeAll
    public static WebDriver browserDriver(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    public static void init() {
        ExtentSparkReporter reporter = new ExtentSparkReporter("Report" + File.separator + "extentReport.html");
        reporter.config().setDocumentTitle("Report");
        reporter.config().setReportName("Tests");
        reporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("System Operation", System.getProperty("os.name").toUpperCase());
        extent.setSystemInfo("System Operation", System.getProperty("user.name").toUpperCase());
    }

    public Framework(Scenario scenario, Status status) throws IOException {
        if (scenario.isFailed()) {
            logger.fail(status + "<br><font color=red>" + status + "</font></br>");
            logger.addScreenCaptureFromPath(capture(), "<br><font color=red>" + status + " click screenshot</font></br>");
        }
        extent.flush();
        driver.quit();
        FileOutputStream write = new FileOutputStream("file.txt");
    }

    public Framework(Scenario scenario) {
        logger = extent.createTest(scenario.getName());
    }

    public void test(String typeStep) throws IOException {
        fileReader = new FileReader("file.txt");
        reader = new BufferedReader(fileReader);
        while ((textStep = reader.readLine()) != null) {
            if (textStep.contains(typeStep)) {
                logger.pass(GetStep.gherkin + "<br><font color=Lime>" + Status.PASSED + "</font></br>");
//                logger.pass("<font color=Lime>" + GetStep.gherkin + "</font>" + "<br><font color=Lime>" + Status.PASSED + "</font></br>");
                logger.addScreenCaptureFromPath(capture(), "<br><font color=Lime>" + typeStep + " click screenshot</font></br>");
            }
        }
    }

    public static String capture() throws IOException {
        return ScreenshotReport.screenshot(driver);
    }

    public static void remove(File f) {
        if (f.isDirectory()) {
            File[] files = f.listFiles();
            for (int i = 0 ; i < files.length; i++) {
                remove(files[i]);
            }
        }
        f.delete();
    }

}
