package com.edhou.songr.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Album {
    String title;
    String artist;
    int songCount;
    int length;
    String imageUrl;
    @OneToMany
    List<Song> songs;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    public Album() {
    }

    public Album(String title, String artist, int songCount, int length, String imageUrl, List<Song> songs) {
        this.title = title;
        this.artist = artist;
        this.songCount = songCount;
        this.length = length;
        this.imageUrl = imageUrl;
        this.songs = songs;
    }

    public Album(String title, String artist, int songCount, int length, String imageUrl) {
        this.title = title;
        this.artist = artist;
        this.songCount = songCount;
        this.length = length;
        this.imageUrl = imageUrl;
    }

    public String getTitle() { return title; }

    public String getArtist() {
        return artist;
    }

    public int getSongCount() {
        return songCount;
    }

    public int getLength() {
        return length;
    }

    public String getRunTime() {
        return String.format("%d minutes %d seconds", length / 60, length % 60);
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getImagePath() {
        if (imageUrl == null) return null;
        return String.format("/uploaded-photos/%d/%s", id, imageUrl);
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public long getId() {
        return id;
    }
}
