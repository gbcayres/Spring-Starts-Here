# Capítulo 10
* Entendendo REST
* Implementando endpoints REST
* JSON
* Serialização e Desserialização
* DTO
* Gerenciando a resposta HTTP
* Tratando exceções em REST

## REST - *Representational State Transfer*
Diferente de um protocolo ou tecnologia específica, REST (Representational State Transfer) é um estilo arquitetural que orienta como os recursos devem ser representados e manipulados por meio de requisições HTTP.

Em um sistema RESTful, cada recurso — como um produto, usuário ou pedido — deve ser identificado por uma URI única, e a interação com esses recursos é feita de forma *stateless*. Ou seja, cada requisição deve conter todas as informações necessárias para que o servidor a compreenda e a processe, sem depender de contexto anterior armazenado no servidor.

Essa abordagem é útil para conectar:

* um frontend web ao backend;

* um app mobile ao servidor;

* ou até mesmo dois serviços de backend entre si.

### Considerações
Apesar de sua simplicidade, a implementação de serviços REST requer atenção a possíveis riscos e limitações:

* Chamadas que demoram demais podem exceder o tempo limite da conexão HTTP.

* O envio de grandes volumes de dados em uma única requisição pode causar lentidão ou falhas por timeout.

* Endpoints muito acessados podem sofrer com sobrecarga e instabilidade.

* Como REST depende da rede, falhas de conectividade devem ser previstas e tratadas com tolerância.

Por isso, ao projetar serviços REST, é essencial refletir sobre os impactos de uma falha na comunicação:

* O que acontece se a requisição não for concluída?

* O sistema pode ficar com dados inconsistentes?

* Como o erro será comunicado ao usuário?

Essas são decisões que vão além da implementação técnica, exigindo cuidado arquitetural e estratégias de resiliência bem definidas.

---
## Implementando REST
Já sabemos que um *controller* ou controlador, é uma classe responsável por definir os métodos ou ações que o Dispatcher Servlet deve executar. Em aplicações que seguem a arquitetura REST, cada um desses métodos é chamado de *endpoint* — um ponto de entrada da aplicação, geralmente associado a uma URI e um verbo HTTP. É através dos endpoints que nós expomos os recursos do sistema e permitimos sua manipulação externa.

A construção de controladores REST no Spring segue a mesma base dos controladores tradicionais — com uma diferença essencial: ao invés de retornarmos o nome de uma view para ser renderizada, retornamos os dados diretamente, já no formato final esperado pelo cliente.

Pra isso, o Spring oferece a anotação @ResponseBody, que informa ao framework que o valor retornado pelo método não deve ser interpretado como o nome de uma view, mas sim serializado diretamente no corpo da resposta HTTP.

Na prática, isso significa que:

1. O método do controlador retorna um objeto Java;

2. O Spring delega a serialização a um `HttpMessageConverter` — geralmente o `MappingJackson2HttpMessageConverter`, que transforma objetos Java em JSON;

3. O JSON gerado é enviado como corpo da resposta HTTP.

Pra facilitar ainda mais, o Spring oferece a anotação `@RestController`, que combina duas funcionalidades: `@Controller` + `@ResponseBody`. Ao usar `@RestController`, você não precisa anotar cada método com `@ResponseBody` — o Spring já entende que todos os métodos da classe devem retornar dados diretamente.

## JSON (*JavaScript Object Notation*)
Em aplicações que se comunicam por meio de endpoints REST, o formato mais utilizado para troca de dados é o **JSON (JavaScript Object Notation)**. Ele serve como uma representação textual de objetos, permitindo a serialização de dados entre cliente e servidor de forma leve, padronizada e amplamente compatível.

No ecossistema Spring, a conversão entre objetos Java e JSON — tanto na ida quanto na volta — é tratada automaticamente pelo *Jackson*, através do `MappingJackson2HttpMessageConverter`, que integra com o mecanismo de serialização do Spring MVC. Sua sintaxe segue regras simples, mas rígidas:

```json
{
  "id": 1,
  "nome": "Teclado",
  "preco": 29.90
}
```

* A estrutura de um objeto é delimitada por chaves {};

* Cada atributo é representado como um par chave: valor, separados por vírgulas;

* Chaves e valores do tipo string são envolvidos por aspas duplas " ";

* Números (inteiros ou decimais) são declarados sem aspas;

* O separador entre chave e valor é sempre o dois-pontos: ":".

## Serialização e Desserialização
Mas como exatamente os dados se transformam de objetos Java para JSON (e vice-versa)? Esse processo envolve dois conceitos fundamentais: serialização e desserialização.

### Serialização
**Serialização** é o processo de converter um objeto em memória para uma sequência — ou *série* — de bytes ou uma estrutura textual (como JSON, XML, YAML ou binário), com o objetivo de transmiti-lo ou armazená-lo.

De forma técnica, serializar significa transformar um objeto estruturado (com atributos, tipos, relacionamentos e estado) em uma forma linear e transportável, preservando seus dados essenciais.

Principais usos:

* **Transmissão de dados**: Envio de dados pela rede, como em requisições HTTP;

* **Persistência**:  Armazenamento de objetos em arquivos, bancos NoSQL ou cache (ex: Redis);

* **Interoperabilidade**: Serializar para JSON permite que sistemas em diferentes linguagens troquem informações.

### Desserialização
Desserialização é o processo inverso: reconstruir um objeto original a partir de uma representação serializada — por exemplo, um JSON recebido no corpo de uma requisição.

No Spring, esse processo é feito automaticamente quando usamos anotações como @RequestBody, que mapeiam os dados recebidos para um DTO ou objeto de domínio.


### DTO (*Data Transfer Object*)
Quando transmitimos dados entre cliente e servidor, usamos o padrão DTO (Data Transfer Object).

Um DTO é uma classe simples, sem lógica de negócio, usada para representar os dados que queremos expor ou receber via API. Seu principal objetivo é isolar a estrutura da API das entidades internas do sistema, como as do banco de dados.

Exemplo:
```java
public record ProdutoDTO(Long id, String nome, BigDecimal preco) {}
```
## Configurando cabeçalhos
Uma resposta HTTP não se resume apenas ao corpo com os dados em JSON. O protocolo HTTP oferece mecanismos poderosos — como os status codes e os cabeçalhos de resposta — que são fundamentais para comunicar o contexto, o resultado e até políticas de controle da requisição.

No Spring, a forma mais versátil e semântica de configurar a resposta HTTP é utilizando a classe `ResponseEntity<T>`. Essa classe funciona como uma Fluent API que permite construir a resposta de forma clara, imutável e controlada. Com ela, conseguimos:

```java
@RestController
@RequestMapping("/countries")
public class CountryController {

    @GetMapping("/france")
    public ResponseEntity<CountryDTO> getFrance() {
        CountryDTO country = new CountryDTO("France", 67);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)Accepted
                .header("Continent", "Europe")
                .header("Capital", "Paris")
                .header("Cuisine", "Cheese and wine")
                .body(country);
    }
}
```

* Definir explicitamente o status HTTP;

* Adicionar cabeçalhos HTTP personalizados;

* E claro, retornar um corpo com os dados.

## Tratando exceções em REST
É essencial considerar o que acontece quando um controlador lança uma exceção, pois muitas vezes, exceções não são apenas falhas técnicas, mas representam situações previstas no próprio domínio de negócio. Por exemplo, tentar processar um pagamento sem saldo suficiente, acessar um recurso que não existe ou enviar dados inválidos são cenários que precisam ser tratados de forma clara e consistente.

Uma das maneiras de se lidar com exceções em aplicações REST, é capturá-las em um bloco `catch` no controller, e retornando um `ResponseEntity`, como aprendemos anteriormente.

Por exemplo:
```java
@PostMapping("/payment")
    public ResponseEntity<?> makePayment() {
        try {
            PaymentDetails paymentDetails = paymentService.processPayment();

            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(paymentDetails);
        } catch(NotEnoughMoneyException e) {
            ErrorDetails errorDetails = new ErrorDetails(e.getMessage());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorDetails);
        }
    }
```
Embora funcional, essa abordagem apresenta algumas limitações:

* **Acoplamento de lógica**: o controlador precisa conhecer todas as exceções que podem ser lançadas, o que aumenta o acoplamento e torna o código menos coeso.

* **Código duplicado**: o padrão try-catch precisaria ser repetido em todos os métodos que podem lançar exceções.

Para resolver esses problemas e centralizar o tratamento de erros de forma mais elegante, o Spring oferece o mecanismo de @ExceptionHandler.

### ExceptionHandler
Em aplicações Spring, é comum que exceções sejam lançadas durante o processamento de uma requisição. O framework oferece o recurso @ExceptionHandler para capturar essas exceções de forma controlada, permitindo gerar uma resposta apropriada ao cliente.

Um método anotado com `@ExceptionHandler` é responsável por tratar uma ou mais exceções específicas, interceptando-as antes que cheguem ao cliente e produzindo uma resposta personalizada, geralmente em formato JSON (no caso de APIs REST).

Por exemplo:
```java
@ExceptionHandler(NotEnoughMoneyException.class)
public ResponseEntity<ErrorDetails> handleNotEnoughMoneyException(NotEnoughMoneyException e) {
    ErrorDetails error = new ErrorDetails(e.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
}
```

Esse método será chamado automaticamente sempre que a exceção `NotEnoughMoneyException` for lançada em qualquer método do mesmo controlador.

### Centralizando o tratamento de erros com @ControllerAdvice
Embora seja possível declarar métodos `@ExceptionHandler` dentro de cada controlador, essa abordagem pode gerar repetição e dificultar a manutenção à medida que o sistema cresce.

Para evitar isso, o Spring permite centralizar o tratamento de exceções em uma classe separada, usando as anotações `@ControllerAdvice` ou `@RestControllerAdvice`. Essas anotações tornam possível criar uma camada única de tratamento global, onde os métodos `@ExceptionHandler` são aplicados a todas as exceções lançadas nos controladores da aplicação.

Por exemplo:
```java
@RestControllerAdvice
public class ControllerAdvice {
    
    @ExceptionHandler
    public ResponseEntity<ErrorDetails> handleNotEnoughMoneyException(NotEnoughMoneyException e) {
        ErrorDetails errorDetails = new ErrorDetails(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorDetails);
    }
}
```

### Usando `@RequestBody`
Quando trabalhamos com APIs REST, é muito comum que o cliente envie dados no corpo da requisição HTTP — como um JSON contendo as informações de um novo recurso a ser criado (por exemplo, um novo produto ou usuário). Para mapear esses dados JSON de forma automática para objetos Java, o Spring oferece a anotação `@RequestBody`.

A anotação `@RequestBody` informa ao Spring que o conteúdo do corpo da requisição deve ser desserializado e mapeado para um objeto Java, geralmente um DTO. Em outras palavras, quando um cliente envia uma requisição POST ou PUT com um JSON no corpo, o `@RequestBody` transforma esse JSON em um objeto que pode ser manipulado diretamente no método do controlador.

Por exemplo:
```java
@PostMapping("/produtos")
public ResponseEntity<ProdutoDTO> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
    // produtoDTO já contém os dados enviados no corpo da requisição
    ProdutoDTO produtoSalvo = produtoService.salvar(produtoDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
}
```
---