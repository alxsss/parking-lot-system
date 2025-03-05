package com.parking.payment;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class PaymentServiceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new PaymentService())  // Use existing gRPC service implementation
                .build()
                .start();

        System.out.println("Payment Service running on port 9090...");
        server.awaitTermination();
    }
}
