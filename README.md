# Projeto EscreveAí 

[![Deploy](https://www.herokucdn.com/deploy/button.svg)](https://heroku.com/deploy?template=https://github.com/Sancho41/java-notes)

Este é um projeto utilizando Jersey, JAX-rs e Vue.js de um sistemas de notas de lembretes que foi feito um deploy no heroku.

[Clique aqui](https://java-notes-sancho.herokuapp.com/) para acessar uma instância no [Heroku](https://heroku.com/)

## Detalhes do projeto
### Backend
No backend da aplicação foi feito utilizando o [MAVEN](https://maven.apache.org/). Como blibliotecas principais foram utilizados [Jersey](https://eclipse-ee4j.github.io/jersey/), **JPA** e [Hibernate](https://hibernate.org/).

### Frontend
No frontend foi utilizado basicamente [Vue.js](https://vuejs.org/) utilizando a framework e o CLI [NUXT](https://nuxtjs.org/).

### Banco de dados
A aplicação utiliza um arquivo de sqlite como banco de dados. Essa escolha foi feita para minizar as preocupações com outras tecnologias, assim focando o aprendizado no JAVA e suas bibliotecas.

## Executando o projeto localmente

- Faça o download e instalação do [MAVEN](https://maven.apache.org/) (Para instruções de instalação [clique aqui](https://maven.apache.org/install.html))
- Faça o build da aplicação: 
```shell
$ mvn package
```
- E rode aplicação com o comando:
```
$ java target/dependency/webapp-runner.jar target/*.war
```
- É isso! A aplicação deverá estar rodando na porta 8080!