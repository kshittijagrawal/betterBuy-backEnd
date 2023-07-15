package com.example.Search.controller;

import com.example.Search.dto.ProductDTO;
import com.example.Search.repository.SearchRepository;
import com.example.Search.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SolrClient solrClient;

    @Autowired
    SolrTemplate solrTemplate;

    @PostMapping("/addProduct")
    public String addProductDetails(@RequestBody ProductDTO productDTO) {
        SolrInputDocument doc = new SolrInputDocument();
        doc.addField("productId", productDTO.getProductId());
        doc.addField("productName", productDTO.getProductName());
        doc.addField("brand",  productDTO.getBrand());
        doc.addField("merchantId", productDTO.getMerchantId());
        doc.addField("category", productDTO.getCategory());
        doc.addField("image", productDTO.getImage());
        doc.addField("price", productDTO.getPrice());
        doc.addField("description", productDTO.getDescription());
        doc.addField("stock", productDTO.getStock());
        doc.addField("productReview", productDTO.getProductReview());
        doc.addField("stockSold", productDTO.getStockSold());
        doc.addField("productUsp", productDTO.getProductUsp());

        try {
            solrClient.add(doc);
            solrClient.commit();
            return "Data added successfully";

        } catch (SolrServerException | IOException e) {
            return "Failed to add data.\n" + e.getMessage();
        }
    }

    @GetMapping("/query/{product}")
    public ResponseEntity<SolrDocumentList> searchProducts(@PathVariable("product") String product) throws IOException, SolrServerException {
        SolrClient solrClient = new HttpSolrClient.Builder("http://localhost:8983/solr/product-testing").build();
        SolrQuery solrQuery = new SolrQuery();

        solrQuery.setQuery("productName:*"+product+"* OR brand:*"+product+"* OR category:*"+product+"*");

        QueryResponse response = solrClient.query(solrQuery);
        Object solrDocument = response.getResults().toArray();
        return new ResponseEntity<SolrDocumentList>(response.getResults(),HttpStatus.OK);
    }
}