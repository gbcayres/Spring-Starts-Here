# Capítulo 9
* Spring Web Scopes
  * Request Scope
  * Session Scope
  * Application Scope

## Usando Spring Web Scopes
Como vimos no capítulo 5, o Spring pode gerenciar seus beans — objetos controlados pelo Spring Context — de diferentes formas, conhecidas como scopes (escopos). Já conhecíamos o Singleton Scope e o Prototype Scope. Neste capítulo, vamos explorar os escopos específicos do Spring Web.

### Request Scope / Escopo de Requisição
Um bean com escopo de requisição é um objeto gerenciado pelo Spring, para o qual uma nova instância é criada a cada requisição HTTP recebida. Cada instância está vinculada exclusivamente à requisição que a originou. Algumas características importantes desse tipo de bean são:

* O Spring cria uma nova instância do bean a cada requisição, o que significa que muitas instâncias são geradas durante o ciclo de vida da aplicação;

  No entanto, esse alto volume de instâncias geralmente não representa um problema, pois elas têm vida curta. Assim que a requisição é concluída, as instâncias são desreferenciadas e rapidamente coletadas pelo Garbage Collector;

* Beans com escopo de requisição não enfrentam problemas de concorrência, já que são acessados por apenas uma thread — a mesma que trata a requisição;

* Ainda assim, é recomendável evitar lógicas complexas em sua criação, como chamadas a banco de dados ou serviços externos.

## Session Scope / Escopo de Sessão
Um bean com escopo de sessão é um objeto gerenciado pelo Spring cujo o Spring vincula uma instância criada à *sessão* HTTP. Cada instância é criada juntamente a sessão HTTP do cliente específico, e é usada enquanto a mesma estiver ativa. Dessa forma, dados armazenados em beans com requisição de escopo podem ser usados durante toda a sessão, por requisições diferentes.
Algumas características importantes desse tipo de bean são:

* Beans com escopo de sessão têm um ciclo de vida mais longo do que os beans de escopo de requisição. Consequentemente, são coletados pelo Garbage Collector com menos frequência.

* A aplicação mantém os dados armazenados nesses beans por mais tempo. Isso pode ser útil quando você precisa compartilhar informações entre requisições de um mesmo usuário, como o conteúdo de um carrinho de compras — no caso de usuários não logados.

* Nunca armazene dados sensíveis, como senhas, chaves privadas ou qualquer informação secreta, em atributos de beans de sessão. A sessão não é um lugar seguro para esse tipo de informação.

* A mesma instância do bean de sessão pode ser acessada por múltiplas requisições simultâneas do mesmo cliente. Isso significa que, se duas ou mais requisições concorrentes tentarem modificar o bean ao mesmo tempo, podem surgir problemas relacionados à concorrência, como race conditions.

## Application Scope / Escopo de Aplicação
Um bean com escopo de aplicação é uma instância gerenciada pelo Spring que é compartilhada por toda a aplicação web. Ele é criado uma única vez, no início da aplicação (ou na primeira vez que for solicitado), e permanece disponível enquanto o contexto da aplicação estiver ativo — geralmente, até o encerramento do servidor.

Isso significa que o bean é compartilhado entre todas as requisições e sessões, independentemente do cliente. Na prática, ele funciona como um singleton dentro do contexto da aplicação web.

O autor desencoraja o uso do escopo de aplicação, embora ele possa ser útil para armazenar dados globais, como contagens de login. No entanto, ele compartilha as mesmas desvantagens do Singleton Scope, como a necessidade de sincronização, o que pode causar um impacto significativo no desempenho da aplicação.