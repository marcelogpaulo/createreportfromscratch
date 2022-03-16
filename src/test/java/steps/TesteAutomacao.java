package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.PageFactory;
import pageObject.HomePage;
import util.Framework;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TesteAutomacao {

    HomePage page;
    Framework frame;
    Scenario scenario;

    @Before
    public void before(Scenario sce) {
        scenario = sce;
    }

    @Given("que seja acessado o site {string}")
    public void queSejaAcessadoOSiteDeCriacao(String url) throws IOException {
        page = PageFactory.initElements(Framework.browserDriver(url), HomePage.class);
        page.validarTituloHomePage(scenario, "WebDriverUniversity.com");
    }

    @When("confiro que existe um link com nome {string} e clico nele")
    public void confiroQueExisteUmLinkComNomePreDefinidoEClicoNele(String nomeDoCurso) throws IOException {
        page.clicarNoCursoSeleniumWebdriver4(nomeDoCurso);
    }

    @And("for efetuado o cadastro com o nome {string} e ultimo nome {string} e o email {string}")
    public void forEfetuadoOCadastroComONomeEUltimoNomeEOEmail(String string, String string2, String string3) {

    }

    @Then("e validado a mensagem de criacao de usuario {string}")
    public void eValidadoAMensagemDeCriacaoDeUsuario(String string) {

    }

    @After
    public void after(Scenario scenario) throws IOException {
        frame = new Framework(scenario, Status.FAILED);
    }

}
