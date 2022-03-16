package pageObject;

import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.When;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Framework;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HomePage {

    WebDriver webDriver;
    WebDriverWait wait;
    Framework frame;

    @FindBy(xpath = "//a[text() = 'Selenium Webdriver 4 New Features In Detail']")
    WebElement linkSeleniumWebdriver4;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
    }

    public void validarTituloHomePage(Scenario scenario, String title) throws IOException {
        frame = new Framework(scenario);
        Assert.assertEquals(title, webDriver.getTitle());
        frame.test(Given.getGherkinName());
    }

    public void clicarNoCursoSeleniumWebdriver4(String nomeDoCursoCapturado) throws IOException {
        Assert.assertEquals("Selenium Webdriver 4 New Features In Detail", nomeDoCursoCapturado);
        linkSeleniumWebdriver4.click();
        frame.test(When.getGherkinName());
    }
}
