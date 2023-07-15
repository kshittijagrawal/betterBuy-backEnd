package com.example.Search.service;

import com.example.Search.dto.ProductDTO;


import java.util.List;

public interface SearchService {

    public void addProducts();
    public List<ProductDTO> searchByName(String productName);
    public List<ProductDTO> searchByBrand(String brand);
    public List<ProductDTO> searchByCategory(String category);

}
