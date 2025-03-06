package com.parking.apigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.apigateway.service.LprGrpcClient;

@RestController
public class ApiGatewayController {

    private final LprGrpcClient lprGrpcClient;
    
    @Autowired
    public ApiGatewayController(LprGrpcClient lprGrpcClient) {
        this.lprGrpcClient = lprGrpcClient;
    }

    @GetMapping("/api/lpr/process")
    public String processLicense(@RequestParam String imagePath) {
        return lprGrpcClient.processLpr(imagePath);
    }
}
