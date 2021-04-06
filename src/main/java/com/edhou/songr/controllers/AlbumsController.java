package com.edhou.songr.controllers;

import com.edhou.songr.models.Album;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AlbumsController {
    static final String ALBUMS_PATH = "src/main/resources/albums.json";
    List<Album> albums;
    Gson gson = new Gson();

    public AlbumsController() throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(ALBUMS_PATH));
        albums = gson.fromJson(reader, new TypeToken<ArrayList<Album>>(){}.getType());
    }

    @GetMapping("/albums")
    public String renderAlbums(Model model) {
        model.addAttribute("albums", albums);
        return "albums.html";
    }
}
