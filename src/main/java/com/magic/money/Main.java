package com.magic.money;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.server.Route;
import java.util.concurrent.CompletionStage;
import com.magic.money.rest.RestController;
import akka.actor.typed.ActorRef;
import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.*;

import com.magic.money.core.cache.controller.*;


public class Main {
    public static void main(String... args) {
        // Create the Actor System
    	ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "MySystem");
    	
    	// Spawn InstrumentCacheController
    	ActorRef<InstrumentCacheCommand> cacheController =
    		    system.systemActorOf(InstrumentCacheController.create(), "cacheController", akka.actor.typed.Props.empty());    	
        // Create the controller and route
        RestController controller = new RestController(system, cacheController);
        Route routes = controller.createRoutes();

        // Bind the route to the server
        CompletionStage<ServerBinding> binding = Http.get(system)
                .newServerAt("localhost", 8080)
                .bind(routes);

        // Log when binding is complete
        binding.whenComplete((bindingResult, throwable) -> {
            if (throwable == null) {
                System.out.println("Server online at http://localhost:8080/");
            } else {
                System.err.println("Failed to bind HTTP server: " + throwable.getMessage());
                system.terminate();
            }
        });
    }
}
