Feature: Criando usuário dentro do sistema

  Scenario: Teste alteração do Título do Scenario

    Given que seja acessado o site "http://webdriveruniversity.com/index.html"
    When confiro que existe um link com nome "Selenium Webdriver 4 New Features In Detail" e clico nele
    And for efetuado o cadastro com o nome "Michael" e ultimo nome "Jackson" e o email "michaeljackson@jacksonfive.com"
    Then e validado a mensagem de criacao de usuario "Usuário Criado com sucesso"