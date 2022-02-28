package com.example.helloworld;

import com.example.helloworld.health.TemplateHealthCheck;
import com.example.helloworld.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
        if (args.length > 1) {
            switch (args[0]) {
                case "server":
                    System.out.println("Working as a server...");
                    break;
                case "client":
                    System.out.println("Working as a client...");
                    break;
            }
        }
    }

    public static String camelCase(String input) {
        StringBuilder sb = new StringBuilder();
        for (String part : input.split("\\s+")) {
            String tmp = part.toLowerCase();
            char first = Character.toUpperCase(tmp.toCharArray()[0]);
            char[] arr = tmp.toCharArray();
            arr[0] = first;
            sb.append(String.valueOf(arr));
        }
        return sb.toString();
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
    }


}