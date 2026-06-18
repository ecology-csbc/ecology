package com.retail;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RetailOrderRepository extends MongoRepository<RetailOrder, String> {
}
