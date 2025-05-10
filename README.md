# 📘 *Spring Starts Here* — Laurentiu Spilca

> *“Uma introdução prática e progressiva ao universo Spring.”*

Este repositório é dedicado ao registro do meu progresso e aprendizado com o livro **_Spring Starts Here_**, de **Laurentiu Spilca**. A obra é organizada de forma didática, sendo dividida em duas grandes partes, além de apêndices complementares. O objetivo do livro é guiar o leitor desde os fundamentos da plataforma Spring até a construção de aplicações modernas com Spring Boot e Spring MVC.


## Parte 1 - Fundamentos do Spring

Na primeira parte do livro, exploramos os **fundamentos do framework Spring**, partindo do básico:

- O que é um *framework*?
- Por que usar o Spring?
- Quais problemas ele resolve?

Em seguida, o autor apresenta os principais pilares do **Spring Core**:

### Spring Context

Também chamado de `ApplicationContext`, é o **coração do Spring**. Ele gerencia os objetos (beans) da aplicação e suas dependências. Isso permite criar aplicações desacopladas, testáveis e de fácil manutenção, graças ao uso de Inversão de Controle (IoC) e Injeção de Dependência (DI).

### Spring AOP (Aspect-Oriented Programming)

A Programação Orientada a Aspectos permite lidar com funcionalidades transversais, como logging, segurança e auditoria, sem poluir o código principal da aplicação. Com AOP, podemos aplicar comportamentos a métodos e classes de forma declarativa, melhorando a modularidade do sistema.

## Parte 2 - Aplicações Web com Spring Boot

A segunda parte foca no **desenvolvimento web com Spring**, com uma abordagem prática e progressiva.

### Introdução a Aplicações Web

- O que é uma aplicação web?
- Como funciona o modelo **cliente-servidor**?
- O papel do **HTTP** nas comunicações.
- Introdução ao padrão **MVC (Model-View-Controller)**.

A seguir, mergulhamos na criação de aplicações modernas usando o Spring Boot e outros temas como:

* Construção e consumo de serviços REST com Spring MVC;

* Uso de datasources, gerenciamento de transações e implementação de persistência de dados com Spring Data;

* Desenvolvimento de testes unitários e testes de integração, promovendo qualidade e confiabilidade ao código.

A segunda parte é mais prática, mas sem perder a solidez teórica — oferecendo uma compreensão robusta do ciclo de vida de uma aplicação Spring, desde a requisição HTTP até a persistência no banco de dados.


## Apêndices - HTTP e Arquitetura de Sistemas
Os apêndices do livro trazem conteúdos fundamentais para complementar o entendimento do leitor, com foco especial em:

* Uma introdução clara e prática ao protocolo HTTP, com explicações sobre métodos, códigos de status, headers e body das requisições/respostas.

* Discussão sobre diferentes estilos arquiteturais (como monólitos e microsserviços) e como eles se relacionam com o desenvolvimento moderno usando Spring.

Esses capítulos extras ajudam a consolidar o conhecimento e oferecem uma visão mais ampla do desenvolvimento web, conectando as ferramentas do Spring a conceitos maiores de arquitetura de software e boas práticas de projeto.

---