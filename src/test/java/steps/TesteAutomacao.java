package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.Status;
import io.cucumber.java.en.*;
import org.openqa.selenium.support.PageFactory;
import pageObject.HomePage;
import util.Framework;

import java.io.IOException;

public class TesteAutomacao {

    HomePage page;
    Framework frame;
    Scenario scenario;

    @Before
    public void before(Scenario sce) {
        scenario = sce;
    }

    @Given("que acesso o site {string}")
    public void queSejaAcessadoOSiteDeCriacao(String url) throws IOException {
        page = PageFactory.initElements(Framework.browserDriver(url), HomePage.class);
        page.validarTituloHomePage(scenario, "Viaje com a maior agência de viagens online do Brasil | Decolar");
        page.fecharModalDeInicioDeSessao();
    }

    @When("clico no botão de Hospedagens")
    public void ClicoNoBotaoDeHospedagens() throws IOException {
        page.clicarBotaoHospedagens();
    }

    @And("informo destino {string}")
    public void informoDestino(String destino) throws IOException, InterruptedException {
        page.informarDestino(destino);
    }

    @And("informo data de Entrada e Saída pré-definidas")
    public void informoDataDeEntradaESaidaPreDefinidas() throws Exception {
        page.clicarNoCampoDataEntrada();
        page.informarDataEntradaDiaAtual();
        page.informarDataSaidaDiaAtualMaisUm();
        page.clicarBotaoAplicarDatasEscolhidas();
    }

    @And("escolho dois adultos e duas crianças, sendo que uma delas tem {string} anos e a outra {string}")
    public void escolhoDoisAdultosEDuasCriançasVariandoSuasIdades(String idadePrimeraCrianca, String idadeSegundaCrianca) throws Exception {
        page.clicarCampoQuartos();
        page.informarQuantidadeCriancas();
        page.informarIdadeDaCrianca(idadePrimeraCrianca, idadeSegundaCrianca);
        page.clicarBotaoAplicarQuantidadePessoas();
        page.clicarBotaoBuscar();
    }

    @Then("valido que pesquisa foi realizada com sucesso")
    public void validoQuePesquisaFoiRealizadaComSucesso() throws Exception {
        page.validarPesquisaRealizadaComSucesso();
    }

    @After
    public void after(Scenario scenario) throws IOException {
        frame = new Framework(scenario, Status.FAILED);
    }

}
