# Simple Order System API

This is a pet project aim to practice building a microservice application in Java

## Technologies

- Java 21
- Spring Boot
- Spring Cloud
- Spring Data JPA, H2
- Spring Security, Keycloak
- Kafka
- Grafana, Loki, Alloy, Prometheus, Tempo, OpenTelemetry
- Docker

## Getting started with Docker Compose

1. Get the latest source code
2. Open terminal of your choice, go to the project directory, then cd into `docker-compose`
3. Run `docker compose up`, wait for all the containers up and running
4. Register with the auth server
    1. Go to `localhost:7080`, login with admin/admin
    2. Go to `Clients` in the menu on the left, then click `Create client`
    3. Add Client ID of your choice, click next
    4. Set `Client authentication` to on, deselect `Stardard flow`, select `Service accounts roles`, then click next
    5. Click `Save`
    6. Go to `Realm role` in the menu on the left, create a role with role name `ORDER`, `MEMBER`, `PRODUCT`
    7. Go to `Clients`, select your client from the list, select `Service accounts role`, click `Assign role`, switch `Filter by clients` to `Filter by realm roles` in the top corner, then assign `ORDER`, `MEMBER`, `PRODUCT`
5. You are now ready to make your first api call

## Local development architecture
<img width="1164" alt="Screenshot 2025-06-01 at 7 10 49 PM" src="https://github.com/user-attachments/assets/6028ae83-fe7e-427f-b998-b769cec45041" />

## Entity Relationship Diagram
<img width="1327" alt="Screenshot 2025-06-01 at 7 14 08 PM" src="https://github.com/user-attachments/assets/dcebb432-cc1b-482a-895d-0c02bcc3405a" />

## API Specification
https://documenter.getpostman.com/view/15918667/2sB2qgey9F

## Project Details

#### Security
Secure APIs using OAuth 2.0, implementing token-based authentication and role-based access control for sensitive operations

#### Resilience
Enhance system stability and resilience by applying resiliency patterns such as the circuit breaker, retry, and rate limiter

#### Event Streaming
Use Kafka as a message broker to establish asynchronous, event-driven communication between Order and Delivery service

#### Oberservability and Monitoring
Conveniently observe and mointor all the APIs using a dashboard

##### Logging
<img width="2560" alt="logging" src="https://github.com/user-attachments/assets/281f4ba4-90be-46fd-a1bc-4b870c7ac138" />

##### Metric
<img width="2558" alt="metric" src="https://github.com/user-attachments/assets/4c512caf-6539-439e-9efd-e259065d5cff" />
<img width="2560" alt="metric2" src="https://github.com/user-attachments/assets/8828f1c7-c1a5-4639-8f84-b7784964b657" />

##### Ditributed Tracing
<img width="2557" alt="distributed_tracing" src="https://github.com/user-attachments/assets/b7a9c2e2-80ff-45a6-955e-e5d17235ca33" />
