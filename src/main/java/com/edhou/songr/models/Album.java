package com.edhou.songr.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Album {
    String title;
    String artist;
    int songCount;
    int length;
    String imageUrl;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    public Album() {
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

    public long getId() {
        return id;
    }
}
