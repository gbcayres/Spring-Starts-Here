# Capítulo 7

* **Primeiro Web App**
* **Introdução ao Spring Boot**
* **Arquitetura MVC**

## O que é uma Aplicação Web?

Uma aplicação web é qualquer sistema acessado pelo navegador. Antigamente, era comum depender exclusivamente de aplicativos instalados localmente. Hoje, grande parte das interações digitais ocorre diretamente pelo navegador.

Tecnicamente, uma aplicação web consiste em duas partes fundamentais:

* **Frontend (lado cliente)**: roda no navegador e é onde o usuário interage diretamente com a aplicação, enviando requisições ao servidor e exibindo as respostas recebidas.

* **Backend (lado servidor)**: recebe as requisições, processa a lógica de negócios, acessa bancos de dados ou outros recursos, e retorna uma resposta para o frontend.

Na prática, vários usuários acessam simultaneamente o backend. Por isso, é crucial escrever código preparado para ambientes concorrentes, evitando problemas como *race conditions*.

## 7.1.2 - Abordagens para Desenvolver um Web App com Spring

Existem duas abordagens principais ao desenvolver aplicações web com Spring:

* **Backend com renderização de views (sem separação)**: o servidor já devolve uma página HTML pronta ao navegador, que apenas renderiza o conteúdo.

* **Frontend e backend separados**: o servidor envia apenas os dados necessários (em JSON, por exemplo), e o frontend determina como exibir e interagir com esses dados.

Ambas as abordagens são amplamente utilizadas. A primeira é recomendada para aplicações simples ou pequenas, enquanto a segunda é mais moderna e escalável, facilitando inclusive o trabalho de equipes separadas (frontend e backend).

## 7.1.3 - Web Servers ou Servlet Containers

Navegadores utilizam o protocolo HTTP para enviar e receber dados pela web. O Java, entretanto, não compreende diretamente esse protocolo em sua forma pura. Para conectar o protocolo HTTP às aplicações Java, utilizamos um web server, também chamado de servlet container. Um exemplo conhecido é o **Apache Tomcat**.

Esses servidores atuam como intermediários: recebem requisições HTTP, interpretam detalhes como o caminho (*endpoint*) e o método utilizado (GET, POST, etc.), e convertem essas informações em objetos Java que o sistema pode manipular.

Dentro da aplicação, os componentes responsáveis por tratar essas requisições são chamados de **servlets**. Quando o servidor recebe uma requisição, identifica o servlet associado ao caminho solicitado e encaminha o processamento a ele. Cada servlet recebe dois objetos principais: um para a requisição e outro para a resposta. Esses objetos encapsulam detalhes importantes da comunicação HTTP, como parâmetros, cabeçalhos e o corpo da mensagem.

Dessa forma, o desenvolvedor pode focar apenas na lógica da aplicação, sem precisar lidar diretamente com a complexidade do protocolo HTTP.

## 7.2 - Spring Boot

O **Spring Boot** é um framework que simplifica o desenvolvimento de aplicações Java, especialmente voltadas para web e microserviços. Sua principal proposta é acelerar o processo de criação ao eliminar grande parte da configuração manual exigida em projetos tradicionais.

Ele adota o princípio de *convention over configuration* (convenção sobre configuração), aplicando automaticamente valores padrão e sensatos para diversos aspectos da aplicação — de acordo com as dependências previamente selecionadas. Assim, o desenvolvedor pode se concentrar na lógica de negócio, ajustando apenas o que realmente precisa ser personalizado.

Por exemplo, ao adicionar a dependência starter web, o Spring Boot automaticamente configura:

- Um servidor embutido (por padrão, o Tomcat).
- Configurações padrão de serialização.
- Outras definições iniciais baseadas nas dependências adicionadas.

Dessa forma, é possível iniciar rapidamente um projeto funcional, sem a necessidade de configurar tudo manualmente desde o início.


## 7.3 - Spring MVC

O núcleo da arquitetura MVC (*Model-View-Controller*) do Spring é o **Dispatcher Servlet**.

Pense no Dispatcher Servlet como um único servlet centralizado, que substitui a necessidade de múltiplos servlets específicos. Ele age como um roteador interno que intercepta todas as requisições HTTP e determina como elas serão processadas.

Graças a ele, você não precisa configurar servlets manualmente. Toda configuração é feita com anotações como `@Controller`, `@RestController`, e `@GetMapping`. Assim, o Dispatcher Servlet automaticamente encaminha as requisições aos métodos adequados.

Ele é o ponto inicial de toda requisição recebida pela aplicação, também conhecido como **front controller**.

### Como Funciona o Dispatcher Servlet?

Para entender bem esse fluxo, vejamos passo a passo o caminho de uma requisição HTTP:

1. O cliente envia uma requisição HTTP.

2. O servidor Tomcat recebe e imediatamente encaminha a requisição ao Dispatcher Servlet.

3. O Dispatcher Servlet precisa descobrir qual método no controller deve ser executado. Essa tarefa é delegada ao componente interno chamado Handler Mapping, que analisa a URL e o método HTTP da requisição para identificar o método correto no controller (geralmente marcado com `@RequestMapping` ou derivados como `@GetMapping`).

4. Após localizar o método, o Dispatcher Servlet o executa. Esse método então retorna o nome de uma view, geralmente indicando o arquivo HTML a ser exibido (por exemplo, `home.html`).

5. O Dispatcher Servlet utiliza o componente interno chamado View Resolver para transformar o nome retornado pelo controller em um arquivo real (geralmente localizado nas pastas `resources/templates` ou `resources/static`).

6. Finalmente, o Dispatcher Servlet encapsula o conteúdo da view em uma resposta HTTP, envia essa resposta ao Tomcat, que então devolve ao navegador. O navegador interpreta o HTML recebido e exibe a página para o usuário.
---