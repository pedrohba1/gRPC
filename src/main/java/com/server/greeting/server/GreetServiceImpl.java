package com.server.greeting.server;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {

    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
       Greeting greeting  = request.getGreeting();
        String firstname = greeting.getFirstName();
        String lastname = greeting.getLastName();

        //cria a resposta
        String result = "hello " + firstname + " " + lastname ;
        GreetResponse response = GreetResponse.newBuilder()
                .setResult(result)
                .build();


        // envia a resposta
        responseObserver.onNext(response);

        // completa a chamada RPC:
        responseObserver.onCompleted();

        //super.greet(request, responseObserver);
    }
}
