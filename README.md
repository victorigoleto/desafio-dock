# Desafio Dock - Sistema de Gestão de Contas (SystemAccount)

## 🚀Tecnologias Utilizadas
<p>•Java 17</p>
<p>•Spring Boot</p>
<p>•Spring Data JPA</p>
<p>•PostgreSQL</p>
<p>•Docker</p>
<p>•Flyway Migration</p>
<p>•Rancher Desktop</p>

## 💻 Pré-requisitos para executar o projeto localmente

Antes de começar, verifique se você atendeu aos seguintes requisitos:
<!---Estes são apenas requisitos de exemplo. Adicionar, duplicar ou remover conforme necessário--->
* Você instalou a versão mais recente de `<JDK Java 17 / IDE Eclipse ou Netbeans / DBeaver / Git e GitHub / Postman / WSL instalado>`
* Você tem uma máquina `<Windows / Mac / Linux>`.

## ☕ Usando o SystemAccount

Para usar SystemAccount, clone o projeto:

```
Em qualquer lugar da sua máquina
```
<img src="https://i.imgur.com/EhfZP62.png" alt="Clone">


```
Agora abra sua IDE de escolha e importe o projeto. As dependencias serão baixadas. Ao termino, abra o terminal e rode o seguinte comando para subir o cotainer: docker compose up -d
```

<img src="https://i.imgur.com/qtziBWv.png" alt="docker">

```
Foi usado o Rancher Desktop para administrar uma infraestrutura de docker. Caso tenha o Docker original instalado, nao há necessidade de utilizar o mesmo
```
<br>

```
Feito isso, rode a aplicação main SystemAccountApplication, que será gerado automaticamente as tabelas do banco de dados. Note que a tabela user-account já vem com um usuário cadastrado, devido ao Flyway Migration
```
<img src="https://i.imgur.com/cA1LDBm.png" alt="Rodando a aplicação">

```
Agora abra o POSTMAN ou Insomnia e faça as requisiçoes.
```

[⬆ Voltar ao topo](#nome-do-projeto)<br>

### Desafio Dock Tech de Seleção 
Olá, queremos convidá-lo a participar de nosso desafio de seleção.  Pronto para participar? Seu trabalho será visto por nosso time e você receberá ao final um feedback sobre o que achamos do seu trabalho. Não é legal?

### Sobre a oportunidade 
A vaga é para software engineer, temos vagas com diversos níveis de senioridade e para cada um deles utilizaremos critérios específicos considerando esse aspecto, combinado? 
Se você for aprovado nesta etapa, será convidado para uma entrevista final com nosso time de especialistas.

### Desafio Técnico
  Nós trabalhamos com meios de pagamento e nada melhor que um bom sistema para gestão de contas:
  
  - Pré-requisitos:
    ```
    * Desenvolver os recursos em API Rest que realizam operações bancárias com a entidade conta a seguir:
    ```
    | Contas | Tipo |
    |-|-|
    | idConta | Numérico |
    | idPessoa | Numérico |
    | saldo | Monetário |
    | limiteSaqueDiario | Monetário |
    | flagAtivo | Condicional |
    | tipoConta | Numérido |
    | dataCriacao | Data |

    ```
    * Tabela de transações realizadas na conta
    ```
    | Transacoes | Tipo |
    |-|-|
    | idTransacao | Numérico |
    | idConta | Numérico |
    | valor | Monetário |
    | dataTransacao | Data |

    ```
    * P.S.: Não é necessário realizar operações com a tabela pessoa, mas é necessária a criação da tabela para mapeamento da relação com a conta e enviar script de criação de pelo menos uma pessoa.
    ```

    | Pessoas | Tipo |
    |-|-|
    | idPessoa | Numérico |
    | nome | Texto |
    | cpf | Texto |
    | dataNascimento | Data |    

  - O que esperamos como escopo mínimo:
    ```
    * Implementar path que realiza a criação de uma conta;
    * Implementar path que realiza operação de depósito em uma conta;
    * Implementar path que realiza operação de consulta de saldo em determinada conta;
    * Implementar path que realiza operação de saque em uma conta;
    * Implementar path que realiza o bloqueio de uma conta;
    * Implementar path que recupera o extrato de transações de uma conta;
    ```
  - O que será diferencial:
    ```
    * Implementar extrato por período;
    * Elaborar manual de execução;
    * Elaborar documentação javadoc;
    * Elaborar testes;
    * Prazo de entrega;
    ```
    
  - O que vamos avaliar:
    ```
    * Seu código; 
    * Script de banco;
    * Organização;
    * Boas práticas;
    * Diferenciais;    
    ```


### Instruções
      1. Faça o fork do desafio;
      2. Desenvolva. Você terá até 3 (três) dias a partir da data do envio do desafio; 
      3. Envie um e-mail para arthur.azevedo@dock.tech notificando a finalização do desafio e o link do repositório para validação.
