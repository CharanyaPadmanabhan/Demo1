package com.test.demotest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CalendarRequest {
    String storeId;
    Date requestedDate;

}
