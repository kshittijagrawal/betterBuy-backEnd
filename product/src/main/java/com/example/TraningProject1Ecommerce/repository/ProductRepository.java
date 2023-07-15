package com.example.TraningProject1Ecommerce.repository;

import com.example.TraningProject1Ecommerce.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
//            @Query(value = "Select i  from instructor as i  where i.courseId=: courseId ",nativeQuery = true)
//    Instructor instructorForCourse(@Param("courseId") String courseId);

    public boolean existsByProductName(String productName);

    public boolean existsByBrand(String brand);

    public boolean existsByCategory(String category);


}
