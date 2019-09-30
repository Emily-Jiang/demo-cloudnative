package com.example.demo.client;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/client")
@ApplicationScoped
public class ClientController {

    @Inject
    @RestClient
    private Service service;

    @Inject @ConfigProperty(name="conference") String conference;
    @GET
    @Path("/test/{parameter}")
    @Timeout(200)
    @Asynchronous
    @Fallback(fallbackMethod = "myFallback")
    public CompletionStage<String> onClientSide(@PathParam("parameter") String parameter) {
        return CompletableFuture.completedFuture(service.doSomething(parameter));
    }

    
    private CompletionStage<String> myFallback(@PathParam("parameter") String parameter) {
        return CompletableFuture.completedFuture("This is my fallback " + conference);
    }
   
    /*@Operation( summary = "Return the service response.", 
    description = "Returns a completionStage.") */
    

}
