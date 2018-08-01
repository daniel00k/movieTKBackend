package me.danielaguilar.movieTK.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @GeneratedValue
    @Id
    private Long id;
    private String title;
    private String description;

    @OneToMany(mappedBy = "movie")
    private List<Actor> actors;

    @OneToMany(mappedBy = "movie")
    private List<Director> directors;

    public Movie(){}

    public Movie(final String title, final String description){
        this.title          = title;
        this.description    = description;
    }

    public Movie(final Long id, final String title, final String description){
        this.id             = id;
        this.title          = title;
        this.description    = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }
}
