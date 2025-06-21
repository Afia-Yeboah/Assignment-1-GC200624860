package com.example.assignment1gc200624860;

/**
 * This is the Anime entry with title, genre, year, episodes
 * rating and popularity
 */
public class Anime {
    private String title;
    private String genre;
    private int year;
    private int episodes;
    private double rating;
    private double popularity;

    /**
     * Creating a new Anime with its details
     */
    public Anime(String title, String genre, int year, int episodes, double rating, double popularity) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.episodes = episodes;
        this.rating = rating;
        this.popularity = popularity;
    }

    /**
     * return the anime's title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set a parameter title with new for the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * return the genre of the anime
     */
    public String getGenre() {
        return genre;
    }

    /**
     * set a new genre for the anime
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * return the anime release year
     */
    public int getYear() {
        return year;
    }

    /**
     * set the parameter for the release year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * return the episodes for the selected anime
     */
    public int getEpisodes() {
        return episodes;
    }

    /**
     * set a new episode count for the anime selected
     */
    public void setEpisodes(int episodes) {
        this.episodes = episodes;
    }

    /**
     * return the user rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * set the user rating
    */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * return the popularity values for the anime
     */
    public double getPopularity() {
        return popularity;
    }

    /**
     * set new value for the popularity
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
}
