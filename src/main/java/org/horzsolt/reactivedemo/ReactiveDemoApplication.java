package org.horzsolt.reactivedemo;

import lombok.*;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.Movie;
import org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data.MovieRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Date;

@SpringBootApplication
public class ReactiveDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveDemoApplication.class, args);
	}

}

@Component
class SampleDataIntitializer implements ApplicationRunner {

	private final MovieRepository mr;

	public SampleDataIntitializer(MovieRepository mr) {
		this.mr = mr;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {


		Flux<Movie> movieFlux = Flux.just("Silence of Lambdas", "Y to Mono Tumbien", "Back to the Future", "AEon Flux")
				.map(m -> new Movie(m))
				.flatMap(mr::save);

		mr.deleteAll()
				.thenMany(movieFlux)
				.thenMany(mr.findAll())
				.subscribe(System.out::println);

		//movieFlux.thenMany(mr.findAll()).subscribe(System.out::println);
	}
}





