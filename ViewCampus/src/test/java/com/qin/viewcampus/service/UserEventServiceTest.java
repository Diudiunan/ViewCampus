package com.qin.viewcampus.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@SpringBootTest
public class UserEventServiceTest {

    @Autowired
    private IUserEventService iUserEventService;

    @Test
    void test(){
        iUserEventService.EventsIdOfUser("o_-Xc5e5EWzdidpVvVfUP9P7huUY");
    }

    @Test
    void sda(){
        iUserEventService.UpdateEventIdById("o_-Xc5e5EWzdidpVvVfUP9P7huUY","fsd");
    }

    @Test
    void dff(){
        List<String> idList = new ArrayList<>();
        idList.add("sds");
        idList.add("sds");
        idList.add("sds");
        idList.add("sds");
        System.out.println(idList);
        //
        String fg = String.join(",", idList);
        System.out.println(fg);
        //
        List<String> idList2 = new ArrayList<>();
        System.out.println(String.join(",", idList2));
        //
        List<String> idList3 = new ArrayList<>();
        idList3.add("sds");
        System.out.println(String.join(",", idList3));
        //
        List<String> idList4 = Arrays.asList("".split(","));
        System.out.println(idList4);
        System.out.println(idList4.size());
        List<String> idList5 = new ArrayList<>(idList4);
        System.out.println(idList5);
        System.out.println(idList5.size());
        if(Objects.equals(idList5.get(0), "")){
            idList5.set(0,"sdd");
        }else {
            idList5.add("sdp");
        }
        System.out.println(idList5);
        System.out.println(idList5.size());
        System.out.println(String.join(",", idList5));
    }
}
