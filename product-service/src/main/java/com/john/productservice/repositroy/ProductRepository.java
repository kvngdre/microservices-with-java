package com.john.productservice.repositroy;

import com.john.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

//The types mentioned in the angle brackets is the <Model, Type_of_ID_field>.
public interface ProductRepository extends MongoRepository<Product, String> {
}
