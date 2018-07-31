package me.danielaguilar.movieTK.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(Actor.DISCRIMINATOR)
public class Actor extends CastMember {
    public static final String DISCRIMINATOR    =   "1";
    public Actor(String name, Movie movie){
        this.name   = name;
        this.movie  = movie;
    }
}
