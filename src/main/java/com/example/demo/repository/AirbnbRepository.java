package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.demo.domain.ListingsAndReviews;

public interface AirbnbRepository extends MongoRepository<ListingsAndReviews, String> {

	@Query("{ price: { $lt : ?0, $gt : ?1 }}")
	List<ListingsAndReviews> findListingsAndReviewsByPriceRange(double max,double min);
}
