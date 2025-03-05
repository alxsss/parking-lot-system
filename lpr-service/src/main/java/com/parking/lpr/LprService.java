package com.parking.lpr;

import io.grpc.stub.StreamObserver;
import com.parking.grpc.LprServiceGrpc;
import com.parking.grpc.LprRequest;
import com.parking.grpc.LprResponse;

public class LprService extends LprServiceGrpc.LprServiceImplBase {  // ✅ EXTENDS gRPC CLASS

    @Override
    public void checkLpr(LprRequest request, StreamObserver<LprResponse> responseObserver) {
        boolean isRecognized = true; // Replace with actual logic

        LprResponse response = LprResponse.newBuilder()
                .setRecognized(isRecognized)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
