# Apêndice C: Uma introdução rápida ao HTTP

## C.1 - O que é HTTP?
HTTP (Hypertext Transfer Protocol) é um protocolo de comunicação baseado no modelo de requisição-resposta, utilizado principalmente na troca de dados entre clientes e servidores em aplicações web. Ele é um protocolo textual e sem estado (stateless), o que significa que cada requisição é independente — o servidor não mantém informações sobre interações anteriores.

## C.2 - Requisições HTTP
Na maior parte das vezes, usaremos o Spring para desenvolver aplicações web. Seja implementando o lado cliente ou servidor, precisamos entender requisições HTTP.

Ao lidar com requisições HTTP, precisamos entender sua estrutura, composta por alguns elementos principais:
### **URI (Uniform Resource Identifier)**
É o identificador do recurso solicitado pelo cliente. Na prática, é a parte do endereço que define *qual* recurso está sendo acessado. A URI é composta por:

`http://<server_location>:<application_port>/<resource_path>`

* `<server_location>`: o endereço da máquina que está rodando o servidor — por exemplo, localhost ou *api.meusite.com*.

* `<application_port>`: a porta em que a aplicação está sendo executada — como 8080, 443 etc.

* `<resource_path>`: o caminho que representa o recurso desejado — como `/produtos`, `/usuarios/42`, etc.

É importante entender que toda URL é uma URI, mas uma URI pode incluir mais que uma URL. 
Podemos dizer que uma URL identifica o servidor e a aplicação, e quando adicionamos o caminho para um recurso específico, termos uma URI.

### Método HTTP
É o verbo que expressa qual ação o cliente deseja realizar sobre o recurso identificado pela URI. Os mais comuns são:

* **GET**: Usado para buscar dados. Não deve causar efeitos colaterais no servidor — como alterar ou deletar dados.

* **POST**: Usado para criar um novo recurso no servidor. Os dados geralmente são enviados no corpo da requisição.

* **PUT**: Usado para atualizar um recurso existente, substituindo sua representação atual.

* **DELETE**: Usado para remover um recurso do servidor.

Outros métodos menos comuns, mas importantes:

* **PATCH**: Atualiza apenas partes de um recurso — diferente do PUT, que substitui tudo.

* **OPTIONS**: Solicita ao servidor quais métodos e opções estão disponíveis para um determinado recurso.

O protocolo HTTP não impede o uso incorreto dos verbos. Por exemplo, é tecnicamente possível deletar algo com um GET, mas isso vai contra os princípios RESTful e prejudica a previsibilidade e segurança da aplicação.

### Parâmetros (opcional)
Parâmetros são pequenos dados enviados na requisição, geralmente para filtrar, buscar ou modificar o comportamento da resposta. Eles podem aparecer de duas formas:

* Query Parameters:  enviados na URL, após o ?, no formato chave=valor, separados por &. 

  Por exemplo:

  `GET /produtos?categoria=eletronicos&ordem=preco`

* Path Variables: fazem parte do caminho da URI e representam identificadores de recursos. 

  Por exemplo:

  `GET http://localhost:8080/usuarios/42`

### Cabeçalhos
Os cabeçalhos carregam metadados da requisição ou resposta. São usados para transmitir informações adicionais sobre o conteúdo, autenticação, controle de cache, idioma, entre outros.

Exemplos comuns de cabeçalhos:

* **Content-Type**: indica o tipo de dado enviado no corpo — como application/json.

* **Authorization**: transporta tokens ou credenciais de autenticação.

* **Accept**: indica os formatos que o cliente aceita receber como resposta.

### Corpo (opcional)
O corpo é usado principalmente em requisições que enviam dados ao servidor, como POST, PUT ou PATCH. É nele que ficam os dados de um novo recurso ou as informações que serão atualizadas. O corpo geralmente é estruturado em formatos como:

* application/json — o mais comum em APIs REST.

* application/x-www-form-urlencoded — envio de formulários HTML.

* multipart/form-data — usado para upload de arquivos.
### Exemplo de Requisição HTTP (POST)

```http
POST /api/produtos HTTP/1.1
Host: loja.gb.com
Content-Type: application/json
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
Accept: application/json

{
  "nome": "Notebook Dell Inspiron",
  "preco": 3999.90,
  "quantidadeEmEstoque": 15,
  "categoria": "informática"
}
```

## Respostas HTTP
Uma vez que sabemos oque vamos receber em uma requisição HTTP, precisamos entender oque mandar de volta. A estrutura de uma resposta HTTP é formada pelos seguintes componentes:

### Status
Um número inteiro entre 100 e 599 que representa o resultado da requisição. É o único componente obrigatório em toda resposta HTTP.

As categorias mais utilizadas são:

* **2xx – Sucesso**

  O servidor processou a requisição com êxito. Os mais comuns:

    * **200 – OK**: Tudo ocorreu normalmente. O servidor entendeu e executou a solicitação com sucesso.

    * **201 – Created**: Utilizado após uma criação de recurso, como um POST. Informa que o recurso foi criado com sucesso.

    * **204 – No Content** : A requisição foi bem-sucedida, mas o servidor não retornará nenhum conteúdo no corpo da resposta.

* 4xx – Erro do Cliente

  Indica que houve algum problema na requisição enviada pelo cliente:

  * **400 – Bad Request**: A requisição estava malformada ou com dados inválidos.

  * **401 – Unauthorized**: Acesso negado por falta de autenticação.

  * **403 – Forbidden**: A autenticação ocorreu, mas o cliente não tem permissão para acessar o recurso.

  * **404 – Not Found**: O recurso solicitado não foi encontrado no servidor.

* 5xx – Erro do Servidor

  Ocorre quando o servidor falha ao processar uma requisição válida:

  * **500 – Internal Server Error**: Um erro genérico indicando falha inesperada no servidor.

  * **502 – Bad Gateway**: O servidor recebeu uma resposta inválida de outro serviço (ex: downstream).

  * **503 – Service Unavailable**: O servidor está temporariamente indisponível (ex: manutenção, sobrecarga).

  ## Sessão HTTP
 As requisições HTTP são stateless — ou seja, independentes umas das outras. O protocolo não "lembra" de requisições anteriores: cada requisição é tratada de forma isolada, sem contexto. Isso funciona bem para muitos casos, mas em aplicações que precisam manter estado entre requisições, como um carrinho de compras, precisamos de uma forma de relacionar múltiplas interações de um mesmo usuário.

 ### Oque é uma sessão HTTP?

 Uma sessão HTTP é uma forma de manter dados relacionados a um usuário ao longo de múltiplas requisições. Isso é feito da seguinte forma:

1. Quando o usuário faz a primeira requisição, o servidor gera um ID de sessão único.

2. Esse ID é associado a uma área de memória no servidor, onde podem ser armazenadas informações como carrinho de compras, dados de login, preferências, etc.

3. O ID de sessão é então enviado de volta ao cliente — geralmente como um cookie (Set-Cookie: JSESSIONID=xyz123).

4. Em requisições futuras, o cliente envia esse ID de volta nos cabeçalhos (Cookie: JSESSIONID=xyz123), permitindo que o servidor recupere o estado anterior e continue a interação como uma "conversa contínua".

### Tempo de Vida da Sessão
As sessões não duram para sempre. Elas possuem um timeout configurável, definido tanto no servidor (ex: Tomcat) quanto na aplicação.

* Em geral, uma sessão expira após 30 a 60 minutos de inatividade (ou seja, sem nenhuma requisição do cliente).

* Após o tempo configurado, o servidor descarta a sessão e libera a memória associada a ela.

* Se o cliente enviar uma nova requisição após o término da sessão, um novo ID de sessão será gerado, iniciando uma nova sessão.

Essa estratégia evita o acúmulo de dados desnecessários em memória, o que poderia levar à sobrecarga do servidor.

### Exemplo de Cabeçalhos com Sessão

* **Resposta do servidor ao criar sessão**

```http
HTTP/1.1 200 OK
Set-Cookie: JSESSIONID=abc123xyz; Path=/; HttpOnly
Content-Type: text/html
```

* **Requisição subsequente do cliente com a sessão**

```http
GET /carrinho HTTP/1.1
Host: loja.gb.com
Cookie: JSESSIONID=abc123xyz
```

Sessões HTTP são uma forma de contornar a natureza stateless do protocolo, mantendo dados do usuário em memória no lado do servidor e usando um ID de sessão como chave para acessar esses dados em cada requisição subsequente.

---