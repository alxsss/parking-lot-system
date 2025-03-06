package com.parking.lpr;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class LprServiceServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8081)
                .addService(new LprService()) 
                .build()
                .start();

        System.out.println("LPR Service running on port 8081...");
        server.awaitTermination();
    }
}
