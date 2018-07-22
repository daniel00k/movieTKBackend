package me.danielaguilar.movieTK;

import me.danielaguilar.movieTK.models.Movie;
import me.danielaguilar.movieTK.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final MovieRepository repository;

    @Autowired
    public DatabaseLoader(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.repository.save(new Movie("Interstellar", "La wea buena"));
        this.repository.save(new Movie("El señor de los anillos", "Frodo ql"));
    }
}