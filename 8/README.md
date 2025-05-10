# Capítulo 8

* Implementando aplicações web dinâmicas

## 💡 O que são Aplicações Web Dinâmicas?

Aplicações web dinâmicas são aquelas cujo conteúdo pode variar conforme o contexto, mesmo que a estrutura básica da página permaneça a mesma.

Por exemplo, pense em um carrinho de compras: não faria sentido que ele exibisse sempre os mesmos itens. O que aparece ali depende diretamente do que o usuário adicionou previamente. Assim, cada requisição feita pelo usuário gera uma resposta personalizada do servidor.

O fluxo básico é:

1. O navegador envia uma requisição HTTP ao servidor (ex.: ao clicar em "Ver Carrinho").
2. A aplicação recebe essa requisição, extrai os dados necessários (como produtos adicionados), aplica as regras de negócio (descontos, cálculo de preços etc.).
3. Em seguida, gera uma resposta específica, que será renderizada pelo navegador.

Esse processamento intermediário — onde os dados são analisados e tratados antes da resposta — é o que caracteriza uma aplicação dinâmica. Em vez de servir páginas estáticas iguais para todos os usuários, o servidor gera conteúdo sob demanda, considerando o contexto e as ações do usuário.

---

## Implementando Aplicações Dinâmicas

Para criar aplicações dinâmicas, é essencial que o cliente consiga enviar dados ao servidor. Porém, antes disso, precisamos entender como o controller passa esses dados para a view.

### Passando Dados para a View

O fluxo tradicional de uma requisição web é:

1. O cliente envia uma requisição HTTP ao servidor.
2. O Dispatcher Servlet usa o Handler Mapping para definir qual ação e controller deve executar.
3. O Dispatcher Servlet executa essa ação.
4. O Controller retorna o nome da view a ser renderizada na resposta HTTP.
5. A resposta é então enviada ao cliente.

Para tornar esse fluxo dinâmico, precisamos alterar o passo 4. Além de informar o nome da view, o controller precisa também enviar dados à view. Isso é feito adicionando um parâmetro do tipo `Model` no método do controller, permitindo que o controller insira dados com o método `model.addAttribute(key, value)`. Esses dados podem então ser utilizados na view por meio de uma template engine como o Thymeleaf.

### Enviando Dados do Cliente para o Servidor com HTTP

Agora que entendemos como utilizar dados na view, precisamos compreender como esses dados chegam inicialmente ao servidor.

Existem duas formas principais de enviar dados via requisições HTTP:

* **Parâmetros de Requisição:**

  * Enviam dados opcionais no formato `chave=valor` diretamente na URL, frequentemente utilizados para filtros ou buscas.
  * Exemplo: `http://localhost:8080/products?category=food&type=fruits`
  * No método do controller, esses valores são obtidos utilizando a anotação `@RequestParam`.

* **Variáveis de Caminho:**

  * Utilizadas para enviar dados obrigatórios diretamente na URL, melhorando legibilidade e otimização para SEO.
  * Exemplo: `http://localhost:8080/products/categories/eletronics`
  * Esses valores são capturados no método do controller com a anotação `@PathVariable`.

---

## Métodos HTTP: GET e POST

Por padrão, as requisições usam o método HTTP GET, assim como a anotação `@RequestMapping`. Podemos especificar explicitamente o método HTTP desejado usando os argumentos `path` e `method` dentro da anotação `@RequestMapping`. Por exemplo:

```java
@RequestMapping(path = "/products", method = RequestMethod.GET)
```

Alternativamente, é possível utilizar anotações mais específicas e convenientes, como `@GetMapping` ou `@PostMapping`, que tornam o código mais claro e conciso:

```java
@GetMapping("/products")
public String getProducts(Model model) {
    // Implementação
}
```

Essas práticas tornam o código mais intuitivo, organizado e alinhado às boas práticas de desenvolvimento web.
