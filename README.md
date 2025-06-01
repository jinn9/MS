# Simple Order System API

This is a pet project aim to practice building a typical microservice application in Java

## Technologies

- Java 21
- Spring Boot
- Spring Cloud
- Spring Data JPA, H2
- Spring Security, Keycloak
- Kafka
- Grafana, Loki, Alloy, Prometheus, Tempo, OpenTelemetry
- Docker

## Local development architecture
<img width="1440" alt="Screenshot 2025-06-01 at 6 56 11 PM" src="https://github.com/user-attachments/assets/9e2657a7-6467-4298-b595-976eb5620984" />

## Entity Relationship Diagram
<img width="1430" alt="Screenshot 2025-06-01 at 6 28 24 PM" src="https://github.com/user-attachments/assets/c6803955-304b-432b-b12d-693ba7bc2e10" />

## API Specification

[Insert here]

## Project Details

#### Security

#### Resilience

#### Monitoring

#### Event Streaming

## Getting started with Docker Compose

1. Get the latest source code
2. Open terminal of your choice, go to the project directory, then cd into `docker-compose`
3. Run `docker compose up`, wait for all the containers up and running
4. Register with the auth server
    1. Go to `localhost:7080`, login with admin/admin
    2. Go to `Clients` -> `Create client`
    3. Add Client ID of your choice, click next
    4. Set `Client authentication` to on, deselect `Stardard flow`, select `Service accounts roles`, then click next
    5. Click 'Save'
    6. Go to `Realm role` in the menu on the left, create a role with role name `MEMBER`, `ORDER`, `PRODUCT`
5. You are now ready to make your first call to the api
