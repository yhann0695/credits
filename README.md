# Credits

O projeto Credits é simples, tem apenas a finalidade de demonstrar rapidamente uma arquitetura de microserviços

## Pré-requisitos

- Java 11
- Docker

## Breve explicação
- Eureka-server: Tem o papel de permitir que os serviços "descubram" facilmente a rota dos serviços que precisam acessar.

- msclientes: é um serviço REST focado no cliente.

- mscard: é um serviço REST focado em cartões.

- mscreditappraiser: é um serviço REST focado em aprovar crédito ao cliente.

- mscloudgateway: Tem o papel de ser um intermediário nas requisições para os serviços.

## Usando
RabbitMQ
```
Com o docker, crie um container do rabbitMQ com o seguinte comando: docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
em seguida, crie uma nova fila com o nome: emissao-cartoes
```

Inicialização dos projetos:
```
Recomendo que inicialize na seguinte ordem: 
1 - eureka-server
2 - msclientes
3 - mscard
4 - mscreditappraiser
5 - mscloudgateway
```

msclientes:

```
Nesse serviço é possível fazer a criação de um novo cliente.
POST: http://localhost:8080/customers

{
	"cpf": "134.432.124-32",
	"name": "Test",
	"age": 27
}

E também é possível fazer a consulta do cliente.
GET: http://localhost:8080/customers

retorno previsto:

{
	"id": 1,
	"cpf": "134.432.124-32",
	"name": "Test",
	"age": 27
}

```

mscard:

```
Nesse serviço é possível fazer a criação de um novo cartão.
POST: http://localhost:8080/cards

{
  "name": "Card",
  "flag": "VISA",
  "income": 5000,
  "basicLimit": 8000
}

E também é possível fazer a consulta do cartão.
GET: http://localhost:8080/cards?income=5000

retorno previsto:

[
	{
		"id": 1,
		"name": "Card",
		"flag": "VISA",
		"income": 5000.00,
		"basicLimit": 8000.00
	}
]

```
mscreditappraiser:

```
Nesse serviço é possível fazer a avaliação do cliente.
POST: http://localhost:8080/credit-appraiser

{
	"cpf": "134.432.124-32",
	"income": 5000
}

retorno previsto: 

{
	"cards": [
		{
			"card": "Card",
			"flag": "VISA",
			"approvedLimit": 21600.000
		}
	]
}

E também é possível fazer solicitação de emissão de cartão.
POST: http://localhost:8080/credit-appraiser/request-card

{
   "cardId": 1,
   "cpf": "134.432.124-32",
   "address": "Street X",
   "limitReleased": 21600.000
}

retorno previsto:

{
	"protocol": "f2d743f6-4c30-44a0-90a6-9cf7e0ae1095"
} 

em seguida, é possível consultar os situação do cliente.
GET: http://localhost:8080/credit-appraiser/customer-situation?cpf=134.432.124-32

{
	"customerData": {
		"id": 1,
		"name": "Test",
		"age": 27
	},
	"cards": [
		{
			"name": "Card",
			"flag": "VISA",
			"limitReleased": 21600.00
		}
	]
}

```

## Construído com

 - Spring Boot
 - Spring Data JPA (com Hibernate)
 - Cloud
 - Eureka
 - Gateway
 - Maven
 - H2
 - Docker
 - RabbitMQ 
