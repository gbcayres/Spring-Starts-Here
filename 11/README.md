# Capítulo 11

* Consumindo APIs REST
* Spring Cloud OpenFeign
* RestTemplate
* Webclient

## Consumindo APIs
No mundo real, sistemas raramente funcionam de forma isolada. Eles trocam dados o tempo todo: um serviço de e-commerce pode consultar uma API dos Correios para calcular o frete, ou acionar um sistema de pagamentos para finalizar uma compra.

Até aqui, focamos no papel de quem expõe uma API — recebendo, interpretando e respondendo requisições HTTP que representam intenções de uso sobre os dados do sistema. Neste capítulo, vamos olhar para o outro lado da comunicação: o de quem consome uma API.

## Spring Cloud OpenFeign
O OpenFeign é um cliente HTTP declarativo. Isso significa que saímos de um modelo imperativo — onde dizemos como a chamada HTTP deve acontecer (montando a URL, configurando headers, serializando/desserializando) — para um modelo declarativo, no qual apenas expressamos a intenção da chamada, e o framework cuida do resto em tempo de execução.

Com o OpenFeign, espelhamos os métodos de um endpoint REST de outro serviço a partir de uma interface Java, utilizando a sintaxe familiar das anotações do Spring MVC (```@GetMapping```, ```@PostMapping```, etc.), que definem a URL, o método HTTP, os parâmetros, headers, entre outros. Tais interfaces são chamadas de **Feign Clients**.

### Habilitando o Feign
Antes de tudo, é preciso informar ao Spring que ele deve escanear e gerenciar essas interfaces. Isso é feito adicionando a anotação ```@EnableFeignClients``` em uma classe de configuração principal:

```java
@SpringBootApplication
@EnableFeignClients(basePackages = "com.example.base")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

```
### Definindo o cliente de pagamento
Vamos ver um exemplo de cliente para integração com um serviço de pagamentos externo:

```java
@FeignClient(name = "payment", url = "${api.payment.url}")
public interface PaymentClient {

    @PostMapping("/transactions")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);

    // outros métodos...
}
```

### Consumindo com Feign na prática
Após declarar a interface, podemos injetá-la em nossos serviços normalmente, como qualquer outro bean gerenciado pelo Spring:
```java
@Service
public class CheckoutService {

    private final PaymentClient paymentClient;

    public CheckoutService(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    public PaymentResponse finalizarCompra(Cart cart) {
        PaymentRequest request = new PaymentRequest(
            cart.getTotalAmount(),
            cart.getCustomer().getCardToken()
        );

        PaymentResponse response = paymentClient.processPayment(request);

        if (!response.isSuccess()) {
            throw new PaymentFailedException("Pagamento recusado: " + response.getReason());
        }

        return response;
    }
}
```
Nesse exemplo, o ```CheckoutService``` envia os dados do carrinho para a API de pagamentos. A chamada HTTP é feita como se estivéssemos apenas invocando um método Java.

Isso é possível graças ao uso de proxies dinâmicos — um conceito discutido no capítulo 6 sobre **AOP   (Programação Orientada a Aspectos)**. Embora o OpenFeign utilize um mecanismo semelhante, ele não depende diretamente do Spring AOP, e sim de sua própria infraestrutura.

O autor destaca a praticidade dessa abordagem e recomenda o uso do OpenFeign para aplicações não-reativas. 

## Rest Template