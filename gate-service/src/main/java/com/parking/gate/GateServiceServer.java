package com.parking.gate;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GateServiceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8082)
                .addService(new GateService())  // Register gRPC implementation
                .build()
                .start();

        System.out.println("Gate Service running on port 8082...");
        server.awaitTermination();
    }
}
