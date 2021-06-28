package com.test.demotest.service;

import com.test.demotest.model.Availability;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
    public class AvailabilityService {
        List<Availability> availabilityList=new ArrayList<Availability>();
        public  List<Availability> initialize()
        {

            Availability a1=new Availability("Store001", "Prod1", new Date(2021 - 02 - 19), 1.0);
            Availability a2=new Availability	 ("Store001", "Prod2", new Date(2021 - 02 - 20), 3.0);
            Availability a3=new Availability   ("Store001", "Prod2", new Date(2021 - 02 - 21), 0.0);
            Collections.addAll(availabilityList,a1,a2,a3);
            return availabilityList;
        }
        @Async
        public CompletableFuture<Availability> getOrders(Date date){
            initialize();
            Optional<Availability> a=availabilityList.stream().filter(x->x.getDate().equals(date)).findAny();
            return CompletableFuture.completedFuture(a.get());
        }

    }


