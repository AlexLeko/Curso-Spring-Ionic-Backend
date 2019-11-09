# Curso de Java Spring com Modelagem Conceitual

> **Spring-Boot |  Spring-MVC  |  Spring-Data  |  Spring-Security  |  Spring-Mail**
> **JPA-Hibernate  |  Rest  |  JWT  |  AWS-S3  |  MySQL  |  Swagger**

![](https://raw.githubusercontent.com/AlexLeko/Curso-Spring-Ionic-Backend/master/doc/1360966_4b21_4.jpg)

## Ferramentas e tecnologias

 - Spring Boot: 2.1.7.RELEASE 
 - JDK: 1.8
 - Spring Framework: 5.1.9.RELEASE
 - Maven: 4.0.0 
 - Spring Data JPA: 2.1.7.RELEASE 
 - Hibernate: 5.3.10.Final
 - MySQL: 8.0.17
 - H2-DataBase: 2.1.4.199 
 - Spring-Starter-Mail: 2.1.7.RELEASE
 - Spring-Thymeleaf: 2.1.7.RELEASE 
 - Spring Security: 2.1.7.RELEASE 
 - JWT: 0.7.0 
 - AWS: 1.11.671 
 - Swagger: 2.7.0 
 - IDE: IntelliJ 19.1

## Diagrama de Classe
![](https://raw.githubusercontent.com/AlexLeko/Curso-Spring-Ionic-Backend/master/doc/diagrama.jpg)

## Executar Aplicação
Duas maneiras de iniciar a aplicação:
- No diretório raiz da aplicativo digite o seguinte comando para executá-lo.
`$ mvn spring-boot:run`

- No seu IDE, execute o método CursomcApplication.main() como uma classe Java independente que iniciará o servidor Tomcat incorporado na porta 8080 e acessar no navegador: `Link`: http://localhost:8080

## Ambientes

Esta aplicação atua com três ambientes e com banco de dados diferentes para os ambientes de Teste e Desenvolvimento/Produção.

Para executar a aplicação deverá informar qual o ambiente de atuação, sendo por padrão o ambiente de Teste. Também será necessário inserir no arquivo application.properties algumas informações específicas para cada ambiente em execução:

- Url de conexão com o banco de dados: H2 / MySql;
- Senha da base de dados (Caso você informe, durante a criação);
- Usuário e senha, de um e-mail com domínio Gmail, para envio dos e-mails;
- Palavra secreta para criptografia do token de segurança;
- Definir o tempo de expiração do token;
- Id e senha da conta na AWS, para armazenamento das imagens na S3;

### Ambiente Teste
- `Config:` application-test.properties

Durante a execução no ambiente de Teste, a aplicação executa a migration e gera uma carga inicial de dados no banco H2, sendo possível sua visualização: `Link:` http://localhost:8080/h2-console/, estes dados ficaram em memória somente durante a execução da aplicação neste ambiente.

### Ambiente Desenvolvimento / Produção
- `Config:` application-dev.properties 
- `Config:` application-prod.properties

Já nos ambientes de Desenvolvimento ou Produção, será executada a migration somente uma única vez, para a criação da base de dados e não carregará uma carga inicial de dados, nestes ambientes está sendo utilizado o banco de dados MySQL.
