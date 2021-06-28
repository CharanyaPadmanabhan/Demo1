package com.test.demotest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductRes {
    String productId;
    String productName;
    String unitOfMeasure;
    String launchDate;
}
