package org.horzsolt.reactivedemo.org.horzsolt.reactivedemo.data;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MovieRepository extends ReactiveMongoRepository<Movie, String> {

}
