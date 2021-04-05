package com.edhou.songr;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Locale;

@Controller
public class HelloWorldController {
    String name;

    public HelloWorldController() {
        name = "Joe";
    }

    @GetMapping("/hello")
    public String helloWorld(Model model) {
        model.addAttribute("name", name);
        return "hello-world.html";
    }

    @GetMapping("/capitalize/{text}")
    public String capitalize(Model model,
                             @PathVariable String text) {
        model.addAttribute("capitalizedText", text.toUpperCase(Locale.ROOT));
        return "capitalize.html";
    }
}
