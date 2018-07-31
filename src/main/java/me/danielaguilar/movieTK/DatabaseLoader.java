package me.danielaguilar.movieTK;

import me.danielaguilar.movieTK.models.Actor;
import me.danielaguilar.movieTK.models.Director;
import me.danielaguilar.movieTK.models.Movie;
import me.danielaguilar.movieTK.repositories.ActorRepository;
import me.danielaguilar.movieTK.repositories.DirectorRepository;
import me.danielaguilar.movieTK.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final DirectorRepository directorRepository;
    private final ActorRepository actorRepository;

    @Autowired
    public DatabaseLoader(MovieRepository movieRepository, DirectorRepository directorRepository, ActorRepository actorRepository) {
        this.movieRepository    = movieRepository;
        this.directorRepository = directorRepository;
        this.actorRepository    = actorRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Movie interstellar = new Movie("Interstellar", "La wea buena");
        Movie lOtR = new Movie("El señor de los anillos", "Frodo ql");
        this.movieRepository.save(interstellar);
        this.movieRepository.save(lOtR);
        this.actorRepository.save(new Actor("Elaia Madera", lOtR));
        this.actorRepository.save(new Actor("Vigo morteinsensssnsn", lOtR));
        this.directorRepository.save(new Director("Peter Jackson", lOtR));
    }
}