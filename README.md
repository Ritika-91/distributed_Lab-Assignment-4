**Payment & Credit Card Microservices**

A microservices-based distributed system built for COMP41720 – Distributed Systems: Architectural Principles (Lab 4).

This project implements a simple payment-processing system using two independent microservices:

Payment Service – handles incoming payment requests

Credit Card Service – manages credit card accounts and authorizes charges

Communication between services is done using synchronous REST, and the system is deployed using Docker and Kubernetes.

** Architecture Overview**
+------------------------+       REST POST       +---------------------------+
|   Payment Service      | --------------------> |   Credit Card Service     |
|   (Java, Spring Boot)  |   /cards/authorize    |   (Java, Spring Boot)     |
+------------------------+                       +---------------------------+

**Workflow:**
1. Payment Service receives new payment request.
2. It calls Credit Card Service for authorization.
3. Credit Card Service checks credit limit and returns Approved/Declined.
4. Payment Service stores and returns the payment result.

**Technologies Used**

Java 17

Spring Boot 3

Docker

Kubernetes (Minikube / Docker Desktop)

Maven

** Project Structure**
/
├── payment-service/
│   ├── src/
│   ├── pom.xml
│   ├── Dockerfile
│   └── k8s/
│       ├── payment-deployment.yaml
│       └── payment-service.yaml
│
└── credit-card-service/
    ├── src/
    ├── pom.xml
    ├── Dockerfile
    └── k8s/
        ├── credit-card-deployment.yaml
        └── credit-card-service.yaml

**Setup & Installation**
1. Prerequisites

Ensure the following are installed:

Java 17+

Maven 3+

Docker

 Kubernetes via Docker Desktop

kubectl

 Build & Run Locally
Build both services
cd payment-service
mvn clean package

cd ../credit-card-service
mvn clean package

Run locally (without Docker)
Start Credit Card Service:
cd credit-card-service
mvn spring-boot:run

Start Payment Service:
cd payment-service
mvn spring-boot:run

 Docker Build Instructions

Build Docker images:

Payment Service:
cd payment-service
docker build -t payment-service .

Credit Card Service:
cd credit-card-service
docker build -t credit-card-service .

Kubernetes Deployment

Start Minikube (if using Minikube):

minikube start


Apply Kubernetes manifests:

kubectl apply -f credit-card-service/k8s/
kubectl apply -f payment-service/k8s/


Verify pods are running:

kubectl get pods


Expose Payment Service:

kubectl port-forward svc/payment-service 8080:8080

Testing the System
1. Register a fake credit card:
curl -X POST http://localhost:8081/cards \
     -H "Content-Type: application/json" \
     -d '{"cardNumber":"1234","holderName":"Alice","creditLimit":1000}'

2. Make a payment:
curl -X POST http://localhost:8080/payments \
     -H "Content-Type: application/json" \
     -d '{"cardNumber":"1234","amount":200,"currency":"EUR"}'


Expected Response:

{
  "id": 1,
  "cardNumber": "1234",
  "amount": 200,
  "currency": "EUR",
  "status": "AUTHORIZED"
}

