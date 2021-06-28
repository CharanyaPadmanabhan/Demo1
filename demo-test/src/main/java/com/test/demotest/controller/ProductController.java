package com.test.demotest.controller;

import com.test.demotest.model.*;
import com.test.demotest.service.AvailabilityService;
import com.test.demotest.service.CapacityService;
import com.test.demotest.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@Slf4j
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    AvailabilityService availabilityService;

    @Autowired
    CapacityService capacityService;

    @PostMapping("/sortProducts")
    public ResponseEntity<?> sortProducts(@RequestBody Product product) {
        log.trace("sort products endpoint invoked");
        List<ProductRes> products = product.getProductList();
        List<ProductRes> response = productService.sortProducts(products);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/getProdAvailability")
    public ResponseEntity<?> getProdAvailability(@RequestBody Availability availability) throws ExecutionException, InterruptedException {
        availabilityService.initialize();
        capacityService.initialize();
        CompletableFuture<Availability> availabilityOrders = availabilityService.getOrders(availability.getDate());
        CompletableFuture<Capacity> capacityOrders = capacityService.getCapacity(availability.getDate());
        CompletableFuture.allOf(availabilityOrders, capacityOrders).join();

            if (availabilityOrders.get().getAvailQty() > 0.0 && capacityOrders.get().getNoOfOrdersAccepted() > 0.0) {
                CapacityResponse c = new CapacityResponse(availability, "available");
                return new ResponseEntity<>(c, HttpStatus.OK);

            } else {
                CapacityResponse c = new CapacityResponse(availability, "No capacity");
                return new ResponseEntity<>(c, HttpStatus.OK);

        }

    }
}


