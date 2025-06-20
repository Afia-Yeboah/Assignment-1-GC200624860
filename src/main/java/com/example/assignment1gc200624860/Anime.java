package com.example.assignment1gc200624860;

public class Anime {
    private String title;
    private String genre;
    private int year;
    private int episodes;
    private double rating;
    private double popularity;

    public Anime(String title, String genre, int year, int episodes, double rating, double popularity) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.episodes = episodes;
        this.rating = rating;
        this.popularity = popularity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getEpisodes() {
        return episodes;
    }

    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
