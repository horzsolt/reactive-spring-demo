package org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Flux<MovieEvent> events(String movieId) {
        return Flux.<MovieEvent>generate(sink -> sink.next(new MovieEvent(movieId, new Date())))
                .delayElements(Duration.ofSeconds(1));
    }

    public Mono<Movie> byId(String id) {
        return this.movieRepository.findById(id);
    }

    public Flux<Movie> all() {
        return this.movieRepository.findAll();
    }
}