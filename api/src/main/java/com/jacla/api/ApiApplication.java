package com.jacla.api;

import com.jacla.api.util.SetConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class ApiApplication {
    private static Interface frame;

    public static void main(String[] args) {
        frame = new Interface();
        SetConnection connector = new SetConnection();
        connector.checkHosts();
        connector.SendIPtoArduinoReachable();

        SpringApplication.run(ApiApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady(){
        frame.start();
    }
}
