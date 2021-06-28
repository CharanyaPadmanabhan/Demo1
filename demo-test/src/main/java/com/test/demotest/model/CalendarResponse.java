package com.test.demotest.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalendarResponse {
    CalendarRequest calendarRequest;
    String status;
}
