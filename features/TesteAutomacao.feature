Feature: Criando usuário dentro do sistema

  Scenario: Teste alteração do Título do Scenario

    Given que acesso o site "https://www.decolar.com/"
    When clico no botão de Hospedagens
    And informo destino "Porto Alegre, Rio Grande do Sul, Brasil"
    And informo data de Entrada e Saída pré-definidas
    And escolho dois adultos e duas crianças, sendo que uma delas tem "9" anos e a outra "10"
    Then valido que pesquisa foi realizada com sucesso