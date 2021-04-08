package com.edhou.songr.models;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
public class Album {
    String title;
    String artist;
    int songCount;
    int length;
    String imageUrl;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "album")
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getSongCount() {
        return songCount;
    }

    public void setSongCount(int songCount) {
        this.songCount = songCount;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
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

    public int getMinUnusedTrackNumber() {
        int mex = 1;
        songs.sort(Comparator.comparing(Song::getTrackNumber));
        for (Song song : songs) {
            if (song.trackNumber == mex) mex++;
            else return mex;
        }
        return mex;
    }

    public List<Song> getSongs() {
        songs.sort(Comparator.comparing(Song::getTrackNumber));
        return songs;
    }

    public long getId() {
        return id;
    }
}
