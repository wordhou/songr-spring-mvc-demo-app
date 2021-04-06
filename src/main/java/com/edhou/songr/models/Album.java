package com.edhou.songr.models;

public class Album {
    String title;
    String artist;
    int songCount;
    int length;
    String imageUrl;

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
}
