package com.example.happyface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.*;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@Controller
public class HappyfaceApplication {
    private final HappyfaceDB db;
    private static final Gson gson = new Gson();


    public HappyfaceApplication() throws Exception {
        db = new HappyfaceDB();
    }

    public static void main(String[] args) {
        SpringApplication.run(HappyfaceApplication.class, args);
    }

    @GetMapping("/")
    public String index(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return "index";
    }

    @GetMapping("/contact")
    public String contactUs(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return "contact";
    }

    @GetMapping("/products")
    public String products(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        return "products";
    }

    @ResponseBody
    @PostMapping("/message")
    public String postMessage(@RequestBody Message message, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        long msgId = db.addMessage(message);
        return gson.toJson(msgId);
    }

}
