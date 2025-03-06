Parking Lot System - API Gateway

Overview

The Parking Lot System is a microservices-based application that manages parking lot operations, including License Plate Recognition (LPR) and Payment Verification. This repository contains the API Gateway, which serves as the entry point for client requests and routes them to the appropriate backend services using Spring Cloud Gateway and gRPC.

Features

License Plate Recognition (LPR) Service:

Accepts an image path as input and returns the detected license plate number.

Integrates with the LprService via gRPC.

Payment Verification Service:

Checks if a vehicle's parking fee has been paid based on the license plate.

Integrates with the PaymentService via gRPC.

Spring Cloud Gateway:

Routes HTTP requests to the corresponding gRPC services.

Provides error handling and logging.

Architecture

+---------------+          +----------------+         +----------------+
|  Client (UI)  | ----->  |  API Gateway   | -----> | gRPC Services  |
+---------------+          +----------------+         +----------------+
                                            |          |              |
                                            |          |              |
                                       LPR Service    Payment Service

Technologies Used

Java 17

Spring Boot 3.1.2

Spring Cloud Gateway

gRPC

Docker

Maven

Prerequisites

Ensure you have the following installed:

JDK 17

Maven

Docker

Installation & Setup

1. Clone the Repository

git clone https://github.com/alxsss/parking-lot-system.git
cd parking-lot-system/api-gateway

2. Build the Project

mvn clean install

3. Run with Docker Compose

Ensure Docker is running and execute:

docker-compose up --build

This will start the API Gateway, LPR Service, and Payment Service in separate containers.

Usage

1. License Plate Recognition

Send a GET request to recognize a vehicle’s license plate:

curl -X GET "http://localhost:8080/api/lpr/process?imagePath=test.jpg"

Response:

{
  "licensePlate": "ABC123",
  "isPaid": false
}

2. Payment Verification

Check if a vehicle has paid for parking:

curl -X GET "http://localhost:8080/api/payment/check?licensePlate=ABC123"

Response:

{
  "isPaid": true
}

Configuration

Modify application.yml to change service endpoints, gRPC configurations, or logging levels.

Troubleshooting

Common Errors & Solutions

Service Unavailable (gRPC UNAVAILABLE: io exception)

Ensure all microservices are running (docker ps).

Check gRPC service ports in docker-compose.yml.

gRPC Stub Not Initialized

Ensure the @GrpcClient annotation is correctly configured.

Check application.yml for gRPC server configurations.

Contributing

Feel free to submit issues or pull requests. Contributions are welcome!

License

This project is licensed under the MIT License - see the LICENSE file for details.