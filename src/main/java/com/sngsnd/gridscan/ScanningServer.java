package com.sngsnd.gridscan;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ScanningServer {

    public static void main(String[] args) throws InterruptedException, IOException {
        System.out.println("Start gRPC server");

        Server server= ServerBuilder
                .forPort(50051)
                .addService(new ScanServiceImpl())
                .build();

        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Received shutdown request");
            server.shutdown();
            System.out.println("Successfully stopped the server");
        } ));

        server.awaitTermination();
    }
}
