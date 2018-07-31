package me.danielaguilar.movieTK.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Director.DISCRIMINATOR)
public class Director extends CastMember {
    public static final String DISCRIMINATOR    =   "2";
    public Director(String name, Movie movie){
        this.name   = name;
        this.movie  = movie;
    }
}
