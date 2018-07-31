package me.danielaguilar.movieTK.models;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="cast_member_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class CastMember {

    @Id
    @GeneratedValue
    protected Long id;

    @ManyToOne
    @JoinColumn(name="movie_id", nullable=false)
    protected Movie movie;
    protected String name;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
