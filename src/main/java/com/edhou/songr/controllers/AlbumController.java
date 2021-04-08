package com.edhou.songr.controllers;

import com.edhou.songr.models.Album;
import com.edhou.songr.services.AlbumRepository;
import com.edhou.songr.services.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.List;

@Controller
public class AlbumController {
    @Autowired
    public AlbumRepository repo;
    @Autowired
    public FileUploadService fileUploadService;

    @GetMapping("/albums")
    public String renderAlbums(Model model) {
        List<Album> albums = repo.findAll();
        model.addAttribute("albums", albums);
        return "albums.html";
    }

    @RequestMapping(value = "/albums", method = RequestMethod.POST)
    public RedirectView addNewAlbum(String title, String artist, Integer songCount,
                                    Integer length, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        Album album = new Album(title, artist, songCount, length, fileName);
        repo.save(album);

        System.out.println(album.getId());
        String uploadDir = "uploaded-photos/" + album.getId();
        fileUploadService.saveFile(uploadDir, fileName, multipartFile);
        return new RedirectView("/albums");
    }

    @RequestMapping(value = "/albums/{album_id}", method = RequestMethod.PUT)
    public RedirectView updateAlbum(
            @PathVariable("album_id") long album_id,
            String title,
            String artist,
            Integer songCount,
            Integer length) {
        Album album = repo.getOne(album_id);
        if (title != null) album.setTitle(title);
        if (artist != null) album.setArtist(artist);
        if (songCount != null) album.setSongCount(songCount);
        if (length != null) album.setLength(length);
        repo.save(album);
        return new RedirectView("/albums/{album_id}");
    }

    @RequestMapping(value = "/albums/{album_id}", method = RequestMethod.GET)
    public String getAlbumDetail(
            @PathVariable("album_id") long album_id,
            Model model
    ) {
        Album album = repo.getOne(album_id);
        model.addAttribute(album);
        return "album-detail.html";
    }
}
