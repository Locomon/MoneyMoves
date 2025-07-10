package com.magic.money;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.server.Route;
import java.util.concurrent.CompletionStage;
import com.magic.money.rest.RestController;
import akka.actor.ActorSystem;


public class Main {
    public static void main(String... args) {
        // Create the Actor System
        ActorSystem system = ActorSystem.create("my-akka-system");

        // Create the controller and route
        RestController controller = new RestController();
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
