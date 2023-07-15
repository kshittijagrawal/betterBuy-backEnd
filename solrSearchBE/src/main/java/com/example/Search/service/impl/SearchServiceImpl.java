package com.example.Search.service.impl;

import com.example.Search.dto.ProductDTO;
import com.example.Search.repository.SearchRepository;
import com.example.Search.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SearchServiceImpl implements SearchService {


    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SolrClient solrClient;

    @Override
    public void addProducts() {}

    @Override
    public List<ProductDTO> searchByName(String productName) {
        return searchRepository.findByProductName(productName);
    }

    @Override
    public List<ProductDTO> searchByBrand(String brand) {
        return searchRepository.findByProductBrand(brand);
    }

    @Override
    public List<ProductDTO> searchByCategory(String category) {
        return searchRepository.findByProductCategory(category);
    }
}
