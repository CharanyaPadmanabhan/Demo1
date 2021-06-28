package com.test.demotest.service;

import com.test.demotest.model.ProductRes;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public List<ProductRes> sortProducts(List<ProductRes> products) {
        List<ProductRes> result = products.stream()
                .sorted(Comparator.comparing(ProductRes::getProductId).thenComparing(ProductRes::getLaunchDate).reversed())
                .collect(Collectors.toList());
        return result;
    }
}
