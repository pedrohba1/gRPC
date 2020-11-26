package com.server.greeting.client;

import com.proto.dummy.DummyServiceGrpc;
import com.proto.greet.*;
import com.server.greeting.server.GreetingServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GreetingClient {

    public static void main(String[] args) {
        System.out.println("cliente aqui");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",50051)
                .usePlaintext()
                .build();


        // cria um cliente síncrono
        GreetServiceGrpc.GreetServiceBlockingStub greetClient = GreetServiceGrpc.newBlockingStub(channel);

        // cria um protocol Buffer com mensagem de olá
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("pedro Henrique")
                .setLastName("Bufulin de Almeida")
                .build();


        // Mesma coisa para o Greet Rquest:
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();


        // resopsta:
        GreetResponse greetResponse = greetClient.greet(greetRequest);
        System.out.println(greetResponse.getResult());



        channel.shutdown();
    }
}
