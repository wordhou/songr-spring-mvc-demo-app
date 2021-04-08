package com.edhou.songr.models;

import javax.persistence.*;

@Entity
public class Song {
    String title;
    int length;
    int trackNumber;
    @ManyToOne
    Album album;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    public Song() {
    }

    public Song(String title, int length, int trackNumber, Album album) {
        this.title = title;
        this.length = length;
        this.trackNumber = trackNumber;
        this.album = album;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public int getTrackNumber() {
        return trackNumber;
    }

    public Album getAlbum() {
        return album;
    }

    public String getRunTime() {
        return String.format("%d minutes, %d seconds", length / 60, length % 60);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setTrackNumber(int trackNumber) {
        this.trackNumber = trackNumber;
    }

    public long getId() {
        return id;
    }
}
