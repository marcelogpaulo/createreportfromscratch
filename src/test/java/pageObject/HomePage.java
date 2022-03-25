package pageObject;

import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Framework;

import java.io.IOException;
import java.time.Duration;

public class HomePage {

    WebDriver webDriver;
    WebDriverWait wait;
    Framework frame;
    Actions actions;

    @FindBy(xpath = "//*[@class='button-circle-label' and contains(text(),'Hospedagens')]")
    WebElement botaoHospedagens;

    @FindBy(xpath = "//*[@class = 'login-incentive--close shifu-3-icon-close -eva-3-mr-md']")
    WebElement modalIniciarSessaoBotaoFechar;

    @FindBy(xpath = "//*[@class='sbox5-title']")
    WebElement tituloModalHospedagens;

    @FindBy(xpath = "//*[@class='sbox-places-destination--1xd0k']//input")
    WebElement inputDestino;

    @FindBy(xpath = "//li[@class='item -active']")
    WebElement destinoAutoComplete;

    @FindBy(xpath = "//*[@class='sbox5-floating-tooltip sbox5-floating-tooltip-opened']")
    WebElement calendarioFlutuante;

    @FindBy(xpath = "//*[@placeholder='Entrada']")
    WebElement inputDataEntrada;

    @FindBy(xpath = "//*[@class='sbox5-monthgrid-datenumber -today']")
    WebElement dataDiaAtual;

    @FindBy(xpath = "(//*[@class='sbox5-monthgrid-datenumber -today'])[3]")
    WebElement dataDiaAtual3;

    @FindBy(xpath = "(//*[@class = 'sbox5-monthgrid-datenumber -selected -limit-date -today']/following-sibling::div)[1]")
    WebElement dataFinalDiaAtualMaisUm;

    @FindBy(xpath = "//*[@class='sbox5-3-btn -primary -md']")
    WebElement botaoAplicarDatasEscolhidas;

    @FindBy(xpath = "//*[@id='svg-bed-378XaOe']")
    WebElement campoQuartos;

    @FindBy(xpath = "(//*[@class='row__text__title' and text()='Menor'])[2]")
    WebElement tituloQuantidadeCriancas;

    @FindBy(xpath = "//*[@class='row__text__title' and text()='Idade do menor 1']")
    WebElement tituloQuantidadeCriancasMenoresDeUmAno;

    @FindBy(css = "#component-modals > div.sbox5-floating-tooltip.sbox5-floating-tooltip-opened > div > div > div.stepper__room > div.stepper__distribution_container > div:nth-child(2) > div.stepper__room__row__stepper__contaer > div > button.steppers-icon-right.stepper__icon")
    WebElement botaoMaisCrianca;

    @FindBy(xpath = "(//*[@class='select'])[1]")
    WebElement comboboxCriacaMenorPrimeira;

    @FindBy(xpath = "(//*[@class='select'])[2]")
    WebElement comboboxCriacaMenorSegunda;

    @FindBy(xpath = "(//a[@class='sbox5-3-btn -md -primary'])[2]")
    WebElement botaoAplicarQuantidadePessoas;

    @FindBy(xpath = "//button[@type='button' and @class='sbox5-box-button-ovr sbox5-3-btn -secondary -icon -lg']")
    WebElement botaoBuscar;

    @FindBy(xpath = "(//*[@class='accommodation-name -eva-3-ellipsis'])[1]")
    WebElement primeiroResultadoPesquisa;
    
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        webDriver = driver;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        actions = new Actions(webDriver);
    }

    public void validarTituloHomePage(Scenario scenario, String title) throws IOException {
        frame = new Framework(scenario);
        Assert.assertEquals(title, webDriver.getTitle());
        frame.test(Given.getGherkinName());
    }

    public void fecharModalDeInicioDeSessao() {
        if (modalIniciarSessaoBotaoFechar.isDisplayed()) {
            modalIniciarSessaoBotaoFechar.click();
        }
        else System.out.println("Modal de Inicio de Sessão não foi apresentado.");
    }

    public void clicarBotaoHospedagens() throws IOException {
        Assert.assertEquals("Hospedagens", botaoHospedagens.getText());
        botaoHospedagens.click();
        frame.test(When.getGherkinName());
    }

    public void informarDestino(String destino) throws IOException, InterruptedException {
        Assert.assertEquals("Hospedagens", tituloModalHospedagens.getText());
        inputDestino.click();
        Thread.sleep(2000);
        inputDestino.sendKeys(destino);
        actions.moveToElement(destinoAutoComplete).click().build().perform();
        frame.test(And.getGherkinName());
    }

    public void clicarNoCampoDataEntrada() {
        inputDataEntrada.click();
        waitVisibilityOf(calendarioFlutuante);
    }

    public void informarDataEntradaDiaAtual() throws Exception {
        validandoVisibilidadeEntreDoisWebElements(dataDiaAtual, dataDiaAtual3);
        Thread.sleep(1000);
    }

    public void informarDataSaidaDiaAtualMaisUm() {
        waitVisibilityOf(dataFinalDiaAtualMaisUm);
        actions.moveToElement(dataFinalDiaAtualMaisUm).click().build().perform();
    }

    public void validandoVisibilidadeEntreDoisWebElements(WebElement primeiroWebElemento, WebElement segundoWebElement) throws Exception {
        if (primeiroWebElemento.isDisplayed()) {
            waitVisibilityOf(primeiroWebElemento);
            actions.moveToElement(primeiroWebElemento).click().build().perform();
        }
        else if (segundoWebElement.isDisplayed()) {
            waitVisibilityOf(segundoWebElement);
            actions.moveToElement(segundoWebElement).click().build().perform();
        }
        else throw new Exception("ELEMENT NOT FOUND");
    }

    public void clicarBotaoAplicarDatasEscolhidas() throws IOException {
        actions.moveToElement(botaoAplicarDatasEscolhidas).click().build().perform();
        frame.test(And.getGherkinName());
    }

    public void clicarCampoQuartos() {
        campoQuartos.click();
    }

    public void informarQuantidadeCriancas() {
        waitVisibilityOf(tituloQuantidadeCriancas);
        actions.moveToElement(botaoMaisCrianca).click().build().perform();
        actions.moveToElement(botaoMaisCrianca).click().build().perform();
    }

    public void informarIdadeDaCrianca(String idadePrimeiraCrianca, String idadeSegundaCrianca) {
        waitVisibilityOf(tituloQuantidadeCriancasMenoresDeUmAno);
        new Select(comboboxCriacaMenorPrimeira).selectByValue(idadePrimeiraCrianca);
        new Select(comboboxCriacaMenorSegunda).selectByValue(idadeSegundaCrianca);
    }

    public void clicarBotaoAplicarQuantidadePessoas() {
        botaoAplicarQuantidadePessoas.click();
    }

    public void clicarBotaoBuscar() throws IOException {
        botaoBuscar.click();
        frame.test(And.getGherkinName());
    }

    public void validarPesquisaRealizadaComSucesso() throws Exception {
        if (!primeiroResultadoPesquisa.isDisplayed()) {
            throw new Exception("Pesquisa não foi realizada com sucesso");
        }
        frame.test(Then.getGherkinName());
    }

    public void waitVisibilityOf(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}
