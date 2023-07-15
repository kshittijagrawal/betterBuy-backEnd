package com.example.Search.repository;

import com.example.Search.dto.ProductDTO;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchRepository extends SolrCrudRepository<ProductDTO, String> {

    @Query("productName:*?0*")
    List<ProductDTO> findByProductName(String name);

    @Query("brand:*?0*")
    List<ProductDTO> findByProductBrand(String brand);

    @Query("category:*?0*")
    List<ProductDTO> findByProductCategory(String category);

}
