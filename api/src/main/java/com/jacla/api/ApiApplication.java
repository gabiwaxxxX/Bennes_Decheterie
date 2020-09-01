package com.jacla.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.lang.Thread;
import java.lang.InterruptedException;
import java.lang.ProcessBuilder;
import java.net.HttpURLConnection;
import java.awt.Desktop; 
import java.net.URL;
import java.io.*;
import java.net.URI;
import java.util.Random;


@SpringBootApplication
public class ApiApplication {

    public static void main(String[] args) {
        UI frame = new UI();
        frame.pack();
        frame.setSize(1280, 780);
        frame.setVisible(true);
        SpringApplication.run(ApiApplication.class, args);
    }
}
