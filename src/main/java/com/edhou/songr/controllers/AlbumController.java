package com.edhou.songr.controllers;

import com.edhou.songr.models.Album;
import com.edhou.songr.models.AlbumRepository;
import com.edhou.songr.services.FileUploadService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Controller
public class AlbumController {
    static final String ALBUMS_PATH = "src/main/resources/albums.json";
    Gson gson = new Gson();
    @Autowired
    public AlbumRepository albumRepository;
    @Autowired
    public FileUploadService fileUploadService;

    public AlbumController() throws FileNotFoundException {
        // Populate the database with some initial data
//        System.out.println(albumRepository);
//        if (albumRepository.count() == 0) {
//            List<Album> albums;
//            try (BufferedReader reader = new BufferedReader(new FileReader(ALBUMS_PATH))) {
//                albums = gson.fromJson(reader, new TypeToken<ArrayList<Album>>(){}.getType());
//                albumRepository.saveAll(albums);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return;
//            }
//        }
    }

    @GetMapping("/albums")
    public String renderAlbums(Model model) {
        List<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);
        return "albums.html";
    }

    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    public RedirectView addNewAlbum(String title, String artist, Integer songCount,
                                    Integer length, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Album album = new Album(title, artist, songCount, length, fileName);
        albumRepository.save(album);

        System.out.println(album.getId());
        String uploadDir = "uploaded-photos/" + album.getId();
        fileUploadService.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/albums");
    }
}
