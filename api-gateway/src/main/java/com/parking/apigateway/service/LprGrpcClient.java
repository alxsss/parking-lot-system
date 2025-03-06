package com.parking.apigateway.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.parking.grpc.LprRequest;
import com.parking.grpc.LprResponse;
import com.parking.grpc.LprServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class LprGrpcClient {   
    
    //manual initialization
    private LprServiceGrpc.LprServiceBlockingStub lprStub;

    public LprGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("lpr-service", 8081)
                                                      .usePlaintext()
                                                      .build();
        this.lprStub = LprServiceGrpc.newBlockingStub(channel);
    }
    

    //@GrpcClient("lpr-service")
    //private LprServiceGrpc.LprServiceBlockingStub lprStub;

    @PostConstruct
    private void checkGrpcClient() {
        if (lprStub == null) {
            throw new IllegalStateException("❌ gRPC stub is not initialized! Check gRPC client configuration.");
        }
        System.out.println("✅ gRPC client initialized successfully.");
    }

    public String processLpr(String imagePath) {
        if (lprStub == null) {
            throw new IllegalStateException("❌ gRPC client is null! Ensure gRPC is properly configured.");
        }

        LprRequest request = LprRequest.newBuilder()
                                       .setImagePath(imagePath)
                                       .build();
        LprResponse response = lprStub.processLpr(request);
        return response.getLicensePlate();
    }
}



