# Capítulo 13

* Transações

## Oque são transações?

Transações são um conjunto de operações mutáveis — ou seja, que modificam dados — que devem ser executadas de forma atômica e indivisível. Isso significa que ou todas as operações acontecem corretamente, ou nenhuma delas acontece.

O objetivo principal das transações é proteger a consistência dos dados, evitando que erros intermediários deixem o sistema em um estado incoerente. 

Um exemplo clássico é o cenário de uma transferência bancária. Nesse caso, precisamos executar o seguinte conjunto de operações: retirar o dinheiro da conta remetente; adicionar o valor à conta destinatária. Essas duas ações precisam acontecer dentro de uma mesma transação, pois não faria sentido retirar o dinheiro da conta A e não adicioná-lo na conta B.

### Commit
O commit ocorre quando todas as operações da transação são concluídas com sucesso. Nesse caso, as mudanças feitas são persistidas definitivamente no banco de dados.

### Rollback
Se alguma operação falhar durante a execução, a transação é desfeita e o banco de dados retorna ao estado original, como se nada tivesse acontecido. Esse processo é chamado de rollback.

## Usando transações no Spring
No Spring, o gerenciamento de transações é implementado com base no paradigma de Programação Orientada a Aspectos (AOP) — assunto estudado no capítulo 6.

Relembrando rapidamente: a AOP permite aplicar lógicas transversais, como logging, segurança ou transações, sem misturá-las com a lógica de negócio. Isso é feito por meio de proxies que interceptam chamadas de métodos e adicionam comportamento antes ou depois da execução.

No caso de transações, usamos a anotação @Transactional para declarar que um método deve ser executado de forma atômica.

1. Em vez de chamar diretamente o método anotado, o cliente (por exemplo, o controller) interage com um proxy gerado pelo Spring.

2. Esse proxy contém a lógica do aspecto transacional:
    * Antes de executar o método o Spring abre uma nova transação.

    * Se o método for concluído sem falhas o proxy executa o commit, persistindo as alterações.

    *  Se o método lançar uma exceção o proxy executa o rollback, desfazendo as alterações.


## Propagação da exceção
É importante destacar que para que o proxy consiga identificar uma falha e disparar o rollback, a exceção precisa chegar até ele. Isso significa que:

* Se o método lançar uma RuntimeException, o proxy verá a falha e fará rollback automaticamente (comportamento padrão).

* Se o método lançar uma CheckedException, o rollback não acontece por padrão — a não ser que configuremos explicitamente com ```@Transactional(rollbackFor = ...)```.`

* Se o método capturar a exceção internamente e não a relançar, o proxy nunca saberá que houve falha. Nesse caso, ele assumirá que tudo ocorreu bem e fará o commit — mesmo que a lógica de negócio tenha dado errado.

Em outras palavras, quando tratamos uma exceção dentro de um método anotado com ```@Transactional```, precisamos estar cientes de que isso desabilita o rollback automático.