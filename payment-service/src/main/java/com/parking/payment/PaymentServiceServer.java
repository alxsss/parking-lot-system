package com.parking.payment;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class PaymentServiceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9090)
                .addService(new PaymentService())  
                .addService(ProtoReflectionService.newInstance()) 
                .build()
                .start();

        System.out.println("Payment Service running on port 9090...");
        server.awaitTermination();
    }
}
