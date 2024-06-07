package com.luna.stub;

import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) throws IOException, JSONException, ParseException, InterruptedException {
//        Request req = new Request();
//        req.getCatalog();

        SpringApplication.run(Application.class, args);
    }

}
