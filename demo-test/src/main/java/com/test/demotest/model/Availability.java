package com.test.demotest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Availability {
    String storeNo;
    String productId;
    Date date;
    Double availQty;
}
