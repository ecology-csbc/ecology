package com.ecology;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EcologyRepository extends MongoRepository<Ecology, String> {
}
