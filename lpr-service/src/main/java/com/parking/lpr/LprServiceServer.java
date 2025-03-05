package com.parking.lpr;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class LprServiceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8081)
                .addService(new LprService())  // ✅ Pass a correctly implemented gRPC service
                .build()
                .start();

        System.out.println("LPR Service running on port 8081...");
        server.awaitTermination();
    }
}
