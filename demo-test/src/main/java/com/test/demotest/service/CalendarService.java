package com.test.demotest.service;

import com.test.demotest.model.Calendar1;
import com.test.demotest.model.CalendarRequest;
import com.test.demotest.model.CalendarResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
@Slf4j
@Service
public class CalendarService {
    List<Calendar1> calendarList = new ArrayList<Calendar1>();

    public List<Calendar1> initialize() {

        Calendar1 c1 = new Calendar1("STORE001", "ALL", "13:30");
        Calendar1 c2 = new Calendar1("STORE002", "SUNDAY", "13:30");
        Calendar1 c3 = new Calendar1("STORE003", "MONDAY", "13:30");
        calendarList.add(c1);
        calendarList.add(c2);
        calendarList.add(c3);
        return calendarList;

    }



    public CalendarResponse getStatus(CalendarRequest calendar) {
        //Initializing the list
        initialize();

        if (calendarList.stream().anyMatch(x -> x.getLocationId().equals(calendar.getStoreId()))) {
            if (calendar.getRequestedDate().getTime() > calendarList.stream().filter(x -> x.getLocationId().equals(calendar.getStoreId())).findAny().get().getCutOffTime()) {
                CalendarResponse c = new CalendarResponse(calendar, "Not Available");
                return c;
            } else {
                CalendarResponse  c = new CalendarResponse(calendar, "Available");
                return c;
            }
        }
        log.error("No storeId found");
return new CalendarResponse(calendar,"No status");
    }
}
