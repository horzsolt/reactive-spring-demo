package org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.web;

import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.Movie;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.MovieEvent;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.MovieService;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.security.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class WebConfiguration {

    @Bean
    RouterFunction<?> routes(MovieService movieService, UserHandler uh) {
        return RouterFunctions

                .route(GET("/movies"),
                        serverRequest -> ServerResponse.ok().body(movieService.all(), Movie.class))
                .andRoute(GET("/movies/{id}"),
                        serverRequest -> ServerResponse.ok().body(movieService.byId(serverRequest.pathVariable("id")), Movie.class))
                .andRoute(GET("/movies/{id}/events"), serverRequest ->
                        ServerResponse.ok()
                                .contentType(MediaType.TEXT_EVENT_STREAM)
                                .body(movieService.events(serverRequest.pathVariable("id")), MovieEvent.class))
                .andRoute(GET("/users/me"), uh::current)
                .andRoute(GET("/users/{username}"), uh::byUsername);
    }
}
