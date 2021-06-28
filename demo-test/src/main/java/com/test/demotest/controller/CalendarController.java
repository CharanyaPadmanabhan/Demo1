package com.test.demotest.controller;

import com.test.demotest.model.CalendarRequest;
import com.test.demotest.model.CalendarResponse;
import com.test.demotest.service.CalendarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
@Slf4j
public class CalendarController {
    @Autowired
    CalendarService service;
@PostMapping("/findStoreAvailability")
    public ResponseEntity findStoreAvailability(@RequestAttribute CalendarRequest request){
    log.trace("End point invoked for findStoreAvailability");
  /*  Date d = request.getRequestedDate();
    Calendar cal = Calendar.getInstance();
    cal.setTime(d);
    int day = cal.get(Calendar.DAY_OF_MONTH);*/
    service.initialize();
CalendarResponse c=service.getStatus(request);
 return new ResponseEntity<>(c, HttpStatus.OK);

}



}
