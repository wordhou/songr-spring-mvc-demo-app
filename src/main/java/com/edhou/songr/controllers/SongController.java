package com.edhou.songr.controllers;

import com.edhou.songr.models.Album;
import com.edhou.songr.models.Song;
import com.edhou.songr.services.AlbumRepository;
import com.edhou.songr.services.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class SongController {
    @Autowired
    public SongRepository songRepo;
    @Autowired
    public AlbumRepository albumRepo;

    @RequestMapping(value = "/albums/{album_id}/songs", method = RequestMethod.POST)
    public RedirectView addSongToAlbum(@PathVariable("album_id") long album_id,
                                       String title, Integer trackNumber, Integer length) {
        Album album = albumRepo.getOne(album_id);
        Song song = new Song(title, length, trackNumber, album);

        songRepo.save(song);

        return new RedirectView("/albums/{album_id}");
    }

    @RequestMapping(value = "/albums/{album_id}/songs/{song_id}", method = RequestMethod.DELETE)
    public RedirectView removeSongFromAlbum(@PathVariable("album_id") long album_id,
                                            @PathVariable("song_id") long song_id) {
        Album album = albumRepo.getOne(album_id);
        Song song = songRepo.getOne(song_id);
        album.removeSong(song);
        albumRepo.save(album);
        songRepo.delete(song);
        return new RedirectView("/albums/{album_id}");
    }

    @RequestMapping(value = "/albums/{album_id}/songs/{song_id}", method = RequestMethod.PUT)
    public RedirectView updateSong(@PathVariable("album_id") long album_id,
                                   @PathVariable("song_id") long song_id,
                                   String title,
                                   Integer length,
                                   Integer trackNumber) {
        Song song = songRepo.getOne(song_id);
        if (title != null) song.setTitle(title);
        if (length != null) song.setLength(length);
        if (trackNumber != null) song.setTrackNumber(trackNumber);
        songRepo.save(song);
        return new RedirectView("/albums/{album_id}");
    }
}
