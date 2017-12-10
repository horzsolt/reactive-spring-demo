package org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.web;

import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.Movie;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.MovieEvent;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.MovieService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movies")
class LegacyRestController {

    private final MovieService movieService;

    LegacyRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<MovieEvent> crossTheStreams(@PathVariable String id) {
        return movieService.events(id);
    }

    @GetMapping("/{id}")
    Mono<Movie> byId(@PathVariable String id) {
        return movieService.byId(id);
    }

    @GetMapping
    Flux<Movie> all() {
        return movieService.all();
    }

}