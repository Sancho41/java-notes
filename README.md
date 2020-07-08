# Projeto EscreveAí 

Este é um projeto utilizando Jersey, JAX-rs e Vue.js de um sistemas de notas de lembretes que foi feito um deploy no heroku.

## Detalhes do projeto
### Backend
No backend da aplicação foi feito utilizando o MAVEN. Como blibliotecas principais foram utilizados **Jersey**, **JAX-rs**, **JPA** e **Hibernate**.

### Frontend
No frontend foi utilizado basicamente **Vue.js** utilizando a framework e o CLI **NUXT**.

### Banco de dados
A aplicação utiliza um arquivo de sqlite como banco de dados. Essa escolha foi feita para minizar as preocupações com outras tecnologias, assim focando o aprendizado no JAVA e suas bibliotecas.

## Executando o projeto localmente

- Faça o download e instalação do MAVEN (Para instruções de instalação [clique aqui](https://maven.apache.org/install.html))
- Faça o build da aplicação: 
```shell
$ mvn package
```
- E rode aplicação com o comando:
```
$ java target/dependency/webapp-runner.jar target/*.war
```
- É isso! A aplicação deverá estar rodando na porta 8080!

## Fazendo o deploy no heroku
É possível fazer o deploy dessa aplicação diretamente no heroku. Basta clicar no botão abaixo:

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/Sancho41/java-notes)