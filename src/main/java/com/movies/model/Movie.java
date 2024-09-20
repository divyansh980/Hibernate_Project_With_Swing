package com.movies.model;

import javax.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String genre;
    private String director;
    private int year;

    // Constructors
    public Movie() { }

    public Movie(String title, String genre, String director, int year) {
        this.title = title;
        this.genre = genre;
        this.director = director;
        this.year = year;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Movie [ID=" + id + ", Title=" + title + ", Genre=" + genre + ", Director=" + director + ", Year=" + year + "]";
    }
}
